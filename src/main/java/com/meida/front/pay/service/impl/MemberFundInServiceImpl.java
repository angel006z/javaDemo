package com.meida.front.pay.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meida.common.util.StringUtils;
import com.meida.front.pay.dao.inter.IMemberFundInDao;
import com.meida.front.pay.domain.po.AlipayNotify;
import com.meida.front.pay.domain.po.MemberFundIn;
import com.meida.front.pay.service.inter.IMemberFundInService;

@Service
public class MemberFundInServiceImpl implements IMemberFundInService {
    @Autowired
    private IMemberFundInDao memberFundInDao;

    @Override
    public boolean addOrUpdate(MemberFundIn item, boolean isAdd) {
        if (isAdd) {
            return memberFundInDao.save(item) > 0;
        } else {
            return memberFundInDao.update(item) > 0;
        }
    }

    @Override
    public boolean isExistOrderNo(String orderNo) {
        return memberFundInDao.isExistOrderNo(orderNo) > 0;
    }

    @Override
    public MemberFundIn getObjectByOrderNo(Serializable orderNo) {
        return memberFundInDao.getObjectByOrderNo(orderNo);
    }
}
