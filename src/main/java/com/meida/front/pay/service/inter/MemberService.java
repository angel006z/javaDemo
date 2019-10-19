package com.meida.front.pay.service.inter;

import java.io.Serializable;
import java.util.List;

import com.meida.front.pay.po.MemberInfo;
import com.meida.front.pay.dto.*;
import com.meida.front.base.dto.*;

/**
 * Member业务接口
 * 
 * @author BING
 * @date 2019-10-19 15:27:58
 */
public interface MemberService {
     /**
      * 查询单条记录
      *
      * @param id
      * @return
      */
      MemberInfo getObjectById(Serializable id);

     /**
      * 查询多条记录
      * @param whereItemDto
      * @return
      */
     List<MemberInfo> getListPage(MemberListDto whereItemDto);

     /**
      * 获取所有记录
      *
      * @return
      */
     List<MemberInfo> getListByAll();

     /**
      * 获取所有有效记录
      *
      * @return
      */
     List<MemberInfo> getListByValid();

     /**
      * 提交
      *
      * @param submitDto
      * @return
      */
     boolean submit(MemberSubmitDto submitDto);

     /**
      * 删除（无特殊要求一般为逻辑删除）
      *
      * @param deleteDto
      * @return
      */
     boolean delete(DeleteDto deleteDto);

}
