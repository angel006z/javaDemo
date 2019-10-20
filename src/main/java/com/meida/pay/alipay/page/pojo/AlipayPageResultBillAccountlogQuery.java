package com.meida.pay.alipay.page.pojo;

import com.meida.common.constant.EErrorCode;

import java.util.List;

public class AlipayPageResultBillAccountlogQuery {
    public AlipayPageResultBillAccountlogQuery() {
        code = EErrorCode.Error;
        message = "操作失败.";
    }

    private String code;
    private String message;
    private String page_no;
    private String page_size;
    private String total_size;
    private List<AccountLogItemResult> listAccountLogItemResult;

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

    public String getPage_no() {
        return page_no;
    }

    public void setPage_no(String page_no) {
        this.page_no = page_no;
    }

    public String getPage_size() {
        return page_size;
    }

    public void setPage_size(String page_size) {
        this.page_size = page_size;
    }

    public String getTotal_size() {
        return total_size;
    }

    public void setTotal_size(String total_size) {
        this.total_size = total_size;
    }

    public List<AccountLogItemResult> getListAccountLogItemResult() {
        return listAccountLogItemResult;
    }

    public void setListAccountLogItemResult(List<AccountLogItemResult> listAccountLogItemResult) {
        this.listAccountLogItemResult = listAccountLogItemResult;
    }
}
