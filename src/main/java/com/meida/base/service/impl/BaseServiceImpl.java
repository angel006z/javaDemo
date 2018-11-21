package com.meida.base.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meida.base.dao.inter.IBaseDao;
import com.meida.base.service.inter.IBaseService;

@Service
@Transactional(rollbackFor = Exception.class)
public abstract class BaseServiceImpl<T> implements IBaseService<T> {
	
	@Autowired
    private IBaseDao<T> dao;
	
	@Override
	public boolean save(T entity) {
		return dao.save(entity);
	}

	@Override
	public boolean update(T entity) {
		return dao.update(entity);
	}

	@Override
	public boolean deletePhysicalById(Serializable id) {
		return dao.deletePhysicalById(id);
	}

	@Override
	public boolean deleteLogicById(Serializable id) {
		return dao.deleteLogicById(id);
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
