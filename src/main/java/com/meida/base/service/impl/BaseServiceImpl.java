package com.meida.base.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meida.backend.basic.po.Dept;
import com.meida.base.dao.impl.BaseDaoImpl;
import com.meida.base.dao.inter.IBaseDao;
import com.meida.base.service.inter.IBaseService;
import com.meida.common.util.constant.EButtonType;

@Service
@Transactional(rollbackFor = Exception.class)
public abstract class BaseServiceImpl<T> implements IBaseService<T> {

	
	private IBaseDao<T> baseDao=new BaseDaoImpl<T>();
	@Override
	public boolean addOrUpdate(T entity, boolean isAdd) {
		if (isAdd) {
			return baseDao.save(entity);
		} else {
			return baseDao.update(entity);
		}
	}

	@Override
	public boolean batchExecuteByIds(Serializable[] ids, String command) {
		switch (command) {
		case "" + EButtonType.PhyDelete:
			return baseDao.deletePhysicalById(ids);
		case "" + EButtonType.Enable:
			return baseDao.deleteLogicById(ids);
		case "" + EButtonType.Disable:
			return baseDao.deleteLogicById(ids);
		default:
			return false;
		}
	}

	
	@Override
	public T getById(Serializable id) {
		return baseDao.getById(id);
	}

	@Override
	public List<T> getByAll() {
		return baseDao.getByAll();
	}

	@Override
	public List<T> getByValid() {
		return baseDao.getByValid();
	}

}
