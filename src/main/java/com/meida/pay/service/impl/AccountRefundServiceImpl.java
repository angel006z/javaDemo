package com.meida.pay.service.impl;

import com.meida.base.vo.ResultMessage;
import com.meida.common.constant.*;
import com.meida.common.util.StringUtils;
import com.meida.basefront.dto.*;
import com.meida.pay.dao.inter.*;
import com.meida.pay.dto.AccountRefundListDto;
import com.meida.pay.dto.AccountRefundSubmitDto;
import com.meida.pay.dto.OriginalRefundDto;
import com.meida.pay.po.*;
import com.meida.pay.service.inter.AccountHistoryService;
import com.meida.pay.service.inter.AccountPayableService;
import com.meida.pay.service.inter.AccountRefundService;

import com.meida.common.util.DateUtils;

import com.meida.paysdk.pojo.EPayType;
import com.meida.paysdk.pojo.ParametersTradeRefund;
import com.meida.paysdk.pojo.ResultTradeRefund;
import com.meida.paysdk.service.inter.ITradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * AccountRefund业务实现类
 *
 * @author BING
 * @date 2019-10-19 15:27:58
 */
@Service("accountRefundService")
public class AccountRefundServiceImpl implements AccountRefundService {

    @Autowired
    private AccountRefundDao accountRefundDao;

    @Autowired
    private AccountAmountDao accountAmountDao;

    @Autowired
    private AccountPayableDao accountPayableDao;

    @Autowired
    private AccountPayableService accountPayableService;

    @Autowired
    private AccountHistoryDao accountHistoryDao;

    @Autowired
    private AccountReceivableDao accountReceivableDao;

    @Autowired
    private AccountHistoryService accountHistoryService;

    @Autowired
    private AlipayReturnDao alipayReturnDao;

    @Autowired
    private AlipayNotifyDao alipayNotifyDao;

    @Autowired
    private ITradeService tradeService;

    @Override
    public AccountRefundInfo getObjectById(Serializable id) {
        return accountRefundDao.getObjectById(id);
    }

    @Override
    public List<AccountRefundInfo> getListPage(AccountRefundListDto whereItemDto) {
        whereItemDto.getPagination().setPageIndex((whereItemDto.getPagination().getCurrentPage() - 1) * whereItemDto.getPagination().getPageSize());
        List<AccountRefundInfo> list = accountRefundDao.getListPage(whereItemDto);
        long totalRecord = accountRefundDao.getTotalRecord(whereItemDto);
        whereItemDto.getPagination().setTotalRecord(totalRecord);
        return list;
    }

    @Override
    public List<AccountRefundInfo> getListByAll() {
        return accountRefundDao.getListByAll();
    }

    @Override
    public List<AccountRefundInfo> getListByValid() {
        return accountRefundDao.getListByValid();
    }

    @Override
    public boolean submit(AccountRefundSubmitDto submitDto) {
        Date nowTime = DateUtils.now();
        AccountRefundInfo item = submitDto.getItem();
        CurrentMemberDto currentMemberDto = submitDto.getCurrentMemberDto();
        if (submitDto.getOperate().equals(EOperate.ADD)) {
            item.setIsValid(ESystemStatus.Valid);
            item.setCreateDate(nowTime);
            item.setCreateUserId(currentMemberDto.getMemberId().toString());
            item.setCreateUser(currentMemberDto.getAccount());
            item.setUpdateDate(nowTime);
            item.setUpdateUserId(currentMemberDto.getMemberId().toString());
            item.setUpdateUser(currentMemberDto.getAccount());
            return accountRefundDao.save(item) > 0;
        } else {
            item.setUpdateDate(nowTime);
            item.setUpdateUserId(currentMemberDto.getMemberId().toString());
            item.setUpdateUser(currentMemberDto.getAccount());
            return accountRefundDao.update(item) > 0;
        }
    }

