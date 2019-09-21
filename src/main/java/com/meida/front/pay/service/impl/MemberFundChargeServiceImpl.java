package com.meida.front.pay.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meida.base.domain.vo.ResultMessage;
import com.meida.common.util.DateUtils;
import com.meida.common.util.StringUtils;
import com.meida.common.util.constant.EErrorCode;
import com.meida.common.util.constant.ESystemStatus;
import com.meida.front.pay.dao.inter.IAlipayNotifyDao;
import com.meida.front.pay.dao.inter.IMemberFundAmountDao;
import com.meida.front.pay.dao.inter.IMemberFundChargeDao;
import com.meida.front.pay.dao.inter.IMemberFundInDao;
import com.meida.front.pay.domain.dto.AlipayNotifyParamDto;
import com.meida.front.pay.domain.dto.AlipayReturnParamDto;
import com.meida.front.pay.domain.po.AlipayNotify;
import com.meida.front.pay.domain.po.MemberFundAmount;
import com.meida.front.pay.domain.po.MemberFundCharge;
import com.meida.front.pay.domain.po.MemberFundIn;
import com.meida.front.pay.service.inter.IMemberFundChargeService;
import com.meida.pay.pojo.EPayType;
import com.meida.pay.pojo.ParametersTradeQuery;
import com.meida.pay.service.inter.ITradeService;

@Service
public class MemberFundChargeServiceImpl implements IMemberFundChargeService {
	@Autowired
	private IMemberFundChargeDao memberFundChargeDao;
	@Autowired
	private IMemberFundInDao memberFundInDao;
	@Autowired
	private IMemberFundAmountDao memberFundAmountDao;
	@Autowired
	private IAlipayNotifyDao alipayNotifyDao;
	@Autowired
	private ITradeService tradeService;

	@Override
	public boolean addOrUpdate(MemberFundCharge item, boolean isAdd) {
		if (isAdd) {
			return memberFundChargeDao.save(item) > 0;
		} else {
			return memberFundChargeDao.update(item) > 0;
		}
	}

	@Override
	public boolean isExistOrderNo(String orderNo) {
		return memberFundChargeDao.isExistOrderNo(orderNo) > 0;
	}

	@Override
	public MemberFundCharge getObjectByOrderNo(Serializable orderNo) {
		return memberFundChargeDao.getObjectByOrderNo(orderNo);
	}

