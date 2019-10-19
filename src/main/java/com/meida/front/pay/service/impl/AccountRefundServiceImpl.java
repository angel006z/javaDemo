package com.meida.front.pay.service.impl;

import com.meida.base.vo.ResultMessage;
import com.meida.common.constant.EErrorCode;
import com.meida.common.util.StringUtils;
import com.meida.front.pay.dao.inter.*;
import com.meida.front.pay.po.*;
import com.meida.front.pay.dto.*;
import com.meida.front.base.dto.*;
import com.meida.front.pay.service.inter.AccountRefundService;

import com.meida.common.constant.EOperate;
import com.meida.common.constant.ESystemStatus;
import com.meida.common.util.DateUtils;

import com.meida.pay.pojo.EPayType;
import com.meida.pay.pojo.ParametersTradeRefund;
import com.meida.pay.pojo.ResultTradeRefund;
import com.meida.pay.service.inter.ITradeService;
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
    private AccountReceivableDao accountReceivableDao;

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


    @Override
    public ResultMessage originalRefund(OriginalRefundDto originalRefundDto) {
        Date nowTime = DateUtils.now();
        ResultMessage resultMessage = new ResultMessage();
        AccountReceivableInfo queryFundIn = accountReceivableDao.getObjectByOrderNo(originalRefundDto.getOrderNo());
        if (queryFundIn == null) {
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("该订单号不存在");
            return resultMessage;
        }
        long refundMemberId = originalRefundDto.getRefundMemberId();
        if (queryFundIn.getMemberId() != refundMemberId) {
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
        String inWay = queryFundIn.getReceivableType();
        BigDecimal refund_amount = queryFundIn.getReceivableAmount();
        //退款
        AccountRefundInfo refund = new AccountRefundInfo();
        refund.setRefundNo(getNoByRefund());
        refund.setOrderNo(queryFundIn.getOrderNo());
        refund.setTradeNo(tradeNo);
        refund.setRefundAmount(refund_amount);
        refund.setRefundDate(nowTime);
        refund.setMemberId(refundMemberId);
        refund.setRefundReason(originalRefundDto.getRefundReason());
        refund.setRefundStatus("no");
        refund.setCreateDate(nowTime);
        refund.setCreateUserId("系统id");
        refund.setCreateUser("系统");
        refund.setUpdateDate(nowTime);
        refund.setUpdateUserId("系统id");
        refund.setUpdateUser("系统");
        refund.setIsValid(ESystemStatus.Valid);
        refund.setRemark("正常退款");
        refund.setSignature("待签名");

        boolean isFlagFundAmount = false;
        AccountAmountInfo queryFundAmount = accountAmountDao.getObjectByMemberId(refundMemberId);
        if (queryFundAmount != null) {
            if (queryFundAmount.getTotalAmount().multiply(new BigDecimal(100)).longValue() < refund_amount.multiply(new BigDecimal(100)).longValue()) {
                resultMessage.setCode(EErrorCode.Error);
                resultMessage.setMessage("该退款人总金额小于退款额，不能退款");
                return resultMessage;
            }
            AccountAmountInfo fundAmount = new AccountAmountInfo();
            fundAmount.setMemberId(refundMemberId);
            BigDecimal totalMoney = queryFundAmount.getTotalAmount().subtract(refund_amount);
            fundAmount.setTotalAmount(totalMoney);
            fundAmount.setUpdateDate(nowTime);
            fundAmount.setUpdateUserId("系统id");
            fundAmount.setUpdateUser("系统");
            isFlagFundAmount = accountAmountDao.updateByMemberId(fundAmount) > 0;
        }

        AccountPayableInfo fundOut = new AccountPayableInfo();
        fundOut.setMemberId(refundMemberId);
        fundOut.setPayableAmount(refund_amount);
        fundOut.setPayableDate(nowTime);
        fundOut.setPayableType(inWay);
        fundOut.setCreateDate(nowTime);
        fundOut.setCreateUserId("系统id");
        fundOut.setCreateUser("系统");
        fundOut.setUpdateDate(nowTime);
        fundOut.setUpdateUserId("系统id");
        fundOut.setUpdateUser("系统");
        fundOut.setIsValid(ESystemStatus.Valid);
        fundOut.setRemark("正常退款");
        fundOut.setSignature("待签名");
        boolean isFlagFundOut = accountPayableDao.save(fundOut) > 0;
        //第三方支付系统退款
        if (inWay.equals(EPayType.Alipay)) {
            ParametersTradeRefund builderParameters = new ParametersTradeRefund();
            builderParameters.setPayType(inWay);
            builderParameters.setOut_trade_no(queryFundIn.getOrderNo());
            builderParameters.setTrade_no(tradeNo);
            builderParameters.setRefund_amount(refund_amount);
            builderParameters.setRefund_reason(originalRefundDto.getRefundReason());
            ResultTradeRefund resultTradeRefund = tradeService.tradeRefund(builderParameters);
            resultMessage.setCode(resultTradeRefund.getCode());
            resultMessage.setMessage(resultTradeRefund.getMessage());
        } else if (inWay.equals(EPayType.Weixin)) {
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("暂不支持微信退款");
        } else if (inWay.equals(EPayType.Banks)) {
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
