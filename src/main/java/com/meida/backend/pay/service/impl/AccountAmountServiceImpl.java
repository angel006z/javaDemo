package com.meida.backend.pay.service.impl;

import com.meida.backend.pay.po.AccountAmountInfo;
import com.meida.backend.pay.vo.AccountAmountVo;
import com.meida.backend.pay.dto.*;
import com.meida.backend.base.dto.*;
import com.meida.backend.pay.service.inter.AccountAmountService;
import com.meida.backend.pay.dao.inter.AccountAmountDao;

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
 * AccountAmount业务实现类
 *
 * @author BING
 * @date 2019-10-19 18:56:35
 */
@Service("backendAccountAmountServiceImpl")
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
        CurrentUserDto currentUserDto = submitDto.getCurrentUserDto();
        if (submitDto.getOperate().equals(EOperate.ADD)) {
																																																																																										item.setIsValid(ESystemStatus.Valid);
			item.setCreateDate(nowTime);
			item.setCreateUserId(currentUserDto.getUserId());
			item.setCreateUser(currentUserDto.getRealName());
			item.setUpdateDate(nowTime);
			item.setUpdateUserId(currentUserDto.getUserId());
			item.setUpdateUser(currentUserDto.getRealName());
        	return accountAmountDao.save(item) > 0;
        } else {
			item.setUpdateDate(nowTime);
			item.setUpdateUserId(currentUserDto.getUserId());
			item.setUpdateUser(currentUserDto.getRealName());
			return accountAmountDao.update(item) > 0;
        }
    }

	@Override
	public boolean delete(DeleteDto deleteDto) {
        boolean isFlag = accountAmountDao.logicDelete(deleteDto) > 0;
        return isFlag;
     }

	@Override
	public boolean enable(EnableDto enableDto) {
        boolean isFlag = accountAmountDao.enable(enableDto) > 0;
        return isFlag;
    }

	@Override
	public boolean disable(DisableDto disableDto) {
        boolean isFlag = accountAmountDao.disable(disableDto) > 0;
        return isFlag;
     }

	@Override
	public boolean physicalDelete(PhysicalDeleteDto physicalDeleteDto) {
    	boolean isFlag = accountAmountDao.physicalDelete(physicalDeleteDto.getIds()) > 0;
    	return isFlag;
    }
}
