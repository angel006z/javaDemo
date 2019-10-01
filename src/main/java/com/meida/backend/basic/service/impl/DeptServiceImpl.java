package com.meida.backend.basic.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meida.backend.basic.dao.inter.IDeptDao;
import com.meida.backend.basic.dto.DeptListDto;
import com.meida.backend.basic.po.Dept;
import com.meida.backend.basic.service.inter.IDeptService;
import com.meida.base.vo.Pagination;
import com.meida.common.util.constant.EButtonType;

@Service
public class DeptServiceImpl implements IDeptService {

    @Autowired
    private IDeptDao deptDao;

    @Override
    public boolean addOrUpdate(Dept item, boolean isAdd) {
        if (isAdd) {
            return deptDao.save(item) > 0;
        } else {
            return deptDao.update(item) > 0;
        }
    }

    @Override
    public boolean batchExecuteByIds(Serializable[] ids, String command) {
        switch (command) {
            case "" + EButtonType.PhyDelete:
                return deptDao.deletePhysicalById(ids) > 0;
            case "" + EButtonType.Enable:
                return deptDao.deleteLogicById(ids) > 0;
            case "" + EButtonType.Disable:
                return deptDao.deleteLogicById(ids) > 0;
            default:
                return false;
        }
    }

    @Override
    public Dept getObjectById(Serializable id) {
        return deptDao.getObjectById(id);
    }

    @Override
    public List<Dept> getListByAll() {
        return deptDao.getListByAll();
    }

    @Override
    public List<Dept> getListByValid() {
        return deptDao.getListByValid();
    }

    @Override
    public List<Dept> getListByPage(DeptListDto whereItem) {
        long totalRecord = deptDao.getTotalRecord(whereItem);
        Pagination pagination = new Pagination();
        pagination.setTotalRecord(totalRecord);
        whereItem.setPagination(pagination);
        return deptDao.getListByPage(whereItem);
    }

}
