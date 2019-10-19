package com.meida.front.pay.service.impl;

import com.meida.front.pay.po.AccountReceivableInfo;
import com.meida.front.pay.dto.*;
import com.meida.front.base.dto.*;
import com.meida.front.pay.service.inter.AccountReceivableService;
import com.meida.front.pay.dao.inter.AccountReceivableDao;

import com.meida.common.constant.EOperate;
import com.meida.common.constant.ESystemStatus;
import com.meida.common.util.DateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * AccountReceivable业务实现类
 *
 * @author BING
 * @date 2019-10-19 15:27:58
 */
@Service("accountReceivableService")
public class AccountReceivableServiceImpl implements AccountReceivableService {
	@Autowired
	private AccountReceivableDao accountReceivableDao;

	@Override
	public AccountReceivableInfo getObjectById(Serializable id) {
        return accountReceivableDao.getObjectById(id);
	}

	@Override
	public List<AccountReceivableInfo> getListPage(AccountReceivableListDto whereItemDto) {
        whereItemDto.getPagination().setPageIndex((whereItemDto.getPagination().getCurrentPage() - 1) * whereItemDto.getPagination().getPageSize());
        List<AccountReceivableInfo> list = accountReceivableDao.getListPage(whereItemDto);
        long totalRecord = accountReceivableDao.getTotalRecord(whereItemDto);
        whereItemDto.getPagination().setTotalRecord(totalRecord);
        return list;
    }

	@Override
	public List<AccountReceivableInfo> getListByAll() {
        return accountReceivableDao.getListByAll();
     }

	@Override
	public List<AccountReceivableInfo> getListByValid() {
        return accountReceivableDao.getListByValid();
    }

	@Override
	public boolean submit(AccountReceivableSubmitDto submitDto) {
        Date nowTime = DateUtils.now();
	    AccountReceivableInfo item = submitDto.getItem();
        CurrentMemberDto currentMemberDto = submitDto.getCurrentMemberDto();
        if (submitDto.getOperate().equals(EOperate.ADD)) {
																																																																																																																																				item.setIsValid(ESystemStatus.Valid);
			item.setCreateDate(nowTime);
			item.setCreateUserId(currentMemberDto.getMemberId().toString());
			item.setCreateUser(currentMemberDto.getAccount());
			item.setUpdateDate(nowTime);
			item.setUpdateUserId(currentMemberDto.getMemberId().toString());
			item.setUpdateUser(currentMemberDto.getAccount());
        	return accountReceivableDao.save(item) > 0;
        } else {
			item.setUpdateDate(nowTime);
			item.setUpdateUserId(currentMemberDto.getMemberId().toString());
			item.setUpdateUser(currentMemberDto.getAccount());
			return accountReceivableDao.update(item) > 0;
        }
    }

	@Override
	public boolean delete(DeleteDto deleteDto) {
        boolean isFlag = accountReceivableDao.logicDelete(deleteDto) > 0;
        return isFlag;
	}
}
