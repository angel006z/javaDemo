package com.meida.backend.basic.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meida.backend.basic.dao.inter.IUserDao;
import com.meida.backend.basic.domain.dto.UserListDto;
import com.meida.backend.basic.domain.po.User;
import com.meida.backend.basic.domain.vo.UserVo;
import com.meida.backend.basic.service.inter.IUserService;
import com.meida.base.domain.vo.Pagination;
import com.meida.common.util.constant.EButtonType;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserDao userDao;

	@Override
	public boolean addOrUpdate(User item, boolean isAdd) {
		if (isAdd) {
			return userDao.save(item) > 0;
		} else {
			return userDao.update(item) > 0;
		}
	}

	@Override
	public boolean batchExecuteByIds(Serializable[] ids, String command) {
		switch (command) {
		case "" + EButtonType.PhyDelete:
			return userDao.deletePhysicalById(ids) > 0;
		case "" + EButtonType.Enable:
			return userDao.deleteLogicById(ids) > 0;
		case "" + EButtonType.Disable:
			return userDao.deleteLogicById(ids) > 0;
		default:
			return false;
		}
	}

	@Override
	public User getObjectById(Serializable id) {
		return userDao.getObjectById(id);
	}

	@Override
	public List<User> getListByAll() {
		return userDao.getListByAll();
	}

	@Override
	public List<User> getListByValid() {
		return userDao.getListByValid();
	}

	@Override
	public List<UserVo> getListByPage(UserListDto whereItem) {
		long totalRecord = userDao.getTotalRecord(whereItem);
		Pagination pagination = new Pagination();
		pagination.setTotalRecord(totalRecord);
		whereItem.setPagination(pagination);
		return userDao.getListWhereByPage(whereItem);
	}

	
	@Override
	public boolean isExistUserName(String userName) {
		return userDao.isExistUserName(userName) > 0;
	}

	@Override
	public User loginUser(String userName, String password) {		
		return userDao.loginUser(userName,password);
	}

	@Override
	public boolean changePassword(String userId, String newPassword) {
		return userDao.changePassword(userId, newPassword);
	}

}
