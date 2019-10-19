package com.meida.backend.pay.dao.inter;

import java.util.List;

import com.meida.backend.pay.po.AlipayNotifyInfo;
import com.meida.backend.pay.vo.AlipayNotifyVo;
import com.meida.backend.base.dao.inter.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * AlipayNotifyDao
 * 支付宝支付成功通知
 * @author BING
 * @date 2019-10-19 18:56:35
 */
@Repository("backendAlipayNotifyDao")
public interface AlipayNotifyDao extends BaseDao<AlipayNotifyInfo> {
    /**
     * 获取Vo分页记录
     *
     * @param condition 查询条件
     * @return vo list
     */
    List<AlipayNotifyVo> getVoListPage(Object condition);

    /**
     * 获取Vo总记录数
     *
     * @param condition 查询条件
     * @return vo total record
    */
    long getVoTotalRecord(Object condition);
}
