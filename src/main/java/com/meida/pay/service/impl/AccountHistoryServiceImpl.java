package com.meida.pay.service.impl;

import com.meida.pay.dto.AccountHistoryListDto;
import com.meida.pay.dto.AccountHistorySubmitDto;
import com.meida.pay.po.AccountHistoryInfo;
import com.meida.front.pay.dto.*;
import com.meida.basefront.dto.*;
import com.meida.pay.service.inter.AccountHistoryService;
import com.meida.pay.dao.inter.AccountHistoryDao;

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
 * @date 2019-10-19 15:27:58
 */
@Service("accountHistoryService")
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
        CurrentMemberDto currentMemberDto = submitDto.getCurrentMemberDto();
        if (submitDto.getOperate().equals(EOperate.ADD)) {
																																																																																																																																											item.setIsValid(ESystemStatus.Valid);
			item.setCreateDate(nowTime);
			item.setCreateUserId(currentMemberDto.getMemberId().toString());
			item.setCreateUser(currentMemberDto.getAccount());
			item.setUpdateDate(nowTime);
			item.setUpdateUserId(currentMemberDto.getMemberId().toString());
			item.setUpdateUser(currentMemberDto.getAccount());
        	return accountHistoryDao.save(item) > 0;
        } else {
			item.setUpdateDate(nowTime);
			item.setUpdateUserId(currentMemberDto.getMemberId().toString());
			item.setUpdateUser(currentMemberDto.getAccount());
			return accountHistoryDao.update(item) > 0;
        }
    }

	@Override
	public boolean delete(DeleteDto deleteDto) {
        boolean isFlag = accountHistoryDao.logicDelete(deleteDto) > 0;
        return isFlag;
	}

	/**
	 * 账单号
	 *
	 * @return 返回20位订单号
	 */
	@Override
	public String getInOutNoByAccountHistory() {

		int hashCodeV = UUID.randomUUID().toString().hashCode();
		if (hashCodeV < 0) {// 有可能是负数
			hashCodeV = -hashCodeV;
		}
		// 0 代表前面补充0
		// 4 代表长度为4
		// d 代表参数为正数型
		return "H" + DateUtils.formatDate(DateUtils.now(), "yyyyMMdd" ) + String.format("%011d", hashCodeV);
		// 1+8+11
	}
}
