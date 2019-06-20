package com.meida.front.pay.service.inter.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.ibatis.scripting.xmltags.VarDeclSqlNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meida.common.util.DateUtils;
import com.meida.common.util.StringUtils;
import com.meida.front.pay.dao.inter.IMemberFundAmountDao;
import com.meida.front.pay.dao.inter.IMemberFundChargeDao;
import com.meida.front.pay.dao.inter.IMemberFundInDao;
import com.meida.front.pay.domain.po.AlipayNotify;
import com.meida.front.pay.domain.po.MemberFundAmount;
import com.meida.front.pay.domain.po.MemberFundCharge;
import com.meida.front.pay.domain.po.MemberFundIn;
import com.meida.front.pay.service.inter.IAlipayNotifyService;
import com.meida.front.pay.service.inter.IMemberFundChargeService;

@Service
public class MemberFundChargeServiceImpl implements IMemberFundChargeService {
	@Autowired
	private IMemberFundChargeDao memberFundChargeDao;
	@Autowired
	private IMemberFundInDao memberFundInDao;
	@Autowired
	private IMemberFundAmountDao memberFundAmountDao;
	@Autowired
	private IAlipayNotifyService alipayNotifyService;

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
	public boolean handleAlipayTradeSuccess(AlipayNotify alipayNotify) {
		if (alipayNotify == null) {
			return false;
		}

		String orderNo = alipayNotify.getOut_trade_no();
		if (StringUtils.isEmpty(orderNo)) {
			return false;
		}

		if (alipayNotifyService.isExistOrderNo(orderNo)) {
			return false;
		}
		// 事务begin
		// 新增支付宝通知记录
		Boolean isFlagAlipayNotify = alipayNotifyService.addOrUpdate(alipayNotify, true);
		if (isFlagAlipayNotify == false) {
			return false;
		}
		Date nowTime = DateUtils.now();
		// 修改充值记录为已充值
		MemberFundCharge item = new MemberFundCharge();
		item.setOrderNo(orderNo);
		item.setIsPay("yes");
		item.setOperateDate(nowTime);
		boolean isFlagPay = memberFundChargeDao.updateByOrderNo(item) > 0;
		// 入账记录
		Long memberId = 1l;
		MemberFundIn memberFundIn = new MemberFundIn();
		memberFundIn.setMemberId(memberId);
		memberFundIn.setOrderNo(orderNo);
		memberFundIn.setInDate(nowTime);
		memberFundIn.setInMoney(alipayNotify.getTotal_amount());
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
			BigDecimal totalMoney = queryMemberFundAmount.getTotalMoney().add(alipayNotify.getTotal_amount());
			memberFundAmount.setTotalMoney(totalMoney);
			memberFundAmount.setOperateDate(nowTime);
			memberFundAmountDao.updateByMemberId(memberFundAmount);
		} else {
			memberFundAmount.setTotalMoney(alipayNotify.getTotal_amount());
			memberFundAmount.setCreateDate(nowTime);
			memberFundAmount.setOperateDate(nowTime);
			memberFundAmount.setIsValid(1);
			memberFundAmount.setRemark("");
			memberFundAmount.setSignature("");
			memberFundAmountDao.save(memberFundAmount);
		}
		// 事务end
		return true;
	}

}
