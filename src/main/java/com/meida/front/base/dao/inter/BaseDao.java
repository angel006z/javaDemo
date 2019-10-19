package com.meida.front.base.dao.inter;

import com.meida.front.base.dto.DeleteDto;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Dao接口
 *
 * @param <T>
 */
@Repository("frontBaseDao")
public interface BaseDao<T> {
    /**
     * 新增单条记录
     *
     * @param entity
     * @return
     */
    long save(T entity);

    /**
     * 根据主键id更新单条记录
     *
     * @param entity
     * @return
     */
    long update(T entity);

    /**
     * 逻辑删除多条记录
     *
     * @param deleteDto
     * @return
     */
    long logicDelete(DeleteDto deleteDto);

    /**
     * 根据主键id获取单条记录
     *
     * @param id
     * @return
     */
    T getObjectById(Serializable id);

    /**
     * 获取所有记录
     *
     * @return
     */
    List<T> getListByAll();

    /**
     * 获取所有有效记录
     *
     * @return
     */
    List<T> getListByValid();

    /**
     * 获取分页记录
     *
     * @return
     */
    List<T> getListPage(Object condition);

    /**
     * 获取总记录数
     *
     * @return
     */
    long getTotalRecord(Object condition);
}
