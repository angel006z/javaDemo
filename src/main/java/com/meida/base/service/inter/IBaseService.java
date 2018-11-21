package com.meida.base.service.inter;

import java.io.Serializable;
import java.util.List;

/**
 * 通用业务层
 * @param <T>
 */
public interface IBaseService<T> {
	/**
	 * 保存单条记录
	 * @param entity
	 * @return
	 */
	public boolean save(T entity);

    /**
     * 更新单条记录
     * @param entity
     * @return
     */
    public boolean update(T entity);
    
    /**
     * 物理删除单条记录
     * @param id
     * @return
     */
    public boolean deletePhysicalById(Serializable id);
    
    /**
     * 逻辑删除单条记录
     * @param id
     * @return
     */
    public boolean deleteLogicById(Serializable id);
    
    /**
     * 根据主键查找用户
     * @param id
     * @return
     */
    public T getById(Serializable id);

    /**
     * 查询所有记录
     * @return
     */
    public List<T> getByAll();
    
    /**
     * 查询所有有效记录
     * @return
     */
    public List<T> getByValid();
    
}