	@Override
	public ResultMessage handleAlipayNotify(AlipayNotifyParamDto alipayNotifyParamDto) {
		ResultMessage resultMessage = new ResultMessage();
		if (alipayNotifyParamDto == null) {
			resultMessage.setCode(EErrorCode.Error);
			resultMessage.setMessage("通知接收参数为空");
			return resultMessage;
		}

		String trade_no = alipayNotifyParamDto.getTrade_no();
		if (StringUtils.isEmpty(trade_no)) {
			resultMessage.setCode(EErrorCode.Error);
			resultMessage.setMessage("支付宝交易号参数为空");
			return resultMessage;
		}

		String orderNo = alipayNotifyParamDto.getOut_trade_no();
		if (StringUtils.isEmpty(orderNo)) {
			resultMessage.setCode(EErrorCode.Error);
			resultMessage.setMessage("订单号参数为空");
			return resultMessage;
		}

//		String trade_status = alipayNotifyParamDto.getTrade_status();
//		if (StringUtils.isEmpty(trade_status)) {
//			resultMessage.setCode(EErrorCode.Error);
//			resultMessage.setMessage("支付宝交易状态参数为空");
//			return resultMessage;
//		}

		// 查询支付宝是否支付成功
//		Boolean isAlipayPaySuccess = trade_status.equals(AlipayTradeStatus.TRADE_SUCCESS)
//				|| trade_status.equals(AlipayTradeStatus.TRADE_FINISHED);		
		ParametersTradeQuery parametersTradeQuery = new ParametersTradeQuery();
		parametersTradeQuery.setPayType(EPayType.Alipay);
		parametersTradeQuery.setOut_trade_no(orderNo);
		parametersTradeQuery.setTrade_no(trade_no);
		ResultMessage tradeIsPaySuccess = tradeService.tradeIsPaySuccess(parametersTradeQuery);
		if (!tradeIsPaySuccess.getCode().equals(EErrorCode.Success)) {
			resultMessage.setCode(EErrorCode.Error);
			resultMessage.setMessage("支付宝交易号：" + trade_no + "业务订单号：" + orderNo + "未支付成功.");
			return resultMessage;
		}

		MemberFundCharge memberFundChargeItem = memberFundChargeDao.getObjectByOrderNo(orderNo);
		if (memberFundChargeItem == null) {
			resultMessage.setCode(EErrorCode.Error);
			resultMessage.setMessage("业务订单号：" + orderNo + "充值订单不存在");
			return resultMessage;
		}

		if (memberFundChargeItem.getIsPay().equals("yes")) {
			resultMessage.setCode(EErrorCode.Success);
			resultMessage.setMessage("业务订单号：" + orderNo + "充值订单状态已经是支付状态");
			return resultMessage;
		}

		Date nowTime = DateUtils.now();

		// 事务begin

		Boolean isExistAlipayNotify = alipayNotifyDao.isExistOrderNo(orderNo) > 0;
		if (isExistAlipayNotify == false) {
			// 新增支付宝通知记录
			AlipayNotify alipayNotify = new AlipayNotify();
			alipayNotify.setApp_id(alipayNotifyParamDto.getApp_id());
			alipayNotify.setAuth_app_id(alipayNotifyParamDto.getAuth_app_id());
			alipayNotify.setBody(alipayNotifyParamDto.getBody());
			alipayNotify.setBuyer_id(alipayNotifyParamDto.getBuyer_id());
			alipayNotify.setBuyer_pay_amount(alipayNotifyParamDto.getBuyer_pay_amount());
			alipayNotify.setCharset(alipayNotifyParamDto.getCharset());
			alipayNotify.setFund_bill_list(alipayNotifyParamDto.getFund_bill_list());
			alipayNotify.setGmt_close(alipayNotifyParamDto.getGmt_close());
			alipayNotify.setGmt_create(alipayNotifyParamDto.getGmt_create());
			alipayNotify.setGmt_payment(alipayNotifyParamDto.getGmt_payment());
			alipayNotify.setGmt_refund(alipayNotifyParamDto.getGmt_refund());
			alipayNotify.setInvoice_amount(alipayNotifyParamDto.getInvoice_amount());
			alipayNotify.setNotify_id(alipayNotifyParamDto.getNotify_id());
			alipayNotify.setNotify_time(alipayNotifyParamDto.getNotify_time());
			alipayNotify.setNotify_type(alipayNotifyParamDto.getNotify_type());
			alipayNotify.setOut_biz_no(alipayNotifyParamDto.getOut_biz_no());
			alipayNotify.setOut_trade_no(alipayNotifyParamDto.getOut_trade_no());
			alipayNotify.setPassback_params(alipayNotifyParamDto.getPassback_params());
			alipayNotify.setPoint_amount(alipayNotifyParamDto.getPoint_amount());
			alipayNotify.setReceipt_amount(alipayNotifyParamDto.getReceipt_amount());
			alipayNotify.setRefund_fee(alipayNotifyParamDto.getRefund_fee());
			alipayNotify.setSeller_id(alipayNotifyParamDto.getSeller_id());
			alipayNotify.setSign(alipayNotifyParamDto.getSign());
			alipayNotify.setSign_type(alipayNotifyParamDto.getSign_type());
			alipayNotify.setSubject(alipayNotifyParamDto.getSubject());
			alipayNotify.setTotal_amount(alipayNotifyParamDto.getTotal_amount());
			alipayNotify.setTrade_no(alipayNotifyParamDto.getTrade_no());
			alipayNotify.setTrade_status(alipayNotifyParamDto.getTrade_status());
			alipayNotify.setVersion(alipayNotifyParamDto.getVersion());
			alipayNotify.setVoucher_detail_list(alipayNotifyParamDto.getVoucher_detail_list());
			alipayNotify.setCreateDate(nowTime);
			alipayNotify.setOperateDate(nowTime);
			alipayNotify.setIsValid(ESystemStatus.Valid);
			alipayNotify.setRemark("");
			Boolean isFlagAlipayNotify = alipayNotifyDao.save(alipayNotify) > 0;
			if (isFlagAlipayNotify == false) {
				resultMessage.setCode(EErrorCode.Error);
				resultMessage.setMessage("新增支付宝通知记录错误");
				return resultMessage;
			}
		}

		// 修改充值记录为已充值
		MemberFundCharge memberFundCharge = new MemberFundCharge();
		memberFundCharge.setOrderNo(orderNo);
		memberFundCharge.setIsPay("yes");
		memberFundCharge.setOperateDate(nowTime);
		boolean isFlagPay = memberFundChargeDao.updateByOrderNo(memberFundCharge) > 0;
		// 入账记录
		Long memberId = 1l;
		MemberFundIn memberFundIn = new MemberFundIn();
		memberFundIn.setMemberId(memberId);
		memberFundIn.setOrderNo(orderNo);
		memberFundIn.setInDate(nowTime);
		memberFundIn.setInMoney(alipayNotifyParamDto.getTotal_amount());
		memberFundIn.setInWay("alipay");
		memberFundIn.setCreateDate(nowTime);
		memberFundIn.setOperateDate(nowTime);
		memberFundIn.setIsValid(1);
		memberFundIn.setRemark("");
		String signature = "";
		memberFundIn.setSignature(signature);
		memberFundInDao.save(memberFundIn);

		// 总资金
		MemberFundAmount memberFundAmount = new MemberFundAmount();

		MemberFundAmount queryMemberFundAmount = memberFundAmountDao.getObjectByMemberId(memberId);
		if (queryMemberFundAmount != null) {
			memberFundAmount.setMemberId(memberId);
			BigDecimal totalMoney = queryMemberFundAmount.getTotalMoney().add(alipayNotifyParamDto.getTotal_amount());
			memberFundAmount.setTotalMoney(totalMoney);
			memberFundAmount.setOperateDate(nowTime);
			boolean isMfa = memberFundAmountDao.updateByMemberId(memberFundAmount) > 0;
			System.out.println("Mfa:" + isMfa);
		} else {
			memberFundAmount.setMemberId(memberId);
			memberFundAmount.setTotalMoney(alipayNotifyParamDto.getTotal_amount());
			memberFundAmount.setCreateDate(nowTime);
			memberFundAmount.setOperateDate(nowTime);
			memberFundAmount.setIsValid(1);
			memberFundAmount.setRemark("");
			memberFundAmount.setSignature("");
			boolean isMfa = memberFundAmountDao.save(memberFundAmount) > 0;
			System.out.println("Mfa:" + isMfa);
		}
		// 事务end

		resultMessage.setCode(EErrorCode.Success);
		resultMessage.setMessage("操作成功");
		return resultMessage;
	}

