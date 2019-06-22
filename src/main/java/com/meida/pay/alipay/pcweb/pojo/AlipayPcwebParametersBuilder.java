package com.meida.pay.alipay.pcweb.pojo;

import com.meida.common.util.JsonUtils;

public abstract class AlipayPcwebParametersBuilder {

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
