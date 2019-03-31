package com.meida.backend.basic.service.inter;

import java.io.Serializable;
import java.util.List;

import com.meida.backend.basic.domain.dto.RoleListDto;
import com.meida.backend.basic.domain.po.Role;

public interface IRoleService{
	/**
	 * true:保存单条记录 false:更新单条记录
	 * 
	 * @param item
	 * @param isAdd
	 * @return
	 */
	public boolean addOrUpdate(Role item, boolean isAdd);

	/**
	 * 批执行操作多条记录 删除、禁用、启用等
	 * 
	 * @param ids
	 * @param command
	 * @return
	 */
	public boolean batchExecuteByIds(Serializable[] ids, String command);

	/**
	 * 根据主键查找对象
	 * 
	 * @param id
	 * @return
	 */
	public Role getObjectById(Serializable id);

	/**
	 * 查询所有记录
	 * 
	 * @return
	 */
	public List<Role> getListByAll();

	/**
	 * 查询所有有效记录
	 * 
	 * @return
	 */
	public List<Role> getListByValid();
	
    /**
     * 根据条件查记录
     * @param whereItem
     * @return
     */
	public List<Role> getListByPage(RoleListDto whereItem);
}
