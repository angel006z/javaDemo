package com.meida.base.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.meida.base.dao.inter.IBaseDao;

/**
 * 
 * 只是约 定义 目前不需要任何内容
 *
 * @param <T>
 */
@Repository
public class BaseDaoImpl<T> implements IBaseDao<T> {

	@Override
	public boolean save(T entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(T entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deletePhysicalById(Serializable id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteLogicById(Serializable id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public T getById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> getByAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> getByValid() {
		// TODO Auto-generated method stub
		return null;
	}

}
