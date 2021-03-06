package com.meida.pay.service.inter;

import java.io.Serializable;
import java.util.List;

import com.meida.pay.dto.AccountPayableListDto;
import com.meida.pay.dto.AccountPayableSubmitDto;
import com.meida.pay.po.AccountPayableInfo;
import com.meida.basefront.dto.*;

/**
 * AccountPayable业务接口
 * 
 * @author BING
 * @date 2019-10-19 15:27:58
 */
public interface AccountPayableService {
     /**
      * 查询单条记录
      *
      * @param id
      * @return
      */
      AccountPayableInfo getObjectById(Serializable id);

     /**
      * 查询多条记录
      * @param whereItemDto
      * @return
      */
     List<AccountPayableInfo> getListPage(AccountPayableListDto whereItemDto);

     /**
      * 获取所有记录
      *
      * @return
      */
     List<AccountPayableInfo> getListByAll();

     /**
      * 获取所有有效记录
      *
      * @return
      */
     List<AccountPayableInfo> getListByValid();

     /**
      * 提交
      *
      * @param submitDto
      * @return
      */
     boolean submit(AccountPayableSubmitDto submitDto);

     /**
      * 删除（无特殊要求一般为逻辑删除）
      *
      * @param deleteDto
      * @return
      */
     boolean delete(DeleteDto deleteDto);


     String getPayableNoByAccountPayable();
}
