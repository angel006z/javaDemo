package com.meida.common.util;

import com.alibaba.fastjson.JSON;

public class JsonUtils {
	
	public static String toJSONString(Object object) {
		return JSON.toJSONString(object);
	}
	
}
