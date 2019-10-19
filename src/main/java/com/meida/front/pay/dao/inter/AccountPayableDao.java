package com.meida.front.pay.dao.inter;

import java.util.List;

import com.meida.front.pay.po.AccountPayableInfo;
import com.meida.front.pay.vo.AccountPayableVo;
import com.meida.front.base.dao.inter.BaseDao;

/**
 * AccountPayableDao
 * 
 * @author BING
 * @date 2019-10-19 15:27:58
 */
public interface AccountPayableDao extends BaseDao<AccountPayableInfo> {
    /**
     * 获取Vo分页记录
     *
     * @param condition 查询条件
     * @return vo list
     */
    List<AccountPayableVo> getVoListPage(Object condition);

    /**
     * 获取Vo总记录数
     *
     * @param condition 查询条件
     * @return vo total record
    */
    long getVoTotalRecord(Object condition);
}
