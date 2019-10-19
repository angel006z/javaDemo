package com.meida.backend.pay.service.impl;

import com.meida.backend.pay.po.MemberInfo;
import com.meida.backend.pay.vo.MemberVo;
import com.meida.backend.pay.dto.*;
import com.meida.backend.base.dto.*;
import com.meida.backend.pay.service.inter.MemberService;
import com.meida.backend.pay.dao.inter.MemberDao;

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
 * Member业务实现类
 *
 * @author BING
 * @date 2019-10-19 18:56:35
 */
@Service("backendMemberServiceImpl")
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDao memberDao;

	@Override
	public MemberInfo getObjectById(Serializable id) {
        return memberDao.getObjectById(id);
	}

	@Override
	public List<MemberInfo> getListPage(MemberListDto whereItemDto) {
        whereItemDto.getPagination().setPageIndex((whereItemDto.getPagination().getCurrentPage() - 1) * whereItemDto.getPagination().getPageSize());
        List<MemberInfo> list = memberDao.getListPage(whereItemDto);
        long totalRecord = memberDao.getTotalRecord(whereItemDto);
        whereItemDto.getPagination().setTotalRecord(totalRecord);
        return list;
    }

	@Override
	public List<MemberInfo> getListByAll() {
        return memberDao.getListByAll();
     }

	@Override
	public List<MemberInfo> getListByValid() {
        return memberDao.getListByValid();
    }

	@Override
	public boolean submit(MemberSubmitDto submitDto) {
        Date nowTime = DateUtils.now();
	    MemberInfo item = submitDto.getItem();
        CurrentUserDto currentUserDto = submitDto.getCurrentUserDto();
        if (submitDto.getOperate().equals(EOperate.ADD)) {
																																																																																																																																											item.setIsValid(ESystemStatus.Valid);
			item.setCreateDate(nowTime);
			item.setCreateUserId(currentUserDto.getUserId());
			item.setCreateUser(currentUserDto.getRealName());
			item.setUpdateDate(nowTime);
			item.setUpdateUserId(currentUserDto.getUserId());
			item.setUpdateUser(currentUserDto.getRealName());
        	return memberDao.save(item) > 0;
        } else {
			item.setUpdateDate(nowTime);
			item.setUpdateUserId(currentUserDto.getUserId());
			item.setUpdateUser(currentUserDto.getRealName());
			return memberDao.update(item) > 0;
        }
    }

	@Override
	public boolean delete(DeleteDto deleteDto) {
        boolean isFlag = memberDao.logicDelete(deleteDto) > 0;
        return isFlag;
     }

	@Override
	public boolean enable(EnableDto enableDto) {
        boolean isFlag = memberDao.enable(enableDto) > 0;
        return isFlag;
    }

	@Override
	public boolean disable(DisableDto disableDto) {
        boolean isFlag = memberDao.disable(disableDto) > 0;
        return isFlag;
     }

	@Override
	public boolean physicalDelete(PhysicalDeleteDto physicalDeleteDto) {
    	boolean isFlag = memberDao.physicalDelete(physicalDeleteDto.getIds()) > 0;
    	return isFlag;
    }
}
