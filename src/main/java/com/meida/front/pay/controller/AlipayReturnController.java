package com.meida.front.pay.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.scripting.xmltags.VarDeclSqlNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.meida.common.util.DateUtils;
import com.meida.common.util.JsonUtils;
import com.meida.front.pay.domain.po.AlipayNotify;
import com.meida.front.pay.domain.po.MemberFundCharge;
import com.meida.front.pay.service.inter.IMemberFundChargeService;
import com.meida.pay.alipay.config.AlipayConfig;
import com.meida.pay.alipay.constant.AlipayTradeStatus;

@Controller
@RequestMapping(value = "/front/pay/alipayreturn")
public class AlipayReturnController {
	private static final Logger logger = LoggerFactory.getLogger(AlipayNotifyController.class);// slf4j日志记录器
	@Autowired
	private IMemberFundChargeService memberFundChargeService;

	private ExecutorService executorService = Executors.newFixedThreadPool(20);

	@RequestMapping(value = "/index")
	public ModelAndView index(HttpServletRequest request) {
		handleAlipay(request);
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}

	private String handleAlipay(HttpServletRequest request) {
		Map<String, String> params = convertRequestParamsToMap(request); // 将异步通知中收到的待验证所有参数都存放到map中
		String paramsJson = JsonUtils.toJSONString(params);
		logger.info("支付宝回调，{}", paramsJson);
		System.out.println(paramsJson);
		try {
			// 调用SDK验证签名
			boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY,
					AlipayConfig.CHARSET, AlipayConfig.SIGNTYPE);
			if (signVerified) {
				logger.info("支付宝回调签名认证成功");
				// 按照支付结果异步通知中的描述，对支付结果中的业务内容进行1\2\3\4二次校验，校验成功后在response中返回success，校验失败返回failure
				this.check(params);

				String out_trade_no = params.get("out_trade_no");
				MemberFundCharge memberFundCharge = memberFundChargeService.getObjectByOrderNo(out_trade_no);
				if (memberFundCharge != null) {
					return "failure";
				}
				if (memberFundCharge.getIsPay().equals("yes")) {
					return "success";
				}
				// 另起线程处理业务
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						AlipayNotify alipayNotify = buildAlipayNotifyParam(params);
						String trade_status = alipayNotify.getTrade_status();
						// 支付成功
						if (trade_status.equals(AlipayTradeStatus.TRADE_SUCCESS)
								|| trade_status.equals(AlipayTradeStatus.TRADE_FINISHED)) {
							// 处理支付成功逻辑
							try {
								Date nowTime = DateUtils.now();
								alipayNotify.setCreateDate(nowTime);
								alipayNotify.setOperateDate(nowTime);
								alipayNotify.setIsValid(1);
								alipayNotify.setRemark("");
								String orderNo = alipayNotify.getOut_trade_no();
								Boolean isFlag = memberFundChargeService.handleAlipayTradeSuccess(alipayNotify);
								if (isFlag == false) {
									// 记录日志
									logger.error("没有处理支付宝回调业务，支付宝交易状态：{},params:{}", trade_status, paramsJson);
								}

							} catch (Exception e) {
								logger.error("支付宝回调业务处理报错,params:" + paramsJson, e);
							}
						} else {
							logger.error("没有处理支付宝回调业务，支付宝交易状态：{},params:{}", trade_status, paramsJson);
						}
					}
				});
				// 如果签名验证正确，立即返回success，后续业务另起线程单独处理
				// 业务处理失败，可查看日志进行补偿，跟支付宝已经没多大关系。
				return "success";
			} else {
				logger.info("支付宝回调签名认证失败，signVerified=false, paramsJson:{}", paramsJson);
				return "failure";
			}
		} catch (AlipayApiException e) {
			logger.error("支付宝回调签名认证失败,paramsJson:{},errorMsg:{}", paramsJson, e.getMessage());
			return "failure";
		}
	}

	// 将request中的参数转换成Map
	private static Map<String, String> convertRequestParamsToMap(HttpServletRequest request) {
		Map<String, String> retMap = new HashMap<String, String>();

		Set<Entry<String, String[]>> entrySet = request.getParameterMap().entrySet();

		for (Entry<String, String[]> entry : entrySet) {
			String name = entry.getKey();
			String[] values = entry.getValue();
			int valLen = values.length;

			if (valLen == 1) {
				retMap.put(name, values[0]);
			} else if (valLen > 1) {
				StringBuilder sb = new StringBuilder();
				for (String val : values) {
					sb.append(",").append(val);
				}
				retMap.put(name, sb.toString().substring(1));
			} else {
				retMap.put(name, "");
			}
		}

		return retMap;
	}

	private AlipayNotify buildAlipayNotifyParam(Map<String, String> params) {
		String json = JsonUtils.toJSONString(params);
		return JSON.parseObject(json, AlipayNotify.class);
	}

	/**
	 * 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
	 * 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
	 * 3、校验通知中的seller_id（或者seller_email)是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
	 * 4、验证app_id是否为该商户本身。上述1、2、3、4有任何一个验证不通过，则表明本次通知是异常通知，务必忽略。
	 * 在上述验证通过后商户必须根据支付宝不同类型的业务通知，正确的进行不同的业务处理，并且过滤重复的通知结果数据。
	 * 在支付宝的业务通知中，只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功。
	 * 
	 * @param params
	 * @throws AlipayApiException
	 */
	private void check(Map<String, String> params) throws AlipayApiException {
		String out_trade_no = params.get("out_trade_no");

		// 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
		MemberFundCharge memberFundCharge = memberFundChargeService.getObjectByOrderNo(out_trade_no);
		if (memberFundCharge == null) {
			throw new AlipayApiException("out_trade_no错误");
		}

		// 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
		long total_amount = new BigDecimal(params.get("total_amount")).multiply(new BigDecimal(100)).longValue();
		if (total_amount != memberFundCharge.getChargeMoney().multiply(new BigDecimal(100)).longValue()) {
			throw new AlipayApiException("error total_amount");
		}

		// 3、校验通知中的seller_id（或者seller_email)是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
		String seller_id = params.get("seller_id");
		if (!seller_id.equals(AlipayConfig.UID)) {
			throw new AlipayApiException("seller_id不一致");
		}

		// 4、验证app_id是否为该商户本身。
		if (!params.get("app_id").equals(AlipayConfig.APPID)) {
			throw new AlipayApiException("app_id不一致");
		}
	}

}
