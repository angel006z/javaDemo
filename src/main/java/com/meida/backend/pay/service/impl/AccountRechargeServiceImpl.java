package com.meida.backend.pay.service.impl;

import com.meida.backend.pay.po.AccountRechargeInfo;
import com.meida.backend.pay.vo.AccountRechargeVo;
import com.meida.backend.pay.dto.*;
import com.meida.backend.base.dto.*;
import com.meida.backend.pay.service.inter.AccountRechargeService;
import com.meida.backend.pay.dao.inter.AccountRechargeDao;

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
 * AccountRecharge业务实现类
 *
 * @author BING
 * @date 2019-10-19 18:56:35
 */
@Service("backendAccountRechargeServiceImpl")
public class AccountRechargeServiceImpl implements AccountRechargeService {
	@Autowired
	private AccountRechargeDao accountRechargeDao;

	@Override
	public AccountRechargeInfo getObjectById(Serializable id) {
        return accountRechargeDao.getObjectById(id);
	}

	@Override
	public List<AccountRechargeInfo> getListPage(AccountRechargeListDto whereItemDto) {
        whereItemDto.getPagination().setPageIndex((whereItemDto.getPagination().getCurrentPage() - 1) * whereItemDto.getPagination().getPageSize());
        List<AccountRechargeInfo> list = accountRechargeDao.getListPage(whereItemDto);
        long totalRecord = accountRechargeDao.getTotalRecord(whereItemDto);
        whereItemDto.getPagination().setTotalRecord(totalRecord);
        return list;
    }

	@Override
	public List<AccountRechargeInfo> getListByAll() {
        return accountRechargeDao.getListByAll();
     }

	@Override
	public List<AccountRechargeInfo> getListByValid() {
        return accountRechargeDao.getListByValid();
    }

	@Override
	public boolean submit(AccountRechargeSubmitDto submitDto) {
        Date nowTime = DateUtils.now();
	    AccountRechargeInfo item = submitDto.getItem();
        CurrentUserDto currentUserDto = submitDto.getCurrentUserDto();
        if (submitDto.getOperate().equals(EOperate.ADD)) {
																																																																																																																													item.setIsValid(ESystemStatus.Valid);
			item.setCreateDate(nowTime);
			item.setCreateUserId(currentUserDto.getUserId());
			item.setCreateUser(currentUserDto.getRealName());
			item.setUpdateDate(nowTime);
			item.setUpdateUserId(currentUserDto.getUserId());
			item.setUpdateUser(currentUserDto.getRealName());
        	return accountRechargeDao.save(item) > 0;
        } else {
			item.setUpdateDate(nowTime);
			item.setUpdateUserId(currentUserDto.getUserId());
			item.setUpdateUser(currentUserDto.getRealName());
			return accountRechargeDao.update(item) > 0;
        }
    }

	@Override
	public boolean delete(DeleteDto deleteDto) {
        boolean isFlag = accountRechargeDao.logicDelete(deleteDto) > 0;
        return isFlag;
     }

	@Override
	public boolean enable(EnableDto enableDto) {
        boolean isFlag = accountRechargeDao.enable(enableDto) > 0;
        return isFlag;
    }

	@Override
	public boolean disable(DisableDto disableDto) {
        boolean isFlag = accountRechargeDao.disable(disableDto) > 0;
        return isFlag;
     }

	@Override
	public boolean physicalDelete(PhysicalDeleteDto physicalDeleteDto) {
    	boolean isFlag = accountRechargeDao.physicalDelete(physicalDeleteDto.getIds()) > 0;
    	return isFlag;
    }
}
