package com.meida.backend.pay.service.inter;

import java.io.Serializable;
import java.util.List;

import com.meida.backend.pay.po.AlipayNotifyInfo;
import com.meida.backend.pay.dto.*;
import com.meida.backend.base.dto.*;

/**
 * AlipayNotify业务接口
 * 支付宝支付成功通知
 * @author BING
 * @date 2019-10-19 18:56:35
 */
public interface AlipayNotifyService {
     /**
      * 查询单条记录
      *
      * @param id
      * @return
      */
      AlipayNotifyInfo getObjectById(Serializable id);

     /**
      * 查询多条记录
      * @param whereItemDto
      * @return
      */
     List<AlipayNotifyInfo> getListPage(AlipayNotifyListDto whereItemDto);

     /**
      * 获取所有记录
      *
      * @return
      */
     List<AlipayNotifyInfo> getListByAll();

     /**
      * 获取所有有效记录
      *
      * @return
      */
     List<AlipayNotifyInfo> getListByValid();

     /**
      * 提交
      *
      * @param submitDto
      * @return
      */
     boolean submit(AlipayNotifySubmitDto submitDto);

     /**
      * 删除（无特殊要求一般为逻辑删除）
      *
      * @param deleteDto
      * @return
      */
     boolean delete(DeleteDto deleteDto);

     /**
      * 启用
      *
      * @param enableDto
      * @return
      */
     boolean enable(EnableDto enableDto);

     /**
      * 禁用
      *
      * @param disableDto
      * @return
      */
     boolean disable(DisableDto disableDto);

     /**
      * 物理删除
      *
      * @param physicalDeleteDto
      * @return
      */
     boolean physicalDelete(PhysicalDeleteDto physicalDeleteDto);
}
