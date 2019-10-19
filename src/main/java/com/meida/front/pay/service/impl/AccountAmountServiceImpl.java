package com.meida.front.pay.service.impl;

import com.meida.front.pay.po.AccountAmountInfo;
import com.meida.front.pay.dto.*;
import com.meida.front.base.dto.*;
import com.meida.front.pay.service.inter.AccountAmountService;
import com.meida.front.pay.dao.inter.AccountAmountDao;

import com.meida.common.constant.EOperate;
import com.meida.common.constant.ESystemStatus;
import com.meida.common.util.DateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * AccountAmount业务实现类
 *
 * @author BING
 * @date 2019-10-19 15:27:58
 */
@Service("accountAmountService")
public class AccountAmountServiceImpl implements AccountAmountService {
	@Autowired
	private AccountAmountDao accountAmountDao;

	@Override
	public AccountAmountInfo getObjectById(Serializable id) {
        return accountAmountDao.getObjectById(id);
	}

	@Override
	public List<AccountAmountInfo> getListPage(AccountAmountListDto whereItemDto) {
        whereItemDto.getPagination().setPageIndex((whereItemDto.getPagination().getCurrentPage() - 1) * whereItemDto.getPagination().getPageSize());
        List<AccountAmountInfo> list = accountAmountDao.getListPage(whereItemDto);
        long totalRecord = accountAmountDao.getTotalRecord(whereItemDto);
        whereItemDto.getPagination().setTotalRecord(totalRecord);
        return list;
    }

	@Override
	public List<AccountAmountInfo> getListByAll() {
        return accountAmountDao.getListByAll();
     }

	@Override
	public List<AccountAmountInfo> getListByValid() {
        return accountAmountDao.getListByValid();
    }

	@Override
	public boolean submit(AccountAmountSubmitDto submitDto) {
        Date nowTime = DateUtils.now();
	    AccountAmountInfo item = submitDto.getItem();
        CurrentMemberDto currentMemberDto = submitDto.getCurrentMemberDto();
        if (submitDto.getOperate().equals(EOperate.ADD)) {
																																																																																										item.setIsValid(ESystemStatus.Valid);
			item.setCreateDate(nowTime);
			item.setCreateUserId(currentMemberDto.getMemberId().toString());
			item.setCreateUser(currentMemberDto.getAccount());
			item.setUpdateDate(nowTime);
			item.setUpdateUserId(currentMemberDto.getMemberId().toString());
			item.setUpdateUser(currentMemberDto.getAccount());
        	return accountAmountDao.save(item) > 0;
        } else {
			item.setUpdateDate(nowTime);
			item.setUpdateUserId(currentMemberDto.getMemberId().toString());
			item.setUpdateUser(currentMemberDto.getAccount());
			return accountAmountDao.update(item) > 0;
        }
    }

	@Override
	public boolean delete(DeleteDto deleteDto) {
        boolean isFlag = accountAmountDao.logicDelete(deleteDto) > 0;
        return isFlag;
	}
}
