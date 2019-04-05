package com.meida.backend.basic.service.inter;

import com.meida.backend.basic.domain.po.AuthRoleNode;
import com.meida.backend.basic.domain.po.AuthRoleNode;
import com.meida.backend.basic.domain.vo.LeftAuthRoleNodeVo;

import java.io.Serializable;
import java.util.List;

public interface IAuthRoleNodeService {
    /**
     * true:保存单条记录 false:更新单条记录
     *
     * @param item
     * @param isAdd
     * @return
     */
    public boolean addOrUpdate(AuthRoleNode item, boolean isAdd);

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
    public AuthRoleNode getObjectById(Serializable id);

    /**
     * 查询所有记录
     *
     * @return
     */
    public List<AuthRoleNode> getListByAll();

    /**
     * 查询所有有效记录
     *
     * @return
     */
    public List<AuthRoleNode> getListByValid();

    public List<LeftAuthRoleNodeVo> getListByLeftUserId(Serializable userId,boolean isSuper);
}
