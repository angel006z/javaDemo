package com.meida.front.pay.service.impl;

import com.meida.base.vo.ResultMessage;
import com.meida.common.util.DateUtils;
import com.meida.common.util.StringUtils;
import com.meida.common.util.constant.EErrorCode;
import com.meida.common.util.constant.ESystemStatus;
import com.meida.front.pay.dao.inter.*;
import com.meida.front.pay.dto.OriginalRefundDto;
import com.meida.front.pay.po.*;
import com.meida.front.pay.service.inter.IRefundService;
import com.meida.pay.pojo.EPayType;
import com.meida.pay.pojo.ParametersTradeRefund;
import com.meida.pay.pojo.ResultTradeRefund;
import com.meida.pay.service.inter.ITradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Service
public class RefundServiceImpl implements IRefundService {
    @Autowired
    private IFundAmountDao fundAmountDao;

    @Autowired
    private IFundOutDao fundOutDao;

    @Autowired
    private IFundInDao fundInDao;

    @Autowired
    private IAlipayReturnDao alipayReturnDao;

    @Autowired
    private IAlipayNotifyDao alipayNotifyDao;

    @Autowired
    private ITradeService tradeService;

    @Override
    public ResultMessage originalRefund(OriginalRefundDto originalRefundDto) {
        Date nowTime = DateUtils.now();
        ResultMessage resultMessage = new ResultMessage();
        FundIn queryFundIn = fundInDao.getObjectByOrderNo(originalRefundDto.getOrderNo());
        if(queryFundIn==null){
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("该订单号不存在");
            return resultMessage;
        }
        long refundMemberId = originalRefundDto.getRefundMemberId();
        if(queryFundIn.getMemberId()!=refundMemberId){
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("该订单号和退款人不一致");
            return resultMessage;
        }
        String trade_no ="";
        AlipayReturn queryAlipayReturn = alipayReturnDao.getObjectByOrderNo(originalRefundDto.getOrderNo());
        if(queryAlipayReturn!=null){
            trade_no =queryAlipayReturn.getTrade_no();
        }
        if(StringUtils.isEmpty(trade_no)) {
            AlipayNotify queryAlipayNotify = alipayNotifyDao.getObjectByOrderNo(originalRefundDto.getOrderNo());
            if(queryAlipayNotify!=null){
                trade_no =queryAlipayNotify.getTrade_no();
            }
        }
        if(StringUtils.isEmpty(trade_no)) {
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("未查找到支付交易号");
            return resultMessage;
        }
        String inWay= queryFundIn.getInWay();
        BigDecimal refund_amount = queryFundIn.getInMoney();
        //退款
        Refund refund =new Refund();
        refund.setRefundNo(getNoByRefund());
        refund.setOrderNo(queryFundIn.getOrderNo());
        refund.setTradeNo(trade_no);
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

        boolean isFlagFundAmount=false;
        FundAmount queryFundAmount = fundAmountDao.getObjectByMemberId(refundMemberId);
        if(queryFundAmount!=null){
            if(queryFundAmount.getTotalMoney().multiply(new BigDecimal(100)).longValue()<refund_amount.multiply(new BigDecimal(100)).longValue()){
                resultMessage.setCode(EErrorCode.Error);
                resultMessage.setMessage("该退款人总金额小于退款额，不能退款");
                return resultMessage;
            }
            FundAmount fundAmount = new FundAmount();
            fundAmount.setMemberId(refundMemberId);
            BigDecimal totalMoney = queryFundAmount.getTotalMoney().subtract(refund_amount);
            fundAmount.setTotalMoney(totalMoney);
            fundAmount.setUpdateDate(nowTime);
            fundAmount.setUpdateUserId("系统id");
            fundAmount.setUpdateUser("系统");
            isFlagFundAmount = fundAmountDao.updateByMemberId(fundAmount) > 0;
        }

        FundOut fundOut=new FundOut();
        fundOut.setMemberId(refundMemberId);
        fundOut.setOutMoney(refund_amount);
        fundOut.setOutDate(nowTime);
        fundOut.setOutWay(inWay);
        fundOut.setCreateDate(nowTime);
        fundOut.setCreateUserId("系统id");
        fundOut.setCreateUser("系统");
        fundOut.setUpdateDate(nowTime);
        fundOut.setUpdateUserId("系统id");
        fundOut.setUpdateUser("系统");
        fundOut.setIsValid(ESystemStatus.Valid);
        fundOut.setRemark("正常退款");
        fundOut.setSignature("待签名");
        boolean isFlagFundOut =fundOutDao.save(fundOut)>0;
        //第三方支付系统退款
        if(inWay.equals(EPayType.Alipay)){
            ParametersTradeRefund builderParameters =new ParametersTradeRefund();
            builderParameters.setPayType(inWay);
            builderParameters.setOut_trade_no(queryFundIn.getOrderNo());
            builderParameters.setTrade_no(trade_no);
            builderParameters.setRefund_amount(refund_amount);
            builderParameters.setRefund_reason(originalRefundDto.getRefundReason());
            ResultTradeRefund resultTradeRefund = tradeService.tradeRefund(builderParameters);
            resultMessage.setCode(resultTradeRefund.getCode());
            resultMessage.setMessage(resultTradeRefund.getMessage());
        }else if(inWay.equals(EPayType.Weixin)){
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("暂不支持微信退款");
        }else if(inWay.equals(EPayType.Banks)){
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("暂不支持银行退款");
        }else {
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("该订单号不知道退款方式");
        }
        //第三方支付退款失败
        if(!resultMessage.getCode().equals(EErrorCode.Success)){
            //回滚退款金额

        }
        return resultMessage;
    }

    /**
     * 退款单号
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
