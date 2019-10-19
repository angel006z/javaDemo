package com.meida.front.pay.dao.inter;

import java.util.List;

import com.meida.front.pay.po.AlipayNotifyInfo;
import com.meida.front.pay.vo.AlipayNotifyVo;
import com.meida.front.base.dao.inter.BaseDao;

/**
 * AlipayNotifyDao
 * 支付宝支付成功通知
 * @author BING
 * @date 2019-10-19 15:27:58
 */
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

    long isExistOrderNo(String orderNo);

    AlipayNotifyInfo getObjectByOrderNo(String orderNo);
}
