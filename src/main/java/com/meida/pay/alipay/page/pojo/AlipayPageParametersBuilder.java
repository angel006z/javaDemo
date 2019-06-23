package com.meida.pay.alipay.page.pojo;

import com.meida.common.util.JsonUtils;

public abstract class AlipayPageParametersBuilder {

    public abstract boolean Validate();

    public String GetParameters() {
        return BuildJson();
    }

    private String BuildJson() {
        return JsonUtils.toJSONString(this);
    }
}
