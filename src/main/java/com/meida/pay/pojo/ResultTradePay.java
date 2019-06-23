package com.meida.pay.pojo;

import com.meida.common.util.constant.EErrorCode;

public class ResultTradePay {
	public ResultTradePay()
    {
		code = EErrorCode.Error;
		message = "操作失败.";
		form = "";
    }
    private String code ;
    private String message;
    private String form;
	private String out_trade_no;
	private String qr_code;

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getQr_code() {
		return qr_code;
	}

	public void setQr_code(String qr_code) {
		this.qr_code = qr_code;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getForm() {
		return form;
	}
	public void setForm(String form) {
		this.form = form;
	}
}
