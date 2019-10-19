package com.meida.backend.pay.dao.inter;

import java.util.List;

import com.meida.backend.pay.po.AccountHistoryInfo;
import com.meida.backend.pay.vo.AccountHistoryVo;
import com.meida.backend.base.dao.inter.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * AccountHistoryDao
 * 
 * @author BING
 * @date 2019-10-19 18:56:35
 */
@Repository("backendAccountHistoryDao")
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
