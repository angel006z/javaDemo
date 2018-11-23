package com.meida.backend.basic.service.inter;

import java.util.List;

import com.meida.backend.basic.dto.DeptListDto;
import com.meida.backend.basic.po.Dept;
import com.meida.base.service.inter.IBaseBackendService;

public interface IDeptService  extends IBaseBackendService<Dept>{

	List<Dept> searchByPageCondition(DeptListDto whereItem);
}
