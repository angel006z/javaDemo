package com.meida.front.pay.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.meida.base.domain.vo.ResultMessage;
import com.meida.common.util.JsonUtils;
import com.meida.common.util.constant.EErrorCode;
import com.meida.front.pay.domain.dto.AlipayReturnParamDto;
import com.meida.front.pay.domain.po.Recharge;
import com.meida.front.pay.service.inter.IRechargeService;
import com.meida.pay.alipay.config.AlipayConfig;

@Controller
@RequestMapping(value = "/front/pay/alipayreturn")
public class AlipayReturnController {
	private static final Logger logger = LoggerFactory.getLogger(AlipayNotifyController.class);// slf4j日志记录器
	@Autowired
	private IRechargeService rehargeService;

	@RequestMapping(value = "/index")
	public ModelAndView index(HttpServletRequest request) {
		System.out.println("---------同步通知 Begin---------");
		String result = handleAlipay(request);
		System.out.println("result:" + result);
		System.out.println("---------同步通知 End---------");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("result",result);
		return modelAndView;
	}

	private String handleAlipay(HttpServletRequest request) {
		Map<String, String> params = convertRequestParamsToMap(request); // 将异步通知中收到的待验证所有参数都存放到map中
		String paramsJson = JsonUtils.toJSONString(params);
		System.out.println("支付宝回调参数：" + paramsJson);
		try {
			// 调用SDK验证签名
			boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY,
					AlipayConfig.CHARSET, AlipayConfig.SIGNTYPE);
			if (signVerified) {
				logger.info("支付宝回调签名认证成功");
				this.check(params);

				AlipayReturnParamDto alipayReturnParamDto = buildAlipayReturnParam(params);
				ResultMessage resultMessage = rehargeService.handleAlipayReturn(alipayReturnParamDto);
				System.out.println("resultMessage:" + JsonUtils.toJSONString(resultMessage));
				logger.info(JsonUtils.toJSONString(resultMessage));
				if (resultMessage.getCode().equals(EErrorCode.Success)) {
					return "success";
				} else {
					return "failure";
				}
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

	private AlipayReturnParamDto buildAlipayReturnParam(Map<String, String> params) {
		String json = JsonUtils.toJSONString(params);
		return JSON.parseObject(json, AlipayReturnParamDto.class);
	}

	private void check(Map<String, String> params) throws AlipayApiException {
		String out_trade_no = params.get("out_trade_no");

		// 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
		Recharge fundCharge = rehargeService.getObjectByOrderNo(out_trade_no);
		if (fundCharge == null) {
			throw new AlipayApiException("out_trade_no错误");
		}

		// 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
		long total_amount = new BigDecimal(params.get("total_amount")).multiply(new BigDecimal(100)).longValue();
		if (total_amount != fundCharge.getRechargeMoney().multiply(new BigDecimal(100)).longValue()) {
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
