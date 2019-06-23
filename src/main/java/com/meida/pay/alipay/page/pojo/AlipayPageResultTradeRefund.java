package com.meida.pay.alipay.page.pojo;

import com.meida.common.util.constant.EErrorCode;

public class AlipayPageResultTradeRefund {
	public AlipayPageResultTradeRefund()
    {
		code = EErrorCode.Error;
		message = "操作失败.";
    }
    private String code ;
    private String message;
    private String trade_no;
    private String out_trade_no;
    private String buyer_logon_id;
    private String fund_change;
    private String refund_fee;
    private String refund_currency;
    private String gmt_refund_pay;
    private String refund_detail_item_list;
    private String store_name;
    private String buyer_user_id;
    private String refund_preset_paytool_list;
    private String refund_charge_amount;
    private String refund_settlement_id;
    private String present_refund_buyer_amount;
    private String present_refund_discount_amount;
    private String present_refund_mdiscount_amount;

}
