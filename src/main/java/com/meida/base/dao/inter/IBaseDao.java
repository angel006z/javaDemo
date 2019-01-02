package com.meida.base.dao.inter;

import java.io.Serializable;
import java.util.List;

public interface IBaseDao<T> {
	
	/**
	 * 保存单条记录
	 * 
	 * @param item
	 * @return
	 */
	public int save(T item);

	/**
	 * 更新单条记录
	 * 
	 * @param item
	 * @return
	 */
	public int update(T item);

	/**
	 * 物理删除单条记录
	 * 
	 * @param id
	 * @return
	 */
	public int deletePhysicalById(Serializable id);

	/**
	 * 逻辑删除单条记录
	 * 
	 * @param id
	 * @return
	 */
	public int deleteLogicById(Serializable id);

	/**
	 * 根据主键查找用户
	 * 
	 * @param id
	 * @return
	 */
	public T getObjectById(Serializable id);

	/**
	 * 查询所有记录
	 * 
	 * @return
	 */
	public List<T> getListByAll();

	/**
	 * 查询所有有效记录
	 * 
	 * @return
	 */
	public List<T> getListByValid();
	
	/**
	 * 查询分页录数
	 * 
	 * @return
	 */
	public List<T> getListByPage(Object item);
	
	/**
	 * 获取总记录数
	 * 
	 * @return
	 */
	public long getTotalRecord(Object item);

}
