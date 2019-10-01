package com.meida.front.pay.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meida.front.pay.dao.inter.IFundInDao;
import com.meida.front.pay.po.FundIn;
import com.meida.front.pay.service.inter.IFundInService;

@Service
public class FundInServiceImpl implements IFundInService {
    @Autowired
    private IFundInDao fundInDao;

    @Override
    public boolean addOrUpdate(FundIn item, boolean isAdd) {
        if (isAdd) {
            return fundInDao.save(item) > 0;
        } else {
            return fundInDao.update(item) > 0;
        }
    }

    @Override
    public boolean isExistOrderNo(String orderNo) {
        return fundInDao.isExistOrderNo(orderNo) > 0;
    }

    @Override
    public FundIn getObjectByOrderNo(Serializable orderNo) {
        return fundInDao.getObjectByOrderNo(orderNo);
    }
}
