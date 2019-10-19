package com.meida.front.pay.service.impl;

import com.meida.front.pay.po.AccountPayableInfo;
import com.meida.front.pay.dto.*;
import com.meida.front.base.dto.*;
import com.meida.front.pay.service.inter.AccountPayableService;
import com.meida.front.pay.dao.inter.AccountPayableDao;

import com.meida.common.constant.EOperate;
import com.meida.common.constant.ESystemStatus;
import com.meida.common.util.DateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * AccountPayable业务实现类
 *
 * @author BING
 * @date 2019-10-19 15:27:58
 */
@Service("accountPayableService")
public class AccountPayableServiceImpl implements AccountPayableService {
	@Autowired
	private AccountPayableDao accountPayableDao;

	@Override
	public AccountPayableInfo getObjectById(Serializable id) {
        return accountPayableDao.getObjectById(id);
	}

	@Override
	public List<AccountPayableInfo> getListPage(AccountPayableListDto whereItemDto) {
        whereItemDto.getPagination().setPageIndex((whereItemDto.getPagination().getCurrentPage() - 1) * whereItemDto.getPagination().getPageSize());
        List<AccountPayableInfo> list = accountPayableDao.getListPage(whereItemDto);
        long totalRecord = accountPayableDao.getTotalRecord(whereItemDto);
        whereItemDto.getPagination().setTotalRecord(totalRecord);
        return list;
    }

	@Override
	public List<AccountPayableInfo> getListByAll() {
        return accountPayableDao.getListByAll();
     }

	@Override
	public List<AccountPayableInfo> getListByValid() {
        return accountPayableDao.getListByValid();
    }

	@Override
	public boolean submit(AccountPayableSubmitDto submitDto) {
        Date nowTime = DateUtils.now();
	    AccountPayableInfo item = submitDto.getItem();
        CurrentMemberDto currentMemberDto = submitDto.getCurrentMemberDto();
        if (submitDto.getOperate().equals(EOperate.ADD)) {
																																																																																																																																				item.setIsValid(ESystemStatus.Valid);
			item.setCreateDate(nowTime);
			item.setCreateUserId(currentMemberDto.getMemberId().toString());
			item.setCreateUser(currentMemberDto.getAccount());
			item.setUpdateDate(nowTime);
			item.setUpdateUserId(currentMemberDto.getMemberId().toString());
			item.setUpdateUser(currentMemberDto.getAccount());
        	return accountPayableDao.save(item) > 0;
        } else {
			item.setUpdateDate(nowTime);
			item.setUpdateUserId(currentMemberDto.getMemberId().toString());
			item.setUpdateUser(currentMemberDto.getAccount());
			return accountPayableDao.update(item) > 0;
        }
    }

	@Override
	public boolean delete(DeleteDto deleteDto) {
        boolean isFlag = accountPayableDao.logicDelete(deleteDto) > 0;
        return isFlag;
	}
}
