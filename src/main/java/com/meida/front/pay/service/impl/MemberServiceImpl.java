package com.meida.front.pay.service.impl;

import com.meida.front.pay.po.MemberInfo;
import com.meida.front.pay.dto.*;
import com.meida.front.base.dto.*;
import com.meida.front.pay.service.inter.MemberService;
import com.meida.front.pay.dao.inter.MemberDao;

import com.meida.common.constant.EOperate;
import com.meida.common.constant.ESystemStatus;
import com.meida.common.util.DateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Member业务实现类
 *
 * @author BING
 * @date 2019-10-19 15:27:58
 */
@Service("memberService")
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
        CurrentMemberDto currentMemberDto = submitDto.getCurrentMemberDto();
        if (submitDto.getOperate().equals(EOperate.ADD)) {
																																																																																																																																											item.setIsValid(ESystemStatus.Valid);
			item.setCreateDate(nowTime);
			item.setCreateUserId(currentMemberDto.getMemberId().toString());
			item.setCreateUser(currentMemberDto.getAccount());
			item.setUpdateDate(nowTime);
			item.setUpdateUserId(currentMemberDto.getMemberId().toString());
			item.setUpdateUser(currentMemberDto.getAccount());
        	return memberDao.save(item) > 0;
        } else {
			item.setUpdateDate(nowTime);
			item.setUpdateUserId(currentMemberDto.getMemberId().toString());
			item.setUpdateUser(currentMemberDto.getAccount());
			return memberDao.update(item) > 0;
        }
    }

	@Override
	public boolean delete(DeleteDto deleteDto) {
        boolean isFlag = memberDao.logicDelete(deleteDto) > 0;
        return isFlag;
	}
}
