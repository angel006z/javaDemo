package com.meida.base.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meida.backend.basic.po.Dept;
import com.meida.base.dao.inter.IBaseDao;
import com.meida.base.service.inter.IBaseService;
import com.meida.common.util.constant.EButtonType;

@Service
@Transactional(rollbackFor = Exception.class)
public abstract class BaseServiceImpl<T> implements IBaseService<T> {

	@Autowired
	private IBaseDao<T> dao;
	@Override
	public boolean addOrUpdate(T entity, boolean isAdd) {
		if (isAdd) {
			return dao.save(entity);
		} else {
			return dao.update(entity);
		}
	}

	@Override
	public boolean batchExecuteByIds(Serializable[] ids, String command) {
		switch (command) {
		case "" + EButtonType.PhyDelete:
			return dao.deletePhysicalById(ids);
		case "" + EButtonType.Enable:
			return dao.deleteLogicById(ids);
		case "" + EButtonType.Disable:
			return dao.deleteLogicById(ids);
		default:
			return false;
		}
	}

	
	@Override
	public T getById(Serializable id) {
		return dao.getById(id);
	}

	@Override
	public List<T> getByAll() {
		return dao.getByAll();
	}

	@Override
	public List<T> getByValid() {
		return dao.getByValid();
	}

}
