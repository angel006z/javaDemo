package com.meida.front.pay.service.impl;

import java.util.UUID;

import org.apache.ibatis.scripting.xmltags.VarDeclSqlNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meida.base.domain.vo.ResultMessage;
import com.meida.common.util.DateUtils;
import com.meida.common.util.JsonUtils;
import com.meida.common.util.constant.EErrorCode;
import com.meida.front.pay.dto.ChargeDto;
import com.meida.front.pay.service.IPayService;
import com.meida.pay.pojo.EPayChannel;
import com.meida.pay.pojo.EPayType;
import com.meida.pay.pojo.ParametersTradePay;
import com.meida.pay.pojo.ResultTradePay;
import com.meida.pay.service.inter.ITradeService;

@Service
public class PayServiceImpl implements IPayService {
	@Autowired
	private ITradeService tradeService;

	@Override
	public ResultMessage charge(ChargeDto chargeDto) {
	  
		ResultMessage resultMessage = new ResultMessage();
		String orderNo =getOrderNoByCharge();
		String total_fee = chargeDto.getTotal_fee()+"";
				
		//系统后台产生订单
		
		
		
		
		ParametersTradePay builderParameters = new ParametersTradePay();
		builderParameters.setPayType(chargeDto.getPayType());
		builderParameters.setPayChannel(chargeDto.getPayChannel());
		builderParameters.setOut_trade_no(orderNo);
		builderParameters.setSubject("充值");
		builderParameters.setAttach("");
		builderParameters.setBody(String.format("产品订单号：[%s],日期：[%s]",orderNo,DateUtils.formatDate(DateUtils.now(), "yyyy-MM-dd HH:mm:ss.ff")));
		builderParameters.setTotal_fee(total_fee);
		ResultTradePay resultTradePay = tradeService.tradePay(builderParameters);
		
		boolean isFlag =resultTradePay.getCode().equals(EErrorCode.Success) ;
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
	 *  订单号
	 * @return 返回20位订单号
	 */
	public static String getOrderNoByCharge() {
        
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {//有可能是负数
            hashCodeV = -hashCodeV;
        }
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        return "D"+ DateUtils.formatDate(DateUtils.now(), "yyyyMMdd") + String.format("%011d", hashCodeV);
        //1+8+11
    }

}
