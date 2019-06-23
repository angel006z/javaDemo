package com.meida.front.pay.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meida.base.domain.vo.ResultMessage;
import com.meida.common.util.DateUtils;
import com.meida.common.util.constant.EErrorCode;
import com.meida.front.pay.domain.dto.ChargeDto;
import com.meida.front.pay.domain.po.MemberFundCharge;
import com.meida.front.pay.service.inter.IMemberFundChargeService;
import com.meida.front.pay.service.inter.IPayService;
import com.meida.pay.pojo.ParametersTradePay;
import com.meida.pay.pojo.ResultTradePay;
import com.meida.pay.service.inter.ITradeService;

@Service
public class PayServiceImpl implements IPayService {
	@Autowired
	private ITradeService tradeService;

	@Autowired
	private IMemberFundChargeService memberFundChargeService;

	@Override
	public ResultMessage charge(ChargeDto chargeDto) {
		Date nowTime = DateUtils.now();
		ResultMessage resultMessage = new ResultMessage();
		String orderNo = getOrderNoByCharge();
		if (memberFundChargeService.isExistOrderNo(orderNo)) {
			resultMessage.setCode(EErrorCode.Error);
			resultMessage.setMessage("该订单号已存在，请重新充值");
			return resultMessage;
		}

		BigDecimal total_fee = chargeDto.getTotal_fee();
		if (total_fee.signum() <= 0) {
			resultMessage.setCode(EErrorCode.Error);
			resultMessage.setMessage("充值金额必需大于0，请重新充值");
			return resultMessage;
		}

		Long memberId = 1l;
		// 系统生成订单信息
		MemberFundCharge memberFundCharge = new MemberFundCharge();
		memberFundCharge.setMemberFundChargeId(2l);
		memberFundCharge.setMemberId(memberId);
		memberFundCharge.setOrderNo(orderNo);
		memberFundCharge.setChargeMoney(total_fee);
		memberFundCharge.setChargeDate(nowTime);
		memberFundCharge.setPayType(chargeDto.getPayType());
		memberFundCharge.setPayChannel(chargeDto.getPayChannel());
		memberFundCharge.setIsPay("no");
		memberFundCharge.setCreateDate(nowTime);
		memberFundCharge.setOperateDate(nowTime);
		memberFundCharge.setIsValid(1);
		memberFundCharge.setRemark(String.format("充值，产品订单号：[%s],日期：[%s]", orderNo,
				DateUtils.formatDate(nowTime, "yyyy-MM-dd HH:mm:ss.SSS")));
		boolean isMfc = memberFundChargeService.addOrUpdate(memberFundCharge, true);
		if (isMfc == false) {
			resultMessage.setCode(EErrorCode.Error);
			resultMessage.setMessage("系统产生订单错误，请重新充值");
			return resultMessage;
		}

		// 调用充值接口
		ParametersTradePay builderParameters = new ParametersTradePay();
		builderParameters.setPayType(chargeDto.getPayType());
		builderParameters.setPayChannel(chargeDto.getPayChannel());
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
			resultMessage.setMessage(resultTradePay.getForm());
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
