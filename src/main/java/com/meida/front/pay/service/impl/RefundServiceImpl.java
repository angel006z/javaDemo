package com.meida.front.pay.service.impl;

import com.meida.base.domain.vo.ResultMessage;
import com.meida.common.util.DateUtils;
import com.meida.common.util.StringUtils;
import com.meida.common.util.constant.EErrorCode;
import com.meida.front.pay.dao.inter.IAlipayNotifyDao;
import com.meida.front.pay.dao.inter.IAlipayReturnDao;
import com.meida.front.pay.dao.inter.IFundInDao;
import com.meida.front.pay.domain.dto.OriginalRefundDto;
import com.meida.front.pay.domain.po.AlipayNotify;
import com.meida.front.pay.domain.po.AlipayReturn;
import com.meida.front.pay.domain.po.FundIn;
import com.meida.front.pay.service.inter.IRefundService;
import com.meida.pay.pojo.EPayType;
import com.meida.pay.pojo.ParametersTradeRefund;
import com.meida.pay.pojo.ResultTradeRefund;
import com.meida.pay.service.inter.ITradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RefundServiceImpl implements IRefundService {

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
        if(queryFundIn.getMemberId()!=originalRefundDto.getRefundMemberId()){
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
        //


        if(inWay.equals(EPayType.Alipay)){
            ParametersTradeRefund builderParameters =new ParametersTradeRefund();
            builderParameters.setPayType(inWay);
            builderParameters.setOut_trade_no(queryFundIn.getOrderNo());
            builderParameters.setTrade_no(trade_no);
            builderParameters.setRefund_amount(queryFundIn.getInMoney());
            builderParameters.setRefund_reason(originalRefundDto.getRefundReason());
            ResultTradeRefund resultTradeRefund = tradeService.tradeRefund(builderParameters);
            resultMessage.setCode(resultTradeRefund.getCode());
            resultMessage.setMessage(resultTradeRefund.getMessage());
            return resultMessage;
        }else {
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("该订单号不知道退款方式");
            return resultMessage;
        }

    }

}
