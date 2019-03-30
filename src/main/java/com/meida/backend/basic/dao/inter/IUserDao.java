package com.meida.backend.basic.dao.inter;

import com.meida.backend.basic.po.User;
import com.meida.base.dao.inter.IBaseDao;

public interface IUserDao extends IBaseDao<User> {

	long isExistUserName(String userName);

	User loginUser(String userName, String password);	
}
