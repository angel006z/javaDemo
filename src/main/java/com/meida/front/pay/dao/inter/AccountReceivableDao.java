package com.meida.front.pay.dao.inter;

import java.util.List;

import com.meida.front.pay.po.AccountReceivableInfo;
import com.meida.front.pay.vo.AccountReceivableVo;
import com.meida.front.base.dao.inter.BaseDao;

/**
 * AccountReceivableDao
 * 
 * @author BING
 * @date 2019-10-19 15:27:58
 */
public interface AccountReceivableDao extends BaseDao<AccountReceivableInfo> {
    /**
     * 获取Vo分页记录
     *
     * @param condition 查询条件
     * @return vo list
     */
    List<AccountReceivableVo> getVoListPage(Object condition);

    /**
     * 获取Vo总记录数
     *
     * @param condition 查询条件
     * @return vo total record
    */
    long getVoTotalRecord(Object condition);

    AccountReceivableInfo getObjectByOrderNo(String orderNo);
}
