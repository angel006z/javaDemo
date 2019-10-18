package com.meida.backend.basic.dao.inter;

import com.meida.backend.base.dao.inter.BaseDao;
import com.meida.backend.basic.po.AuthRoleNode;
import com.meida.backend.basic.vo.LeftAuthRoleNodeVo;

import java.io.Serializable;
import java.util.List;

public interface IAuthRoleNodeDao extends BaseDao<AuthRoleNode> {
    List<LeftAuthRoleNodeVo> getListByLeftUserId(Serializable userId, boolean isSuper);
}
