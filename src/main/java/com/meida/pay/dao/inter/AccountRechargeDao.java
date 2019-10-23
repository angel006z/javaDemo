package com.meida.pay.dao.inter;

import java.io.Serializable;
import java.util.List;

import com.meida.pay.po.AccountRechargeInfo;
import com.meida.pay.vo.AccountRechargeVo;
import com.meida.basefront.dao.inter.BaseDao;

/**
 * AccountRechargeDao
 * 
 * @author BING
 * @date 2019-10-19 15:27:58
 */
public interface AccountRechargeDao extends BaseDao<AccountRechargeInfo> {
    /**
     * 获取Vo分页记录
     *
     * @param condition 查询条件
     * @return vo list
     */
    List<AccountRechargeVo> getVoListPage(Object condition);

    /**
     * 获取Vo总记录数
     *
     * @param condition 查询条件
     * @return vo total record
    */
    long getVoTotalRecord(Object condition);

    long isExistOrderNo(Serializable orderNo);

    AccountRechargeInfo getObjectByOrderNo(Serializable orderNo);

    long updateByOrderNo(AccountRechargeInfo recharge);
}
