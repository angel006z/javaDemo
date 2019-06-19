package com.meida.pay.weixin.wxnative.pojo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.meida.common.util.DateUtils;
import com.meida.common.util.StringUtils;

public class ParametersTradePay extends ParametersBuilder {
	public ParametersTradePay() {		
        fee_type = "CNY";
        Date nowTime=DateUtils.now();
        time_start =DateUtils.formatDate(nowTime,DateUtils.DEFAULT_SECOND) ;
        time_expire = DateUtils.formatDate(DateUtils.addHours(nowTime, 2),DateUtils.DEFAULT_SECOND) ;           
//        notify_url = WxPayAPI.WxPayConfig.NOTIFY_URL;
        trade_type = "NATIVE";
//        spbill_create_ip = WxPayAPI.WxPayConfig.IP;
	}

	/**
	 * 商品描述
	 */
	private String body;

	/**
	 * 商品详情
	 */
	private String detail;

	/**
	 * 附加数据
	 */
	private String attach;

	/**
	 * 商户订单号
	 */
	private String out_trade_no;

	/**
	 * 货币类型
	 */
	private String fee_type;

	/**
	 * 总金额（单位分）
	 */
	private long total_fee;

	/**
	 * 终端IP
	 */
	private String spbill_create_ip;

	/**
	 * 交易起始时间
	 */
	private String time_start;

	/**
	 * 交易结束时间
	 */
	private String time_expire;

	/**
	 * 商品标记
	 */
	private String goods_tag;

	/**
	 * 通知地址
	 */
	private String notify_url;
	/**
	 * 交易类型
	 */
	private String trade_type;

	/**
	 * 商品ID
	 */
	private String product_id;

	/**
	 * 指定支付方式
	 */
	private String limit_pay;

	/**
	 * 用户标识
	 */
	private String openid;

	@Override
	public boolean Validate() {
		if (StringUtils.isEmpty(body)) {
			return false;
		}
		if (body.length() > 128) {
			return false;
		}
		if (!StringUtils.isEmpty(detail)) {
			if (detail.length() > 6000) {
				return false;
			}
		}
		if (!StringUtils.isEmpty(attach)) {
			if (attach.length() > 127) {
				return false;
			}
		}
		if (StringUtils.isEmpty(out_trade_no)) {
			return false;
		}
		if (out_trade_no.length() > 32) {
			return false;
		}
		if (total_fee < 1 || total_fee > 100000000) {
			return false;
		}
		if (StringUtils.isEmpty(spbill_create_ip)) {
			return false;
		}
		if (spbill_create_ip.length() > 16) {
			return false;
		}
		if (!StringUtils.isEmpty(goods_tag)) {
			if (goods_tag.length() > 32) {
				return false;
			}
		}
		if (StringUtils.isEmpty(notify_url)) {
			return false;
		}
		if (notify_url.length() > 256) {
			return false;
		}
		if (StringUtils.isEmpty(trade_type)) {
			return false;
		}
		if (trade_type.length() > 16) {
			return false;
		}
		if (!StringUtils.isEmpty(product_id)) {
			if (product_id.length() > 32) {
				return false;
			}
		}
		if (!StringUtils.isEmpty(limit_pay)) {
			if (limit_pay.length() > 32) {
				return false;
			}
		}
		if (!StringUtils.isEmpty(openid)) {
			if (openid.length() > 128) {
				return false;
			}
		}

		// if (trade_type == "NATIVE") {
		// if (string.IsNullOrEmpty(product_id)) {
		// return false;
		// }
		// }
		return true;
	}

//    public HashMap<String ,String> GetParameters()
//    {
//        var parameters = new HashMap<String ,String>();
//        
//        return parameters;
//    }
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	public long getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(long total_fee) {
		this.total_fee = total_fee;
	}

	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}

	public String getTime_start() {
		return time_start;
	}

	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}

	public String getTime_expire() {
		return time_expire;
	}

	public void setTime_expire(String time_expire) {
		this.time_expire = time_expire;
	}

	public String getGoods_tag() {
		return goods_tag;
	}

	public void setGoods_tag(String goods_tag) {
		this.goods_tag = goods_tag;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getLimit_pay() {
		return limit_pay;
	}

	public void setLimit_pay(String limit_pay) {
		this.limit_pay = limit_pay;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

}
