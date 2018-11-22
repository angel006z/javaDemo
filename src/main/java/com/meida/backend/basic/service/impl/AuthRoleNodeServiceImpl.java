package com.meida.backend.basic.service.impl;

import java.util.UUID;

import com.meida.backend.basic.po.AuthRoleNode;
import com.meida.backend.basic.service.inter.IAuthNodeService;
import com.meida.base.service.impl.BaseBackendServiceImpl;


public class AuthRoleNodeServiceImpl  extends BaseBackendServiceImpl<AuthRoleNode> implements IAuthNodeService {

	public boolean IsNodePageAuth(UUID iUSERID, int iCurrentPageNodeId, boolean isSuper) {
		// TODO Auto-generated method stub
		return true;
	}
}
