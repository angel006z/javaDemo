package com.meida.backend.basic.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import com.meida.backend.basic.dao.inter.IAuthRoleNodeDao;
import com.meida.backend.basic.po.AuthRoleNode;
import com.meida.backend.basic.vo.LeftAuthRoleNodeVo;
import com.meida.backend.basic.service.inter.IAuthRoleNodeService;
import com.meida.common.util.constant.EButtonType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthRoleNodeServiceImpl implements IAuthRoleNodeService {
	@Autowired
	private IAuthRoleNodeDao authRoleNodeDao;

	@Override
	public boolean addOrUpdate(AuthRoleNode item, boolean isAdd) {
		if (isAdd) {
			return authRoleNodeDao.save(item) > 0;
		} else {
			return authRoleNodeDao.update(item) > 0;
		}
	}

	@Override
	public boolean batchExecuteByIds(Serializable[] ids, String command) {
		switch (command) {
			case "" + EButtonType.PhyDelete:
				return authRoleNodeDao.deletePhysicalById(ids) > 0;
			case "" + EButtonType.Enable:
				return authRoleNodeDao.deleteLogicById(ids) > 0;
			case "" + EButtonType.Disable:
				return authRoleNodeDao.deleteLogicById(ids) > 0;
			default:
				return false;
		}
	}

	@Override
	public AuthRoleNode getObjectById(Serializable id) {
		return authRoleNodeDao.getObjectById(id);
	}

	@Override
	public List<AuthRoleNode> getListByAll() {
		return authRoleNodeDao.getListByAll();
	}

	@Override
	public List<AuthRoleNode> getListByValid() {
		return authRoleNodeDao.getListByValid();
	}

	@Override
	public List<LeftAuthRoleNodeVo> getListByLeftUserId(Serializable userId,boolean isSuper) {
		return authRoleNodeDao.getListByLeftUserId(userId,isSuper);
	}

	public boolean IsNodePageAuth(UUID iUSERID, int iCurrentPageNodeId, boolean isSuper) {
		// TODO Auto-generated method stub
		return true;
	}
}
