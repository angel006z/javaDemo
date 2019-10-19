package com.meida.backend.base.util;

import com.meida.backend.base.dto.CurrentUserDto;
import com.meida.backend.basic.vo.UserButtonPermissionVo;
import com.meida.common.util.SessionHelper;
import com.meida.common.util.StringUtils;
import com.meida.common.util.UUIDUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class BackendUtils {

	/**
	 * 获取当前登录用户信息
	 * @return
	 */
	public static CurrentUserDto getCurrentUserDto(){
		CurrentUserDto item = new CurrentUserDto();
		item.setUserId(UUID.randomUUID()+"");
		item.setUserName("test");
		item.setRealName("测试账号");
		item.setRoleId(UUID.randomUUID()+"");
		item.setRoleName("测试角色");
		item.setIsSuper(false);
		return item;
	}


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


	/**
	 * 用户拥有的该页面的权限按钮
	 * @param nodeId
	 * @param userId
	 * @param isSuper
	 * @return
	 */
	public static List<UserButtonPermissionVo> getOperateButton(int nodeId, String userId, boolean isSuper){
		List<UserButtonPermissionVo> listVo =new ArrayList<UserButtonPermissionVo>();
		if(true){
			UserButtonPermissionVo itemVo =new UserButtonPermissionVo();
			itemVo.setButtonId(1);
			itemVo.setBtnId("BtnAdd");
			itemVo.setBtnName("新增");
			listVo.add(itemVo);
		}
		if(true){
			UserButtonPermissionVo itemVo =new UserButtonPermissionVo();
			itemVo.setButtonId(2);
			itemVo.setBtnId("BtnUpdate");
			itemVo.setBtnName("修改");
			listVo.add(itemVo);
		}

		if(true){
			UserButtonPermissionVo itemVo =new UserButtonPermissionVo();
			itemVo.setButtonId(3);
			itemVo.setBtnId("BtnDelete");
			itemVo.setBtnName("删除");
			listVo.add(itemVo);
		}

		if(true){
			UserButtonPermissionVo itemVo =new UserButtonPermissionVo();
			itemVo.setButtonId(7);
			itemVo.setBtnId("BtnSubmit");
			itemVo.setBtnName("提交");
			listVo.add(itemVo);
		}
		if(true){
			UserButtonPermissionVo itemVo =new UserButtonPermissionVo();
			itemVo.setButtonId(8);
			itemVo.setBtnId("BtnBack");
			itemVo.setBtnName("返回");
			listVo.add(itemVo);
		}
		return listVo;
	}

}
