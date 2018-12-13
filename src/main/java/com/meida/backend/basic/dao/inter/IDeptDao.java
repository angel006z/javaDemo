package com.meida.backend.basic.dao.inter;

import java.io.Serializable;
import java.util.List;

import com.meida.backend.basic.po.Dept;
import com.meida.base.dao.inter.IBaseDao;

public interface IDeptDao {
	/**
	 * 保存单条记录
	 * @param item
	 * @return
	 */
	public int save(Dept item);

    /**
     * 更新单条记录
     * @param item
     * @return
     */
    public int update(Dept item);
    
    /**
     * 物理删除单条记录
     * @param id
     * @return
     */
    public int deletePhysicalById(String id);
    
    /**
     * 逻辑删除单条记录
     * @param id
     * @return
     */
    public int deleteLogicById(String id);
    
    /**
     * 根据主键查找用户
     * @param id
     * @return
     */
    public Dept getById(String id);

    /**
     * 查询所有记录
     * @return
     */
    public List<Dept> getByAll();
    
    /**
     * 查询所有有效记录
     * @return
     */
    public List<Dept> getByValid();
}
