package com.meida.pay.service.inter;

import java.io.Serializable;
import java.util.List;

import com.meida.pay.dto.AccountReceivableListDto;
import com.meida.pay.dto.AccountReceivableSubmitDto;
import com.meida.pay.po.AccountReceivableInfo;
import com.meida.front.pay.dto.*;
import com.meida.basefront.dto.*;

/**
 * AccountReceivable业务接口
 * 
 * @author BING
 * @date 2019-10-19 15:27:58
 */
public interface AccountReceivableService {
     /**
      * 查询单条记录
      *
      * @param id
      * @return
      */
      AccountReceivableInfo getObjectById(Serializable id);

     /**
      * 查询多条记录
      * @param whereItemDto
      * @return
      */
     List<AccountReceivableInfo> getListPage(AccountReceivableListDto whereItemDto);

     /**
      * 获取所有记录
      *
      * @return
      */
     List<AccountReceivableInfo> getListByAll();

     /**
      * 获取所有有效记录
      *
      * @return
      */
     List<AccountReceivableInfo> getListByValid();

     /**
      * 提交
      *
      * @param submitDto
      * @return
      */
     boolean submit(AccountReceivableSubmitDto submitDto);

     /**
      * 删除（无特殊要求一般为逻辑删除）
      *
      * @param deleteDto
      * @return
      */
     boolean delete(DeleteDto deleteDto);
     String getReceivableNoByAccountReceivable();
}
