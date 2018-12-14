package com.meida.backend.basic.service.inter;

import java.io.Serializable;
import java.util.List;

import com.meida.backend.basic.dto.DeptListDto;
import com.meida.backend.basic.po.Dept;

public interface IDeptService {
	/**
	 * true:保存单条记录 false:更新单条记录
	 * 
	 * @param item
	 * @param isAdd
	 * @return
	 */
	public boolean addOrUpdate(Dept item, boolean isAdd);

	/**
	 * 批执行操作多条记录 删除、禁用、启用等
	 * 
	 * @param id
	 * @return
	 */
	public boolean batchExecuteByIds(Serializable[] ids, String command);

	/**
	 * 根据主键查找对象
	 * 
	 * @param id
	 * @return
	 */
	public Dept getObjectById(Serializable id);

	/**
	 * 查询所有记录
	 * 
	 * @return
	 */
	public List<Dept> getListByAll();

	/**
	 * 查询所有有效记录
	 * 
	 * @return
	 */
	public List<Dept> getListByValid();

	public List<Dept> getListByPage(DeptListDto whereItem);
}
