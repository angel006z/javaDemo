package com.meida.paysdk.pojo;

import com.meida.common.util.StringUtils;

public class ParametersTradeQuery extends ParametersBuilder {
	/**
	 * 在线支付类型
	 */
	private String payType;
	/**
	 * 详细支付方式
	 */
	private String payChannel;
	/**
	 * 订单号
	 */
	private String out_trade_no;
	
	/**
	 * 交易号（支付宝、微信、银行）
	 */
	private String trade_no;
	
	@Override
	public boolean Validate() {
		if(StringUtils.isEmpty(out_trade_no))
		{
			return false;
		}
		if(StringUtils.isEmpty(trade_no))
		{
			return false;
		}
		return true;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}

}
