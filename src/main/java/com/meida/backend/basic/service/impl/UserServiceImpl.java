package com.meida.backend.basic.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meida.backend.basic.dao.inter.IUserDao;
import com.meida.backend.basic.po.User;
import com.meida.backend.basic.service.inter.IUserService;
import com.meida.base.service.impl.BaseBackendServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends BaseBackendServiceImpl<User> implements IUserService {

    @Autowired
    private IUserDao dao;
    
}
