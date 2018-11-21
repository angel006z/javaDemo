package com.meida.backend.basic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meida.backend.basic.dao.inter.IDeptDao;
import com.meida.backend.basic.po.Dept;
import com.meida.backend.basic.service.inter.IDeptService;
import com.meida.base.service.impl.BaseBackendServiceImpl;

@Service
@Transactional(rollbackFor = Exception.class)
public class DeptServiceImpl extends BaseBackendServiceImpl<Dept> implements IDeptService {
	@Autowired
	private IDeptDao dao;
	
}
