package com.meida.base.service.inter;

import java.io.Serializable;
import java.util.List;

/**
 * 通用业务层
 * 
 * @param <T>
 */
public interface IBaseService<T> {
	/**
	 * true:保存单条记录
	 * false:更新单条记录
	 * @param entity
	 * @param isAdd
	 * @return
	 */
	public boolean addOrUpdate(T entity, boolean isAdd);
	
	/**
	 *批执行操作多条记录
	 * 删除、禁用、启用等
	 * @param id
	 * @return
	 */
	public boolean batchExecuteByIds(Serializable[] ids,String command);
	
	
	/**
	 * 根据主键查找用户
	 * 
	 * @param id
	 * @return
	 */
	public T getById(Serializable id);

	/**
	 * 查询所有记录
	 * 
	 * @return
	 */
	public List<T> getByAll();

	/**
	 * 查询所有有效记录
	 * 
	 * @return
	 */
	public List<T> getByValid();

}
