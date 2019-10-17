package com.meida.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Utits {
	public static UUID getCurrentUserId() {
		String userId= SessionHelper.getString("USERID");
		if(StringUtils.isEmpty(userId))
			return UUIDUtils.Empty;
		return UUID.fromString(userId);
	}
	
	public static boolean isSuper(){
		return true;
	}

	public static boolean isLogin() {		
		return getCurrentUserId()!=UUIDUtils.Empty;
	}

	

}
