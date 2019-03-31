package com.meida.backend.basic.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.meida.backend.basic.domain.po.AuthRoleNodeButton;
import com.meida.backend.basic.domain.po.vAuthRoleNodeButton;
import com.meida.backend.basic.service.inter.IAuthRoleNodeButtonService;

public class AuthRoleNodeButtonServiceImpl implements IAuthRoleNodeButtonService {

	public List<vAuthRoleNodeButton> getListByUserIdNodeId(UUID userId, int nodeId, boolean isSuper) {
		List<vAuthRoleNodeButton> list =new ArrayList<vAuthRoleNodeButton>();
		
		if(true) {
			vAuthRoleNodeButton item=new vAuthRoleNodeButton();
			item.setBtnId("BtnAdd");
			item.setBtnName("新增");
			list.add(item);
		}
		
		if(true) {
			vAuthRoleNodeButton item=new vAuthRoleNodeButton();
			item.setBtnId("BtnUpdate");
			item.setBtnName("修改");
			list.add(item);
		}
		
		if(true) {
			vAuthRoleNodeButton item=new vAuthRoleNodeButton();
			item.setBtnId("BtnSubmit");
			item.setBtnName("提交");
			list.add(item);
		}
		
		if(true) {
			vAuthRoleNodeButton item=new vAuthRoleNodeButton();
			item.setBtnId("BtnBack");
			item.setBtnName("返回");
			list.add(item);
		}
		
		return list;
	}

	@Override
	public boolean isAuthRoleNodeButton(UUID iUSERID, int iCurrentPageNodeId, int iCurrentButtonId, boolean isSuper) {
		// TODO Auto-generated method stub
		return true;
	}
}
