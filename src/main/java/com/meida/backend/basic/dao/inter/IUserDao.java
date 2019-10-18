package com.meida.backend.basic.dao.inter;

import java.util.List;
import java.util.UUID;

import com.meida.backend.base.dao.inter.BaseDao;
import com.meida.backend.basic.po.User;
import com.meida.backend.basic.vo.UserVo;

public interface IUserDao extends BaseDao<User> {
	List<UserVo> getListPageByCondition(Object condition);

	long getTotalRecordByCondition(Object condition);

	long isExistUserName(String userName);

	long isExistUserName(UUID id,String userName);

	User loginUser(String userName, String password);

	boolean changePassword(String userId, String password);
}
