package com.meida.paysdk.alipay.page.pojo;

import com.meida.common.constant.EErrorCode;

public class AlipayPageResultBillDownloadurlQuery {
    public AlipayPageResultBillDownloadurlQuery()
    {
        code = EErrorCode.Error;
        message = "操作失败.";
    }
    private String code ;
    private String message;
    /**
     * 账单下载地址链接，获取连接后30秒后未下载，链接地址失效。
     */
    private  String billDownloadUrl;
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

    public String getBillDownloadUrl() {
        return billDownloadUrl;
    }

    public void setBillDownloadUrl(String billDownloadUrl) {
        this.billDownloadUrl = billDownloadUrl;
    }
}
