package com.meida.backend.basic.dao.inter;

import com.meida.backend.basic.po.AuthRoleNode;
import com.meida.backend.basic.vo.LeftAuthRoleNodeVo;
import com.meida.base.dao.inter.IBaseDao;

import java.io.Serializable;
import java.util.List;

public interface IAuthRoleNodeDao extends IBaseDao<AuthRoleNode> {
    List<LeftAuthRoleNodeVo> getListByLeftUserId(Serializable userId, boolean isSuper);
}
