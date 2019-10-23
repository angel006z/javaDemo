package com.meida.pay.service.impl;

import com.meida.pay.dto.AlipayReturnListDto;
import com.meida.pay.dto.AlipayReturnSubmitDto;
import com.meida.pay.po.AlipayReturnInfo;
import com.meida.front.pay.dto.*;
import com.meida.basefront.dto.*;
import com.meida.pay.service.inter.AlipayReturnService;
import com.meida.pay.dao.inter.AlipayReturnDao;

import com.meida.common.constant.EOperate;
import com.meida.common.constant.ESystemStatus;
import com.meida.common.util.DateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * AlipayReturn业务实现类
 *
 * @author BING
 * @date 2019-10-19 15:27:58
 */
@Service("alipayReturnService")
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
        CurrentMemberDto currentMemberDto = submitDto.getCurrentMemberDto();
        if (submitDto.getOperate().equals(EOperate.ADD)) {
																																																																																																																																											item.setIsValid(ESystemStatus.Valid);
			item.setCreateDate(nowTime);
			item.setCreateUserId(currentMemberDto.getMemberId().toString());
			item.setCreateUser(currentMemberDto.getAccount());
			item.setUpdateDate(nowTime);
			item.setUpdateUserId(currentMemberDto.getMemberId().toString());
			item.setUpdateUser(currentMemberDto.getAccount());
        	return alipayReturnDao.save(item) > 0;
        } else {
			item.setUpdateDate(nowTime);
			item.setUpdateUserId(currentMemberDto.getMemberId().toString());
			item.setUpdateUser(currentMemberDto.getAccount());
			return alipayReturnDao.update(item) > 0;
        }
    }

	@Override
	public boolean delete(DeleteDto deleteDto) {
        boolean isFlag = alipayReturnDao.logicDelete(deleteDto) > 0;
        return isFlag;
	}
}
