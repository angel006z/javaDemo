package com.meida.pay.alipay.pcweb.pojo;

import com.meida.common.util.StringUtils;

public class AlipayPcwebParametersTradeQuery extends AlipayPcwebParametersBuilder {

	private String out_trade_no;
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

}
