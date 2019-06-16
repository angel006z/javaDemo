package com.meida.pay.alipay.pcweb.pojo;

import com.meida.common.util.JsonUtils;

public abstract class ParametersBuilder {

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
