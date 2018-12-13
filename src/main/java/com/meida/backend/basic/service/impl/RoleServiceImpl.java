package com.meida.backend.basic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meida.backend.basic.po.Role;
import com.meida.backend.basic.service.inter.IRoleService;
import com.meida.base.service.impl.BaseBackendServiceImpl;

@Service
public class RoleServiceImpl  extends BaseBackendServiceImpl<Role> implements IRoleService {
	
}
