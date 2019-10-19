package com.meida.backend.pay.service.impl;

import com.meida.backend.pay.po.AccountReceivableInfo;
import com.meida.backend.pay.vo.AccountReceivableVo;
import com.meida.backend.pay.dto.*;
import com.meida.backend.base.dto.*;
import com.meida.backend.pay.service.inter.AccountReceivableService;
import com.meida.backend.pay.dao.inter.AccountReceivableDao;

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
 * AccountReceivable业务实现类
 *
 * @author BING
 * @date 2019-10-19 18:56:35
 */
@Service("backendAccountReceivableServiceImpl")
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
        CurrentUserDto currentUserDto = submitDto.getCurrentUserDto();
        if (submitDto.getOperate().equals(EOperate.ADD)) {
																																																																																																																																				item.setIsValid(ESystemStatus.Valid);
			item.setCreateDate(nowTime);
			item.setCreateUserId(currentUserDto.getUserId());
			item.setCreateUser(currentUserDto.getRealName());
			item.setUpdateDate(nowTime);
			item.setUpdateUserId(currentUserDto.getUserId());
			item.setUpdateUser(currentUserDto.getRealName());
        	return accountReceivableDao.save(item) > 0;
        } else {
			item.setUpdateDate(nowTime);
			item.setUpdateUserId(currentUserDto.getUserId());
			item.setUpdateUser(currentUserDto.getRealName());
			return accountReceivableDao.update(item) > 0;
        }
    }

	@Override
	public boolean delete(DeleteDto deleteDto) {
        boolean isFlag = accountReceivableDao.logicDelete(deleteDto) > 0;
        return isFlag;
     }

	@Override
	public boolean enable(EnableDto enableDto) {
        boolean isFlag = accountReceivableDao.enable(enableDto) > 0;
        return isFlag;
    }

	@Override
	public boolean disable(DisableDto disableDto) {
        boolean isFlag = accountReceivableDao.disable(disableDto) > 0;
        return isFlag;
     }

	@Override
	public boolean physicalDelete(PhysicalDeleteDto physicalDeleteDto) {
    	boolean isFlag = accountReceivableDao.physicalDelete(physicalDeleteDto.getIds()) > 0;
    	return isFlag;
    }
}
