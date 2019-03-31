package com.meida.backend.basic.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meida.backend.basic.dao.inter.IRoleDao;
import com.meida.backend.basic.domain.dto.RoleListDto;
import com.meida.backend.basic.domain.po.Role;
import com.meida.backend.basic.service.inter.IRoleService;
import com.meida.base.domain.vo.Pagination;
import com.meida.common.util.constant.EButtonType;

@Service
public class RoleServiceImpl implements IRoleService {
	@Autowired
	private IRoleDao roleDao;

	@Override
	public boolean addOrUpdate(Role item, boolean isAdd) {
		if (isAdd) {
			return roleDao.save(item) > 0;
		} else {
			return roleDao.update(item) > 0;
		}
	}

	@Override
	public boolean batchExecuteByIds(Serializable[] ids, String command) {
		switch (command) {
		case "" + EButtonType.PhyDelete:
			return roleDao.deletePhysicalById(ids) > 0;
		case "" + EButtonType.Enable:
			return roleDao.deleteLogicById(ids) > 0;
		case "" + EButtonType.Disable:
			return roleDao.deleteLogicById(ids) > 0;
		default:
			return false;
		}
	}

	@Override
	public Role getObjectById(Serializable id) {
		return roleDao.getObjectById(id);
	}

	@Override
	public List<Role> getListByAll() {
		return roleDao.getListByAll();
	}

	@Override
	public List<Role> getListByValid() {
		return roleDao.getListByValid();
	}

	@Override
	public List<Role> getListByPage(RoleListDto whereItem) {
		long totalRecord = roleDao.getTotalRecord(whereItem);
		Pagination pagination = new Pagination();
		pagination.setTotalRecord(totalRecord);
		whereItem.setPagination(pagination);
		return roleDao.getListByPage(whereItem);
	}

}
