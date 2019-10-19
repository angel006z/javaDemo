package com.meida.backend.pay.dao.inter;

import java.util.List;

import com.meida.backend.pay.po.AlipayReturnInfo;
import com.meida.backend.pay.vo.AlipayReturnVo;
import com.meida.backend.base.dao.inter.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * AlipayReturnDao
 * 支付宝同步通知
 * @author BING
 * @date 2019-10-19 18:56:35
 */
@Repository("backendAlipayReturnDao")
public interface AlipayReturnDao extends BaseDao<AlipayReturnInfo> {
    /**
     * 获取Vo分页记录
     *
     * @param condition 查询条件
     * @return vo list
     */
    List<AlipayReturnVo> getVoListPage(Object condition);

    /**
     * 获取Vo总记录数
     *
     * @param condition 查询条件
     * @return vo total record
    */
    long getVoTotalRecord(Object condition);
}
