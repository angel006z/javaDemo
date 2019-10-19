package com.meida.backend.pay.service.impl;

import com.meida.backend.pay.po.AlipayReturnInfo;
import com.meida.backend.pay.vo.AlipayReturnVo;
import com.meida.backend.pay.dto.*;
import com.meida.backend.base.dto.*;
import com.meida.backend.pay.service.inter.AlipayReturnService;
import com.meida.backend.pay.dao.inter.AlipayReturnDao;

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
 * AlipayReturn业务实现类
 *
 * @author BING
 * @date 2019-10-19 18:56:35
 */
@Service("backendAlipayReturnServiceImpl")
public class AlipayReturnServiceImpl implements AlipayReturnService {
	@Autowired
	private AlipayReturnDao alipayReturnDao;

	@Override
	public AlipayReturnInfo getObjectById(Serializable id) {
        return alipayReturnDao.getObjectById(id);
	}

	@Override
	public List<AlipayReturnInfo> getListPage(AlipayReturnListDto whereItemDto) {
        whereItemDto.getPagination().setPageIndex((whereItemDto.getPagination().getCurrentPage() - 1) * whereItemDto.getPagination().getPageSize());
        List<AlipayReturnInfo> list = alipayReturnDao.getListPage(whereItemDto);
        long totalRecord = alipayReturnDao.getTotalRecord(whereItemDto);
        whereItemDto.getPagination().setTotalRecord(totalRecord);
        return list;
    }

	@Override
	public List<AlipayReturnInfo> getListByAll() {
        return alipayReturnDao.getListByAll();
     }

	@Override
	public List<AlipayReturnInfo> getListByValid() {
        return alipayReturnDao.getListByValid();
    }

	@Override
	public boolean submit(AlipayReturnSubmitDto submitDto) {
        Date nowTime = DateUtils.now();
	    AlipayReturnInfo item = submitDto.getItem();
        CurrentUserDto currentUserDto = submitDto.getCurrentUserDto();
        if (submitDto.getOperate().equals(EOperate.ADD)) {
																																																																																																																																											item.setIsValid(ESystemStatus.Valid);
			item.setCreateDate(nowTime);
			item.setCreateUserId(currentUserDto.getUserId());
			item.setCreateUser(currentUserDto.getRealName());
			item.setUpdateDate(nowTime);
			item.setUpdateUserId(currentUserDto.getUserId());
			item.setUpdateUser(currentUserDto.getRealName());
        	return alipayReturnDao.save(item) > 0;
        } else {
			item.setUpdateDate(nowTime);
			item.setUpdateUserId(currentUserDto.getUserId());
			item.setUpdateUser(currentUserDto.getRealName());
			return alipayReturnDao.update(item) > 0;
        }
    }

	@Override
	public boolean delete(DeleteDto deleteDto) {
        boolean isFlag = alipayReturnDao.logicDelete(deleteDto) > 0;
        return isFlag;
     }

	@Override
	public boolean enable(EnableDto enableDto) {
        boolean isFlag = alipayReturnDao.enable(enableDto) > 0;
        return isFlag;
    }

	@Override
	public boolean disable(DisableDto disableDto) {
        boolean isFlag = alipayReturnDao.disable(disableDto) > 0;
        return isFlag;
     }

	@Override
	public boolean physicalDelete(PhysicalDeleteDto physicalDeleteDto) {
    	boolean isFlag = alipayReturnDao.physicalDelete(physicalDeleteDto.getIds()) > 0;
    	return isFlag;
    }
}