	@Override
	public ResultMessage handleAlipayReturn(AlipayReturnParamDto alipayReturnParamDto) {
		ResultMessage resultMessage = new ResultMessage();
		if (alipayReturnParamDto == null) {
			resultMessage.setCode(EErrorCode.Error);
			resultMessage.setMessage("通知接收参数为空");
			return resultMessage;
		}

		String trade_no = alipayReturnParamDto.getTrade_no();
		if (StringUtils.isEmpty(trade_no)) {
			resultMessage.setCode(EErrorCode.Error);
			resultMessage.setMessage("支付宝交易号参数为空");
			return resultMessage;
		}

		String orderNo = alipayReturnParamDto.getOut_trade_no();
		if (StringUtils.isEmpty(orderNo)) {
			resultMessage.setCode(EErrorCode.Error);
			resultMessage.setMessage("订单号参数为空");
			return resultMessage;
		}

		// 查询支付宝是否支付成功
		ParametersTradeQuery parametersTradeQuery = new ParametersTradeQuery();
		parametersTradeQuery.setPayType(EPayType.Alipay);
		parametersTradeQuery.setOut_trade_no(orderNo);
		parametersTradeQuery.setTrade_no(trade_no);
		ResultMessage tradeIsPaySuccess = tradeService.tradeIsPaySuccess(parametersTradeQuery);
		if (!tradeIsPaySuccess.getCode().equals(EErrorCode.Success)) {
			resultMessage.setCode(EErrorCode.Error);
			resultMessage.setMessage("支付宝交易号：" + trade_no + "业务订单号：" + orderNo + "未支付成功.");
			return resultMessage;
		}

		MemberFundCharge memberFundChargeItem = memberFundChargeDao.getObjectByOrderNo(orderNo);
		if (memberFundChargeItem == null) {
			resultMessage.setCode(EErrorCode.Error);
			resultMessage.setMessage("业务订单号：" + orderNo + "充值订单不存在");
			return resultMessage;
		}

		if (memberFundChargeItem.getIsPay().equals("yes")) {
			resultMessage.setCode(EErrorCode.Success);
			resultMessage.setMessage("业务订单号：" + orderNo + "充值订单状态已经是支付状态");
			return resultMessage;
		}

		Date nowTime = DateUtils.now();

		// 事务begin

//		Boolean isExistAlipayNotify = alipayNotifyService.isExistOrderNo(orderNo);
//		if (isExistAlipayNotify == false) {
//			// 新增支付宝通知记录
//			AlipayReturn alipayReturn = new AlipayReturn();
//			alipayReturn.setCreateDate(nowTime);
//			alipayReturn.setOperateDate(nowTime);
//			alipayReturn.setIsValid(ESystemStatus.Valid);
//			alipayReturn.setRemark("");
//			Boolean isFlagAlipayNotify = alipayNotifyService.addOrUpdate(alipayNotify, true);
//			if (isFlagAlipayNotify == false) {
//				resultMessage.setCode(EErrorCode.Error);
//				resultMessage.setMessage("新增支付宝通知记录错误");
//				return resultMessage;
//			}
//		}

		// 修改充值记录为已充值
		MemberFundCharge memberFundCharge = new MemberFundCharge();
		memberFundCharge.setOrderNo(orderNo);
		memberFundCharge.setIsPay("yes");
		memberFundCharge.setOperateDate(nowTime);
		boolean isFlagPay = memberFundChargeDao.updateByOrderNo(memberFundCharge) > 0;
		// 入账记录
		Long memberId = 1l;
		MemberFundIn memberFundIn = new MemberFundIn();
		memberFundIn.setMemberId(memberId);
		memberFundIn.setOrderNo(orderNo);
		memberFundIn.setInDate(nowTime);
		memberFundIn.setInMoney(alipayReturnParamDto.getTotal_amount());
		memberFundIn.setInWay("alipay");
		memberFundIn.setCreateDate(nowTime);
		memberFundIn.setOperateDate(nowTime);
		memberFundIn.setIsValid(1);
		memberFundIn.setRemark("");
		String signature = "";
		memberFundIn.setSignature(signature);
		memberFundInDao.save(memberFundIn);

		// 总资金
		MemberFundAmount memberFundAmount = new MemberFundAmount();
		memberFundAmount.setMemberId(memberId);
		MemberFundAmount queryMemberFundAmount = memberFundAmountDao.getObjectByMemberId(memberId);
		if (queryMemberFundAmount != null) {
			BigDecimal totalMoney = queryMemberFundAmount.getTotalMoney().add(alipayReturnParamDto.getTotal_amount());
			memberFundAmount.setTotalMoney(totalMoney);
			memberFundAmount.setOperateDate(nowTime);
			memberFundAmountDao.updateByMemberId(memberFundAmount);
		} else {
			memberFundAmount.setTotalMoney(alipayReturnParamDto.getTotal_amount());
			memberFundAmount.setCreateDate(nowTime);
			memberFundAmount.setOperateDate(nowTime);
			memberFundAmount.setIsValid(1);
			memberFundAmount.setRemark("");
			memberFundAmount.setSignature("");
			memberFundAmountDao.save(memberFundAmount);
		}
		// 事务end

		resultMessage.setCode(EErrorCode.Success);
		resultMessage.setMessage("操作成功");
		return resultMessage;
	}

}
