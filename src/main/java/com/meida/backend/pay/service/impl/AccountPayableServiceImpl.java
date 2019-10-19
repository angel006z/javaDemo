package com.meida.backend.pay.service.impl;

import com.meida.backend.pay.po.AccountPayableInfo;
import com.meida.backend.pay.vo.AccountPayableVo;
import com.meida.backend.pay.dto.*;
import com.meida.backend.base.dto.*;
import com.meida.backend.pay.service.inter.AccountPayableService;
import com.meida.backend.pay.dao.inter.AccountPayableDao;

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
 * AccountPayable业务实现类
 *
 * @author BING
 * @date 2019-10-19 18:56:35
 */
@Service("backendAccountPayableServiceImpl")
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
        CurrentUserDto currentUserDto = submitDto.getCurrentUserDto();
        if (submitDto.getOperate().equals(EOperate.ADD)) {
																																																																																																																																				item.setIsValid(ESystemStatus.Valid);
			item.setCreateDate(nowTime);
			item.setCreateUserId(currentUserDto.getUserId());
			item.setCreateUser(currentUserDto.getRealName());
			item.setUpdateDate(nowTime);
			item.setUpdateUserId(currentUserDto.getUserId());
			item.setUpdateUser(currentUserDto.getRealName());
        	return accountPayableDao.save(item) > 0;
        } else {
			item.setUpdateDate(nowTime);
			item.setUpdateUserId(currentUserDto.getUserId());
			item.setUpdateUser(currentUserDto.getRealName());
			return accountPayableDao.update(item) > 0;
        }
    }

	@Override
	public boolean delete(DeleteDto deleteDto) {
        boolean isFlag = accountPayableDao.logicDelete(deleteDto) > 0;
        return isFlag;
     }

	@Override
	public boolean enable(EnableDto enableDto) {
        boolean isFlag = accountPayableDao.enable(enableDto) > 0;
        return isFlag;
    }

	@Override
	public boolean disable(DisableDto disableDto) {
        boolean isFlag = accountPayableDao.disable(disableDto) > 0;
        return isFlag;
     }

	@Override
	public boolean physicalDelete(PhysicalDeleteDto physicalDeleteDto) {
    	boolean isFlag = accountPayableDao.physicalDelete(physicalDeleteDto.getIds()) > 0;
    	return isFlag;
    }
}
