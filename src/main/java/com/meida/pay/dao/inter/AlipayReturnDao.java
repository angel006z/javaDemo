package com.meida.pay.dao.inter;

import java.util.List;

import com.meida.pay.po.AlipayReturnInfo;
import com.meida.pay.vo.AlipayReturnVo;
import com.meida.basefront.dao.inter.BaseDao;

/**
 * AlipayReturnDao
 * 支付宝同步通知
 * @author BING
 * @date 2019-10-19 15:27:58
 */
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

    long isExistOrderNo(String orderNo);

    AlipayReturnInfo getObjectByOrderNo(String orderNo);
}
