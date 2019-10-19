package com.meida.backend.pay.service.impl;

import com.meida.backend.pay.po.AccountRefundInfo;
import com.meida.backend.pay.vo.AccountRefundVo;
import com.meida.backend.pay.dto.*;
import com.meida.backend.base.dto.*;
import com.meida.backend.pay.service.inter.AccountRefundService;
import com.meida.backend.pay.dao.inter.AccountRefundDao;

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
 * AccountRefund业务实现类
 *
 * @author BING
 * @date 2019-10-19 18:56:35
 */
@Service("backendAccountRefundServiceImpl")
public class AccountRefundServiceImpl implements AccountRefundService {
	@Autowired
	private AccountRefundDao accountRefundDao;

	@Override
	public AccountRefundInfo getObjectById(Serializable id) {
        return accountRefundDao.getObjectById(id);
	}

	@Override
	public List<AccountRefundInfo> getListPage(AccountRefundListDto whereItemDto) {
        whereItemDto.getPagination().setPageIndex((whereItemDto.getPagination().getCurrentPage() - 1) * whereItemDto.getPagination().getPageSize());
        List<AccountRefundInfo> list = accountRefundDao.getListPage(whereItemDto);
        long totalRecord = accountRefundDao.getTotalRecord(whereItemDto);
        whereItemDto.getPagination().setTotalRecord(totalRecord);
        return list;
    }

	@Override
	public List<AccountRefundInfo> getListByAll() {
        return accountRefundDao.getListByAll();
     }

	@Override
	public List<AccountRefundInfo> getListByValid() {
        return accountRefundDao.getListByValid();
    }

	@Override
	public boolean submit(AccountRefundSubmitDto submitDto) {
        Date nowTime = DateUtils.now();
	    AccountRefundInfo item = submitDto.getItem();
        CurrentUserDto currentUserDto = submitDto.getCurrentUserDto();
        if (submitDto.getOperate().equals(EOperate.ADD)) {
																																																																																																																																				item.setIsValid(ESystemStatus.Valid);
			item.setCreateDate(nowTime);
			item.setCreateUserId(currentUserDto.getUserId());
			item.setCreateUser(currentUserDto.getRealName());
			item.setUpdateDate(nowTime);
			item.setUpdateUserId(currentUserDto.getUserId());
			item.setUpdateUser(currentUserDto.getRealName());
        	return accountRefundDao.save(item) > 0;
        } else {
			item.setUpdateDate(nowTime);
			item.setUpdateUserId(currentUserDto.getUserId());
			item.setUpdateUser(currentUserDto.getRealName());
			return accountRefundDao.update(item) > 0;
        }
    }

	@Override
	public boolean delete(DeleteDto deleteDto) {
        boolean isFlag = accountRefundDao.logicDelete(deleteDto) > 0;
        return isFlag;
     }

	@Override
	public boolean enable(EnableDto enableDto) {
        boolean isFlag = accountRefundDao.enable(enableDto) > 0;
        return isFlag;
    }

	@Override
	public boolean disable(DisableDto disableDto) {
        boolean isFlag = accountRefundDao.disable(disableDto) > 0;
        return isFlag;
     }

	@Override
	public boolean physicalDelete(PhysicalDeleteDto physicalDeleteDto) {
    	boolean isFlag = accountRefundDao.physicalDelete(physicalDeleteDto.getIds()) > 0;
    	return isFlag;
    }
}