    @Override
    public boolean delete(DeleteDto deleteDto) {
        boolean isFlag = accountRefundDao.logicDelete(deleteDto) > 0;
        return isFlag;
    }

    /**
     * 原路退款
     *
     * @param originalRefundDto
     * @return
     */
    @Override
    public ResultMessage originalRefund(OriginalRefundDto originalRefundDto) {
        Date nowTime = DateUtils.now();
        ResultMessage resultMessage = new ResultMessage();
        AccountReceivableInfo queryAccountReceivable = accountReceivableDao.getObjectByOrderNo(originalRefundDto.getOrderNo());
        if (queryAccountReceivable == null) {
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("该订单号不存在");
            return resultMessage;
        }
        long refundMemberId = originalRefundDto.getRefundMemberId();
        if (queryAccountReceivable.getMemberId() != refundMemberId) {
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("该订单号和退款人不一致");
            return resultMessage;
        }
        String tradeNo = "";
        AlipayReturnInfo queryAlipayReturn = alipayReturnDao.getObjectByOrderNo(originalRefundDto.getOrderNo());
        if (queryAlipayReturn != null) {
            tradeNo = queryAlipayReturn.getTradeNo();
        }
        if (StringUtils.isEmpty(tradeNo)) {
            AlipayNotifyInfo queryAlipayNotify = alipayNotifyDao.getObjectByOrderNo(originalRefundDto.getOrderNo());
            if (queryAlipayNotify != null) {
                tradeNo = queryAlipayNotify.getTradeNo();
            }
        }
        if (StringUtils.isEmpty(tradeNo)) {
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("未查找到支付交易号");
            return resultMessage;
        }
        String payType = queryAccountReceivable.getReceivableType();
        String payChannel = queryAccountReceivable.getReceivableChannel();
        BigDecimal refundAmount = queryAccountReceivable.getReceivableAmount();
        //退款
        String refundNo = getNoByRefund();//退款订单号
        AccountRefundInfo accountRefund = new AccountRefundInfo();
        accountRefund.setRefundNo(refundNo);
        accountRefund.setOrderNo(queryAccountReceivable.getOrderNo());
        accountRefund.setTradeNo(tradeNo);
        accountRefund.setRefundAmount(refundAmount);
        accountRefund.setRefundDate(nowTime);
        accountRefund.setMemberId(refundMemberId);
        accountRefund.setRefundReason(originalRefundDto.getRefundReason());
        accountRefund.setRefundStatus(ERechargeStatus.YES);
        accountRefund.setCreateDate(nowTime);
        accountRefund.setCreateUserId("系统id");
        accountRefund.setCreateUser("系统");
        accountRefund.setUpdateDate(nowTime);
        accountRefund.setUpdateUserId("系统id");
        accountRefund.setUpdateUser("系统");
        accountRefund.setIsValid(ESystemStatus.Valid);
        accountRefund.setRemark("正常退款");
        accountRefund.setSignature("待签名");
        boolean isFlagAccountRefund = accountRefundDao.save(accountRefund) > 0;

        //修改总金额
        boolean isFlagAccountAmount = false;
        AccountAmountInfo queryAccountAmount = accountAmountDao.getObjectByMemberId(refundMemberId);
        if (queryAccountAmount != null) {
            if (queryAccountAmount.getTotalAmount().multiply(new BigDecimal(100)).longValue() < refundAmount.multiply(new BigDecimal(100)).longValue()) {
                resultMessage.setCode(EErrorCode.Error);
                resultMessage.setMessage("该退款人总金额小于退款额，不能退款");
                return resultMessage;
            }
            AccountAmountInfo accountAmount = new AccountAmountInfo();
            accountAmount.setMemberId(refundMemberId);
            BigDecimal totalMoney = queryAccountAmount.getTotalAmount().subtract(refundAmount);
            accountAmount.setTotalAmount(totalMoney);
            accountAmount.setUpdateDate(nowTime);
            accountAmount.setUpdateUserId("系统id");
            accountAmount.setUpdateUser("系统");
            isFlagAccountAmount = accountAmountDao.updateByMemberId(accountAmount) > 0;
        }
        //出账（付款）记录
        AccountPayableInfo accountPayable = new AccountPayableInfo();
        accountPayable.setPayableNo(accountPayableService.getPayableNoByAccountPayable());
        accountPayable.setMemberId(refundMemberId);
        accountPayable.setOrderNo(refundNo);
        accountPayable.setPayableAmount(refundAmount);
        accountPayable.setPayableDate(nowTime);
        accountPayable.setPayableReason(originalRefundDto.getRefundReason());
        accountPayable.setPayableType(payType);
        accountPayable.setPayableChannel(payChannel);
        accountPayable.setCreateDate(nowTime);
        accountPayable.setCreateUserId("系统id");
        accountPayable.setCreateUser("系统");
        accountPayable.setUpdateDate(nowTime);
        accountPayable.setUpdateUserId("系统id");
        accountPayable.setUpdateUser("系统");
        accountPayable.setIsValid(ESystemStatus.Valid);
        accountPayable.setRemark("正常退款");
        accountPayable.setSignature("待签名");
        boolean isFlagFundOut = accountPayableDao.save(accountPayable) > 0;

        //账单记录（出账、付款）
        AccountHistoryInfo accountHistory = new AccountHistoryInfo();
        accountHistory.setInOutNo(accountHistoryService.getInOutNoByAccountHistory());
        accountHistory.setMemberId(refundMemberId);
        accountHistory.setOrderNo(refundNo);
        accountHistory.setInOutAmount(refundAmount);
        accountHistory.setInOutDate(nowTime);
        accountHistory.setInOutType(payType);
        accountHistory.setInOutChannel(payChannel);
        accountHistory.setInOutStatus("yes");
        accountHistory.setAccountHistoryType(EAccountHistoryType.OUT);
        accountHistory.setCreateDate(nowTime);
        accountHistory.setCreateUserId("系统id");
        accountHistory.setCreateUser("系统");
        accountHistory.setUpdateDate(nowTime);
        accountHistory.setUpdateUserId("系统id");
        accountHistory.setUpdateUser("系统");
        accountHistory.setIsValid(ESystemStatus.Valid);
        accountHistory.setRemark("系统自动记录");
        accountHistory.setSignature("待签名");
        boolean isFlagAccountHistory = accountHistoryDao.save(accountHistory) > 0;

        //第三方支付系统退款
        if (payType.equals(EPayType.Alipay)) {
            ParametersTradeRefund builderParameters = new ParametersTradeRefund();
            builderParameters.setPayType(payType);
            builderParameters.setOut_trade_no(queryAccountReceivable.getOrderNo());
            builderParameters.setTrade_no(tradeNo);
            builderParameters.setRefund_amount(refundAmount);
            builderParameters.setRefund_reason(originalRefundDto.getRefundReason());
            ResultTradeRefund resultTradeRefund = tradeService.tradeRefund(builderParameters);
            resultMessage.setCode(resultTradeRefund.getCode());
            resultMessage.setMessage(resultTradeRefund.getMessage());
        } else if (payType.equals(EPayType.Weixin)) {
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("暂不支持微信退款");
        } else if (payType.equals(EPayType.Banks)) {
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("暂不支持银行退款");
        } else {
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("该订单号不知道退款方式");
        }
        //第三方支付退款失败
        if (!resultMessage.getCode().equals(EErrorCode.Success)) {
            //回滚退款金额

        }
        return resultMessage;
    }

    /**
     * 退款单号
     *
     * @return 返回20位订单号
     */
    public String getNoByRefund() {
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {// 有可能是负数
            hashCodeV = -hashCodeV;
        }
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        return "T" + DateUtils.formatDate(DateUtils.now(), "yyyyMMdd") + String.format("%011d", hashCodeV);
        // 1+8+11
    }
}
