package com.meida.backend.basic.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meida.backend.basic.dao.inter.IDeptDao;
import com.meida.backend.basic.dto.DeptListDto;
import com.meida.backend.basic.po.Dept;
import com.meida.backend.basic.service.inter.IDeptService;
import com.meida.base.service.impl.BaseBackendServiceImpl;
import com.meida.common.pojo.Pagination;
import com.meida.common.util.DateUtils;

@Service
@Transactional(rollbackFor = Exception.class)
public class DeptServiceImpl extends BaseBackendServiceImpl<Dept> implements IDeptService {
	
	@Autowired
	private IDeptDao dao;

	public List<Dept> searchByPageCondition(DeptListDto whereItem) {
		List<Dept> list =new ArrayList<Dept>();
		
		if(true) {
			Dept item =new Dept();
			item.setDeptId(UUID.randomUUID().toString());
			item.setDeptCode("001");
			item.setDeptName("行政人事部");
			item.setIsValid(1);
			item.setRemark("备注001");
			item.setCreateDate(DateUtils.now());
			item.setOperateDate(DateUtils.now());
			list.add(item);
		}
		
		if(true) {
			Dept item =new Dept();
			item.setDeptId(UUID.randomUUID().toString());
			item.setDeptCode("002");
			item.setDeptName("软件部");
			item.setIsValid(1);
			item.setRemark("备注002");
			item.setCreateDate(DateUtils.now());
			item.setOperateDate(DateUtils.now());
			list.add(item);
		}
		
		int totalRecord=2;
		int pageCount=1;
		Pagination pagination=new Pagination();
		pagination.setTotalRecord(totalRecord);
		pagination.setPageCount(pageCount);
		whereItem.setPagination(pagination);
		return list;
	}

	
	
}
