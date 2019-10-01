package com.meida.backend.basic.dao.inter;

import java.util.List;

import com.meida.backend.basic.po.User;
import com.meida.backend.basic.vo.UserVo;
import com.meida.base.dao.inter.IBaseDao;

public interface IUserDao extends IBaseDao<User> {
	
	List<UserVo> getListWhereByPage(Object item);
	
	long isExistUserName(String userName);

	User loginUser(String userName, String password);

	boolean changePassword(String userId, String password);
	//long getTotalRecord(Object item);
}
