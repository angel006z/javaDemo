package com.meida.pay.alipay.page.pojo;

public class AlipayPageResultAccountLogItem {
    private String trans_dt;//	String	必填	20	入账时间	2019-01-01 00:00:00
    private String account_log_id;//	String	必填	256	支付宝账务流水号。对账使用，不脱敏	1***
    private String alipay_order_no;//	String	必填	255	支付宝订单号。对账使用，不脱敏	20190101***
    private String merchant_order_no;//	String	必填	255	商户订单号，创建支付宝交易时传入的信息。对账使用，不脱敏	TX***
    private String trans_amount;//	String	必填	20	金额	1000.00
    private String balance;//	String	必填	20	余额，仅供参考。由于架构原因，余额变动并不保证连续。也就是余额不一定等于上一笔余额减去当笔金额。但是会保证最终一致。	10000.00
    private String type;//	String	必填	255	账务记录的类型，仅供参考	交易
    private String other_account;//	String	必填	255	对方账户	张*(a*@******.com)
    private String trans_memo;//	String	可选	1000	账务备注。由上游业务决定，不可依赖此字段进行对账	备注1
    private String direction;//	String	必填	20	收入/支出。表示收支。amount是正数，返回“收入”。amount是负数，返回“支出”	收入
    private String bill_source;//	String	可选	1000	业务账单来源，资金收支对应的上游业务订单数据来源，确认业务订单出处。此字段供商户对账使用，不脱敏。	商家中心
    private String biz_nos;//	String	可选	1000	业务订单号，资金收支相关的业务场景订单号明细，字母大写；M：平台交易主单号，S：平台交易子单号，O：业务系统单据号（如退款订单号）。此字段供商户对账使用，不脱敏。	M{330***}|S{330***}|O{192***}
    private String biz_orig_no;//	String	可选	1000	业务基础订单号，资金收支对应的原始业务订单唯一识别编号。此字段供商户对账使用，不脱敏。	330***
    private String biz_desc;//	String	可选	1000	业务描述，资金收支对应的详细业务场景信息。此字段供商户对账使用，不脱敏。	002***|交易退款

    public String getTrans_dt() {
        return trans_dt;
    }

    public void setTrans_dt(String trans_dt) {
        this.trans_dt = trans_dt;
    }

    public String getAccount_log_id() {
        return account_log_id;
    }

    public void setAccount_log_id(String account_log_id) {
        this.account_log_id = account_log_id;
    }

    public String getAlipay_order_no() {
        return alipay_order_no;
    }

    public void setAlipay_order_no(String alipay_order_no) {
        this.alipay_order_no = alipay_order_no;
    }

    public String getMerchant_order_no() {
        return merchant_order_no;
    }

    public void setMerchant_order_no(String merchant_order_no) {
        this.merchant_order_no = merchant_order_no;
    }

    public String getTrans_amount() {
        return trans_amount;
    }

    public void setTrans_amount(String trans_amount) {
        this.trans_amount = trans_amount;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOther_account() {
        return other_account;
    }

    public void setOther_account(String other_account) {
        this.other_account = other_account;
    }

    public String getTrans_memo() {
        return trans_memo;
    }

    public void setTrans_memo(String trans_memo) {
        this.trans_memo = trans_memo;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getBill_source() {
        return bill_source;
    }

    public void setBill_source(String bill_source) {
        this.bill_source = bill_source;
    }

    public String getBiz_nos() {
        return biz_nos;
    }

    public void setBiz_nos(String biz_nos) {
        this.biz_nos = biz_nos;
    }

    public String getBiz_orig_no() {
        return biz_orig_no;
    }

    public void setBiz_orig_no(String biz_orig_no) {
        this.biz_orig_no = biz_orig_no;
    }

    public String getBiz_desc() {
        return biz_desc;
    }

    public void setBiz_desc(String biz_desc) {
        this.biz_desc = biz_desc;
    }
}
