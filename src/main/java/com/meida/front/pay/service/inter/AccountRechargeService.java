package com.meida.front.pay.service.inter;

import java.io.Serializable;
import java.util.List;

import com.meida.base.vo.ResultMessage;
import com.meida.front.pay.po.AccountRechargeInfo;
import com.meida.front.pay.dto.*;
import com.meida.front.base.dto.*;

/**
 * AccountRecharge业务接口
 * 
 * @author BING
 * @date 2019-10-19 15:27:58
 */
public interface AccountRechargeService {
     /**
      * 查询单条记录
      *
      * @param id
      * @return
      */
      AccountRechargeInfo getObjectById(Serializable id);

     /**
      * 查询多条记录
      * @param whereItemDto
      * @return
      */
     List<AccountRechargeInfo> getListPage(AccountRechargeListDto whereItemDto);

     /**
      * 获取所有记录
      *
      * @return
      */
     List<AccountRechargeInfo> getListByAll();

     /**
      * 获取所有有效记录
      *
      * @return
      */
     List<AccountRechargeInfo> getListByValid();

     /**
      * 提交
      *
      * @param submitDto
      * @return
      */
     boolean submit(AccountRechargeSubmitDto submitDto);

     /**
      * 删除（无特殊要求一般为逻辑删除）
      *
      * @param deleteDto
      * @return
      */
     boolean delete(DeleteDto deleteDto);



     /**
      * 订单号是否存在
      * @param orderNo
      * @return
      */
     boolean isExistOrderNo(String orderNo);
     /**
      * 处理支付宝异步通知
      * @param alipayNotifyParamDto
      * @return
      */
     ResultMessage handleAlipayNotify(AlipayNotifyParamDto alipayNotifyParamDto);

     /**
      * 处理支付宝同步通知
      */
     ResultMessage handleAlipayReturn(AlipayReturnParamDto alipayReturnParamDto);

     AccountRechargeInfo getObjectByOrderNo(Serializable orderNo);


     /**
      * 构建充值订单
      * 产生订单号，返回相应支付路径
      */
     ResultMessage buildRechargeOrder(BuildRechargeOrderDto buildRechargeOrderDto);

     /**
      * 充值订单号
      * @return 返回20位订单号
      */
     String getOrderNoByRecharge();
}
