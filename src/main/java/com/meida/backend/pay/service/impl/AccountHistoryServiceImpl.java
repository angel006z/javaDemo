package com.meida.backend.pay.service.impl;

import com.meida.backend.pay.po.AccountHistoryInfo;
import com.meida.backend.pay.vo.AccountHistoryVo;
import com.meida.backend.pay.dto.*;
import com.meida.backend.base.dto.*;
import com.meida.backend.pay.service.inter.AccountHistoryService;
import com.meida.backend.pay.dao.inter.AccountHistoryDao;

import com.meida.common.constant.EOperate;
import com.meida.common.constant.ESystemStatus;
import com.meida.common.util.DateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * AccountHistory业务实现类
 *
 * @author BING
 * @date 2019-10-19 18:56:35
 */
@Service("backendAccountHistoryServiceImpl")
public class AccountHistoryServiceImpl implements AccountHistoryService {
	@Autowired
	private AccountHistoryDao accountHistoryDao;

	@Override
	public AccountHistoryInfo getObjectById(Serializable id) {
        return accountHistoryDao.getObjectById(id);
	}

	@Override
	public List<AccountHistoryInfo> getListPage(AccountHistoryListDto whereItemDto) {
        whereItemDto.getPagination().setPageIndex((whereItemDto.getPagination().getCurrentPage() - 1) * whereItemDto.getPagination().getPageSize());
        List<AccountHistoryInfo> list = accountHistoryDao.getListPage(whereItemDto);
        long totalRecord = accountHistoryDao.getTotalRecord(whereItemDto);
        whereItemDto.getPagination().setTotalRecord(totalRecord);
        return list;
    }

	@Override
	public List<AccountHistoryInfo> getListByAll() {
        return accountHistoryDao.getListByAll();
     }

	@Override
	public List<AccountHistoryInfo> getListByValid() {
        return accountHistoryDao.getListByValid();
    }

	@Override
	public boolean submit(AccountHistorySubmitDto submitDto) {
        Date nowTime = DateUtils.now();
	    AccountHistoryInfo item = submitDto.getItem();
        CurrentUserDto currentUserDto = submitDto.getCurrentUserDto();
        if (submitDto.getOperate().equals(EOperate.ADD)) {
																																																																																																																																											item.setIsValid(ESystemStatus.Valid);
			item.setCreateDate(nowTime);
			item.setCreateUserId(currentUserDto.getUserId());
			item.setCreateUser(currentUserDto.getRealName());
			item.setUpdateDate(nowTime);
			item.setUpdateUserId(currentUserDto.getUserId());
			item.setUpdateUser(currentUserDto.getRealName());
        	return accountHistoryDao.save(item) > 0;
        } else {
			item.setUpdateDate(nowTime);
			item.setUpdateUserId(currentUserDto.getUserId());
			item.setUpdateUser(currentUserDto.getRealName());
			return accountHistoryDao.update(item) > 0;
        }
    }

	@Override
	public boolean delete(DeleteDto deleteDto) {
        boolean isFlag = accountHistoryDao.logicDelete(deleteDto) > 0;
        return isFlag;
     }

	@Override
	public boolean enable(EnableDto enableDto) {
        boolean isFlag = accountHistoryDao.enable(enableDto) > 0;
        return isFlag;
    }

	@Override
	public boolean disable(DisableDto disableDto) {
        boolean isFlag = accountHistoryDao.disable(disableDto) > 0;
        return isFlag;
     }

	@Override
	public boolean physicalDelete(PhysicalDeleteDto physicalDeleteDto) {
    	boolean isFlag = accountHistoryDao.physicalDelete(physicalDeleteDto.getIds()) > 0;
    	return isFlag;
    }
}
