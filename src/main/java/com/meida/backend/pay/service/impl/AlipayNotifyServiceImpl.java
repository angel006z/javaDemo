package com.meida.backend.pay.service.impl;

import com.meida.backend.pay.po.AlipayNotifyInfo;
import com.meida.backend.pay.vo.AlipayNotifyVo;
import com.meida.backend.pay.dto.*;
import com.meida.backend.base.dto.*;
import com.meida.backend.pay.service.inter.AlipayNotifyService;
import com.meida.backend.pay.dao.inter.AlipayNotifyDao;

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
 * AlipayNotify业务实现类
 *
 * @author BING
 * @date 2019-10-19 18:56:35
 */
@Service("backendAlipayNotifyServiceImpl")
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
        CurrentUserDto currentUserDto = submitDto.getCurrentUserDto();
        if (submitDto.getOperate().equals(EOperate.ADD)) {
																																																																																																																																																																																																																																																																																														item.setIsValid(ESystemStatus.Valid);
			item.setCreateDate(nowTime);
			item.setCreateUserId(currentUserDto.getUserId());
			item.setCreateUser(currentUserDto.getRealName());
			item.setUpdateDate(nowTime);
			item.setUpdateUserId(currentUserDto.getUserId());
			item.setUpdateUser(currentUserDto.getRealName());
        	return alipayNotifyDao.save(item) > 0;
        } else {
			item.setUpdateDate(nowTime);
			item.setUpdateUserId(currentUserDto.getUserId());
			item.setUpdateUser(currentUserDto.getRealName());
			return alipayNotifyDao.update(item) > 0;
        }
    }

	@Override
	public boolean delete(DeleteDto deleteDto) {
        boolean isFlag = alipayNotifyDao.logicDelete(deleteDto) > 0;
        return isFlag;
     }

	@Override
	public boolean enable(EnableDto enableDto) {
        boolean isFlag = alipayNotifyDao.enable(enableDto) > 0;
        return isFlag;
    }

	@Override
	public boolean disable(DisableDto disableDto) {
        boolean isFlag = alipayNotifyDao.disable(disableDto) > 0;
        return isFlag;
     }

	@Override
	public boolean physicalDelete(PhysicalDeleteDto physicalDeleteDto) {
    	boolean isFlag = alipayNotifyDao.physicalDelete(physicalDeleteDto.getIds()) > 0;
    	return isFlag;
    }
}
