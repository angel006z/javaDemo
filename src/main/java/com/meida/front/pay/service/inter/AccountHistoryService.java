package com.meida.front.pay.service.inter;

import java.io.Serializable;
import java.util.List;

import com.meida.front.pay.po.AccountHistoryInfo;
import com.meida.front.pay.dto.*;
import com.meida.front.base.dto.*;

/**
 * AccountHistory业务接口
 * 
 * @author BING
 * @date 2019-10-19 15:27:58
 */
public interface AccountHistoryService {
     /**
      * 查询单条记录
      *
      * @param id
      * @return
      */
      AccountHistoryInfo getObjectById(Serializable id);

     /**
      * 查询多条记录
      * @param whereItemDto
      * @return
      */
     List<AccountHistoryInfo> getListPage(AccountHistoryListDto whereItemDto);

     /**
      * 获取所有记录
      *
      * @return
      */
     List<AccountHistoryInfo> getListByAll();

     /**
      * 获取所有有效记录
      *
      * @return
      */
     List<AccountHistoryInfo> getListByValid();

     /**
      * 提交
      *
      * @param submitDto
      * @return
      */
     boolean submit(AccountHistorySubmitDto submitDto);

     /**
      * 删除（无特殊要求一般为逻辑删除）
      *
      * @param deleteDto
      * @return
      */
     boolean delete(DeleteDto deleteDto);

}
