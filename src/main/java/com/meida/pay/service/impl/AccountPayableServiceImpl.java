package com.meida.pay.service.impl;

import com.meida.pay.dto.AccountPayableListDto;
import com.meida.pay.dto.AccountPayableSubmitDto;
import com.meida.pay.po.AccountPayableInfo;
import com.meida.front.pay.dto.*;
import com.meida.basefront.dto.*;
import com.meida.pay.service.inter.AccountPayableService;
import com.meida.pay.dao.inter.AccountPayableDao;

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

	/**
	 * 付款单号
	 *
	 * @return 返回20位订单号
	 */
	public String getPayableNoByAccountPayable() {
		int hashCodeV = UUID.randomUUID().toString().hashCode();
		if (hashCodeV < 0) {// 有可能是负数
			hashCodeV = -hashCodeV;
		}
		// 0 代表前面补充0
		// 4 代表长度为4
		// d 代表参数为正数型
		return "P" + DateUtils.formatDate(DateUtils.now(), "yyyyMMdd") + String.format("%011d", hashCodeV);
		// 1+8+11
	}
}
