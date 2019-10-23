package com.meida.pay.service.inter;

import java.io.Serializable;
import java.util.List;

import com.meida.base.vo.ResultMessage;
import com.meida.pay.dto.AccountRefundListDto;
import com.meida.pay.dto.AccountRefundSubmitDto;
import com.meida.pay.po.AccountRefundInfo;
import com.meida.basefront.dto.*;
import com.meida.pay.dto.OriginalRefundDto;

/**
 * AccountRefund业务接口
 * 
 * @author BING
 * @date 2019-10-19 15:27:58
 */
public interface AccountRefundService {
     /**
      * 查询单条记录
      *
      * @param id
      * @return
      */
      AccountRefundInfo getObjectById(Serializable id);

     /**
      * 查询多条记录
      * @param whereItemDto
      * @return
      */
     List<AccountRefundInfo> getListPage(AccountRefundListDto whereItemDto);

     /**
      * 获取所有记录
      *
      * @return
      */
     List<AccountRefundInfo> getListByAll();

     /**
      * 获取所有有效记录
      *
      * @return
      */
     List<AccountRefundInfo> getListByValid();

     /**
      * 提交
      *
      * @param submitDto
      * @return
      */
     boolean submit(AccountRefundSubmitDto submitDto);

     /**
      * 删除（无特殊要求一般为逻辑删除）
      *
      * @param deleteDto
      * @return
      */
     boolean delete(DeleteDto deleteDto);


     /**
      * 原路退款
      * @param originalRefundDto
      * @return
      */
     ResultMessage originalRefund(OriginalRefundDto originalRefundDto);
}
