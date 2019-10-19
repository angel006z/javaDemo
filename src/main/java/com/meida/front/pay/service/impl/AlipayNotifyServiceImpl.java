package com.meida.front.pay.service.impl;

import com.meida.front.pay.po.AlipayNotifyInfo;
import com.meida.front.pay.dto.*;
import com.meida.front.base.dto.*;
import com.meida.front.pay.service.inter.AlipayNotifyService;
import com.meida.front.pay.dao.inter.AlipayNotifyDao;

import com.meida.common.constant.EOperate;
import com.meida.common.constant.ESystemStatus;
import com.meida.common.util.DateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * AlipayNotify业务实现类
 *
 * @author BING
 * @date 2019-10-19 15:27:58
 */
@Service("alipayNotifyService")
public class AlipayNotifyServiceImpl implements AlipayNotifyService {
	@Autowired
	private AlipayNotifyDao alipayNotifyDao;

	@Override
	public AlipayNotifyInfo getObjectById(Serializable id) {
        return alipayNotifyDao.getObjectById(id);
	}

	@Override
	public List<AlipayNotifyInfo> getListPage(AlipayNotifyListDto whereItemDto) {
        whereItemDto.getPagination().setPageIndex((whereItemDto.getPagination().getCurrentPage() - 1) * whereItemDto.getPagination().getPageSize());
        List<AlipayNotifyInfo> list = alipayNotifyDao.getListPage(whereItemDto);
        long totalRecord = alipayNotifyDao.getTotalRecord(whereItemDto);
        whereItemDto.getPagination().setTotalRecord(totalRecord);
        return list;
    }

	@Override
	public List<AlipayNotifyInfo> getListByAll() {
        return alipayNotifyDao.getListByAll();
     }

	@Override
	public List<AlipayNotifyInfo> getListByValid() {
        return alipayNotifyDao.getListByValid();
    }

	@Override
	public boolean submit(AlipayNotifySubmitDto submitDto) {
        Date nowTime = DateUtils.now();
	    AlipayNotifyInfo item = submitDto.getItem();
        CurrentMemberDto currentMemberDto = submitDto.getCurrentMemberDto();
        if (submitDto.getOperate().equals(EOperate.ADD)) {
																																																																																																																																																																																																																																																																																														item.setIsValid(ESystemStatus.Valid);
			item.setCreateDate(nowTime);
			item.setCreateUserId(currentMemberDto.getMemberId().toString());
			item.setCreateUser(currentMemberDto.getAccount());
			item.setUpdateDate(nowTime);
			item.setUpdateUserId(currentMemberDto.getMemberId().toString());
			item.setUpdateUser(currentMemberDto.getAccount());
        	return alipayNotifyDao.save(item) > 0;
        } else {
			item.setUpdateDate(nowTime);
			item.setUpdateUserId(currentMemberDto.getMemberId().toString());
			item.setUpdateUser(currentMemberDto.getAccount());
			return alipayNotifyDao.update(item) > 0;
        }
    }

	@Override
	public boolean delete(DeleteDto deleteDto) {
        boolean isFlag = alipayNotifyDao.logicDelete(deleteDto) > 0;
        return isFlag;
	}
}
