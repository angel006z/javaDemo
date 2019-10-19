package com.meida.pay.weixin.wxnative.pojo;

import com.meida.common.util.JsonUtils;

public abstract class WxnativeParametersBuilder {

    public abstract boolean Validate();

    public String GetParameters()
    {
         return BuildJson();           
    }

    private String BuildJson()
    {
    	return JsonUtils.toJSONString(this);
    }
}
