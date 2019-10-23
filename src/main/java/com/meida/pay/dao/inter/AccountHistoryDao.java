package com.meida.pay.dao.inter;

import java.util.List;

import com.meida.pay.po.AccountHistoryInfo;
import com.meida.pay.vo.AccountHistoryVo;
import com.meida.basefront.dao.inter.BaseDao;

/**
 * AccountHistoryDao
 * 
 * @author BING
 * @date 2019-10-19 15:27:58
 */
public interface AccountHistoryDao extends BaseDao<AccountHistoryInfo> {
    /**
     * 获取Vo分页记录
     *
     * @param condition 查询条件
     * @return vo list
     */
    List<AccountHistoryVo> getVoListPage(Object condition);

    /**
     * 获取Vo总记录数
     *
     * @param condition 查询条件
     * @return vo total record
    */
    long getVoTotalRecord(Object condition);
}
