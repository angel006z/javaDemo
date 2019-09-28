package com.meida.front.pay.service.impl;

import com.meida.base.domain.vo.ResultMessage;
import com.meida.common.util.DateUtils;
import com.meida.common.util.FrontUtils;
import com.meida.common.util.constant.EErrorCode;
import com.meida.common.util.constant.ESystemStatus;
import com.meida.front.pay.domain.dto.BuildChargeOrderDto;
import com.meida.front.pay.domain.po.CurrentMember;
import com.meida.front.pay.domain.po.FundCharge;
import com.meida.front.pay.service.inter.IFundChargeService;
import com.meida.front.pay.service.inter.IPayService;
import com.meida.pay.pojo.EPayChannel;
import com.meida.pay.pojo.ParametersTradePay;
import com.meida.pay.pojo.ResultTradePay;
import com.meida.pay.service.inter.ITradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Service
public class PayServiceImpl implements IPayService {

	@Autowired
	private ITradeService tradeService;

	@Autowired
	private IFundChargeService fundChargeService;
	/**
	 * 构建充值订单
	 * 产生订单号，返回相应支付路径
	 * 网页支付：微信返回二维码，支付宝返回支付宝付款路径
	 *
	 */
	@Override
	public ResultMessage buildChargeOrder(BuildChargeOrderDto buildChargeOrderDto) {
		CurrentMember currentMember = buildChargeOrderDto.getCurrentMember();

		Date nowTime = DateUtils.now();
		ResultMessage resultMessage = new ResultMessage();
		String orderNo = getOrderNoByCharge();
		if (fundChargeService.isExistOrderNo(orderNo)) {
			resultMessage.setCode(EErrorCode.Error);
			resultMessage.setMessage("该订单号已存在，请重新充值");
			return resultMessage;
		}

		BigDecimal total_fee = buildChargeOrderDto.getTotal_fee();
		if (total_fee.signum() <= 0) {
			resultMessage.setCode(EErrorCode.Error);
			resultMessage.setMessage("充值金额必需大于0，请重新充值");
			return resultMessage;
		}

		Long chargeMemberId = buildChargeOrderDto.getChargeMemberId();
		if (chargeMemberId <= 0) {
			resultMessage.setCode(EErrorCode.Error);
			resultMessage.setMessage("充值会员id不能为空");
			return resultMessage;
		}

		String payType=buildChargeOrderDto.getPayType();
		String payChannel=buildChargeOrderDto.getPayChannel();

		// 系统生成订单信息
		FundCharge fundCharge = new FundCharge();
		fundCharge.setMemberId(chargeMemberId);
		fundCharge.setOrderNo(orderNo);
		fundCharge.setChargeMoney(total_fee);
		fundCharge.setChargeDate(nowTime);
		fundCharge.setPayType(payType);
		fundCharge.setPayChannel(payChannel);
		fundCharge.setIsPay("no");
		fundCharge.setCreateDate(nowTime);
		fundCharge.setCreateUserId(currentMember.getMemberId().toString());
		fundCharge.setCreateUser(currentMember.getAccount());
		fundCharge.setUpdateDate(nowTime);
		fundCharge.setUpdateUserId(currentMember.getMemberId().toString());
		fundCharge.setUpdateUser(currentMember.getAccount());
		fundCharge.setIsValid(ESystemStatus.Valid);
		fundCharge.setRemark(String.format("充值，产品订单号：[%s],日期：[%s]", orderNo,
				DateUtils.formatDate(nowTime, "yyyy-MM-dd HH:mm:ss.SSS")));
		fundCharge.setSignature("待签名");
		boolean isFc = fundChargeService.addOrUpdate(fundCharge, true);
		if (isFc == false) {
			resultMessage.setCode(EErrorCode.Error);
			resultMessage.setMessage("系统产生订单错误，请重新充值");
			return resultMessage;
		}


		// 调用充值接口
		ParametersTradePay builderParameters = new ParametersTradePay();
		builderParameters.setPayType(payType);
		builderParameters.setPayChannel(payChannel);
		builderParameters.setOut_trade_no(orderNo);
		builderParameters.setSubject("充值");
		builderParameters.setAttach("");
		builderParameters.setBody(
				String.format("产品订单号：[%s],日期：[%s]", orderNo, DateUtils.formatDate(nowTime, "yyyy-MM-dd HH:mm:ss.SSS")));
		builderParameters.setTotal_fee(total_fee);
		ResultTradePay resultTradePay = tradeService.tradePay(builderParameters);

		boolean isFlag = resultTradePay.getCode().equals(EErrorCode.Success);
		if (isFlag) {
			resultMessage.setCode(EErrorCode.Success);
			if(payChannel.equals(EPayChannel.Alipay_NATIVE)){
				resultMessage.setMessage(resultTradePay.getQr_code());
			}else {
				resultMessage.setMessage(resultTradePay.getForm());
			}
			return resultMessage;
		} else {
			resultMessage.setCode(EErrorCode.Error);
			resultMessage.setMessage(resultTradePay.getMessage());
			return resultMessage;
		}

	}

	/**
	 * 订单号
	 * 
	 * @return 返回20位订单号
	 */
	public static String getOrderNoByCharge() {

		int hashCodeV = UUID.randomUUID().toString().hashCode();
		if (hashCodeV < 0) {// 有可能是负数
			hashCodeV = -hashCodeV;
		}
		// 0 代表前面补充0
		// 4 代表长度为4
		// d 代表参数为正数型
		return "D" + DateUtils.formatDate(DateUtils.now(), "yyyyMMdd") + String.format("%011d", hashCodeV);
		// 1+8+11
	}

}
