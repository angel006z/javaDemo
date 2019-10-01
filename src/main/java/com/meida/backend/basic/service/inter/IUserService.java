package com.meida.backend.basic.service.inter;

import java.io.Serializable;
import java.util.List;

import com.meida.backend.basic.dto.UserListDto;
import com.meida.backend.basic.po.User;
import com.meida.backend.basic.vo.UserVo;

public interface IUserService{
	/**
	 * true:保存单条记录 false:更新单条记录
	 * 
	 * @param item
	 * @param isAdd
	 * @return
	 */
	public boolean addOrUpdate(User item, boolean isAdd);

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
	public User getObjectById(Serializable id);

	/**
	 * 查询所有记录
	 * 
	 * @return
	 */
	public List<User> getListByAll();

	/**
	 * 查询所有有效记录
	 * 
	 * @return
	 */
	public List<User> getListByValid();
	
    /**
     * 根据条件查记录
     * @param whereItem
     * @return
     */
	public List<UserVo> getListByPage(UserListDto whereItem);

	public boolean isExistUserName(String userName);

	public User loginUser(String userName, String password);

	public boolean changePassword(String userId, String newPassword);
}
