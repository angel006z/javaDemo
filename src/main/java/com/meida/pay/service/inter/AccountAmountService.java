package com.meida.pay.service.inter;

import java.io.Serializable;
import java.util.List;

import com.meida.pay.dto.AccountAmountListDto;
import com.meida.pay.dto.AccountAmountSubmitDto;
import com.meida.pay.po.AccountAmountInfo;
import com.meida.front.pay.dto.*;
import com.meida.basefront.dto.*;

/**
 * AccountAmount业务接口
 * 
 * @author BING
 * @date 2019-10-19 15:27:58
 */
public interface AccountAmountService {
     /**
      * 查询单条记录
      *
      * @param id
      * @return
      */
      AccountAmountInfo getObjectById(Serializable id);

     /**
      * 查询多条记录
      * @param whereItemDto
      * @return
      */
     List<AccountAmountInfo> getListPage(AccountAmountListDto whereItemDto);

     /**
      * 获取所有记录
      *
      * @return
      */
     List<AccountAmountInfo> getListByAll();

     /**
      * 获取所有有效记录
      *
      * @return
      */
     List<AccountAmountInfo> getListByValid();

     /**
      * 提交
      *
      * @param submitDto
      * @return
      */
     boolean submit(AccountAmountSubmitDto submitDto);

     /**
      * 删除（无特殊要求一般为逻辑删除）
      *
      * @param deleteDto
      * @return
      */
     boolean delete(DeleteDto deleteDto);

}
