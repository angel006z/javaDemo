package com.meida.paysdk.alipay.page.pojo;

import com.meida.common.util.StringUtils;

import java.math.BigDecimal;

public class AlipayPageParametersTradePay extends AlipayPageParametersBuilder {
    public AlipayPageParametersTradePay() {
        product_code = "FAST_INSTANT_TRADE_PAY";
    }

    @Override
    public boolean Validate() {
        // 支付宝参数验证
        if (StringUtils.isEmpty(out_trade_no)) {
            return false;
        }
        if (!StringUtils.isEmpty(out_trade_no)) {
            if (out_trade_no.length() > 64) {
                return false;
            }
        }
        if (!product_code.equals("FAST_INSTANT_TRADE_PAY")) {
            return false;
        }

//        if (total_amount < 0.01 || total_amount > 1000000)
//        {
//            return false;
//        }

        if (StringUtils.isEmpty(subject)) {
            return false;
        }
        if (!StringUtils.isEmpty(subject)) {
            if (subject.length() > 256) {
                return false;
            }
        }

        if (!StringUtils.isEmpty(body)) {
            if (body.length() > 128) {
                return false;
            }
        }


        return true;
    }

    /**
     * 商户订单号，64个字符以内、可包含字母、数字、下划线；需保证在商户端不重复
     */
    private String out_trade_no;
    /**
     * 销售产品码，与支付宝签约的产品码名称。 注：目前仅支持FAST_INSTANT_TRADE_PAY
     */
    private String product_code;

    /**
     * 订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
     */
    private BigDecimal total_amount;
    /**
     * 订单标题
     */
    private String subject;
    /**
     * 订单描述
     */
    private String body;

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public BigDecimal getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(BigDecimal total_amount) {
        this.total_amount = total_amount;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
