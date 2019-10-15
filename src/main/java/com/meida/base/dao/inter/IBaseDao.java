package com.meida.base.dao.inter;

import com.meida.backend.basic.dto.DeleteDto;

import java.io.Serializable;
import java.util.List;

/**
 * Dao接口
 *
 * @param <T>
 */
public interface IBaseDao<T> {
    /**
     * 新增单条记录
     *
     * @param entity
     * @return
     */
    public long save(T entity);

    /**
     * 根据主键id更新单条记录
     *
     * @param entity
     * @return
     */
    public long update(T entity);

    /**
     * 物理删除多条记录
     *
     * @param ids
     * @return
     */
    public long physicalDelete(Serializable[] ids);

    /**
     * 逻辑删除多条记录
     *
     * @param deleteDto
     * @return
     */
    public long logicDelete(DeleteDto deleteDto);

    /**
     * 根据主键id获取单条记录
     *
     * @param id
     * @return
     */
    public T getObjectById(Serializable id);

    /**
     * 获取所有记录
     *
     * @return
     */
    public List<T> getListByAll();

    /**
     * 获取所有有效记录
     *
     * @return
     */
    public List<T> getListByValid();

    /**
     * 获取分页记录
     *
     * @return
     */
    public List<T> getListPage(Object condition);

    /**
     * 获取总记录数
     *
     * @return
     */
    public long getTotalRecord(Object condition);
}
