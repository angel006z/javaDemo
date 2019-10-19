package com.meida.front.pay.controller;

import com.meida.front.pay.po.MemberInfo;
import com.meida.front.pay.dto.*;
import com.meida.front.base.util.*;
import com.meida.base.vo.*;
import com.meida.front.base.dto.*;
import com.meida.front.pay.service.inter.MemberService;

import com.meida.common.constant.EErrorCode;
import com.meida.common.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * MemberController
 *
 * @author BING
 * @date 2019-10-19 15:27:58
 */
@Controller
@RequestMapping("/front/pay/member" )
public class MemberController {

    private static final long serialVersionUID = 1L;

    @Autowired
    private MemberService memberService;

    @RequestMapping("/list" )
    public String list() {
        return "/front/pay/Member/list";
    }

    @RequestMapping("/detail" )
    public String detail() {
        return "/front/pay/Member/detail";
    }

    /**
     * 查询多条记录
     *
     * @return
     */
    @RequestMapping("/searchList" )
    @ResponseBody
    public String searchList(@RequestBody MemberListParamDto paramDto) {
        ResultMessage resultValidate = ValidatorUtils.validateEntity(paramDto);
        if (!resultValidate.getCode().equals(EErrorCode.Success)) {
            return JsonUtils.toJSONString(resultValidate);
        }
        if (paramDto.getPagination() == null) {
            paramDto.setPagination(new Pagination());
        }
        if (paramDto.getPagination().getCurrentPage() <= 0) {
            paramDto.getPagination().setCurrentPage(1);
        }
        if (paramDto.getPagination().getPageSize() > 100) {
            paramDto.getPagination().setPageSize(100);
        }

        MemberListDto whereItemDto = new MemberListDto();
        whereItemDto.setCurrentMemberDto(FrontUtils.getCurrentMemberDto());
        whereItemDto.setPagination(paramDto.getPagination());
        whereItemDto.setNickname(paramDto.getNickname());
        whereItemDto.setAccount(paramDto.getAccount());
        whereItemDto.setTelphone(paramDto.getTelphone());
        whereItemDto.setPassword(paramDto.getPassword());
        whereItemDto.setEmail(paramDto.getEmail());
        whereItemDto.setWeixin(paramDto.getWeixin());
        whereItemDto.setQq(paramDto.getQq());
        whereItemDto.setAlipay(paramDto.getAlipay());
        whereItemDto.setWeibo(paramDto.getWeibo());
        whereItemDto.setCreateDate(paramDto.getCreateDate());
        whereItemDto.setCreateUserId(paramDto.getCreateUserId());
        whereItemDto.setCreateUser(paramDto.getCreateUser());
        whereItemDto.setUpdateDate(paramDto.getUpdateDate());
        whereItemDto.setUpdateUserId(paramDto.getUpdateUserId());
        whereItemDto.setUpdateUser(paramDto.getUpdateUser());
        whereItemDto.setIsValid(paramDto.getIsValid());
        whereItemDto.setRemark(paramDto.getRemark());
        whereItemDto.setSignature(paramDto.getSignature());

        List<MemberInfo> list = memberService.getListPage(whereItemDto);
        ResultList resultList = new ResultList();
        resultList.setCode(EErrorCode.Success);
        resultList.setMessage("操作成功." );
        resultList.setData(list);
        resultList.setPagination(whereItemDto.getPagination());
        return JsonUtils.toJSONString(resultList);
    }

    /**
     * 查询单条记录
     *
     * @return
     */
    @RequestMapping("/initSingle" )
    @ResponseBody
    public String initSingle(@RequestBody SingleParamDto paramDto) {
        ResultMessage resultValidate = ValidatorUtils.validateEntity(paramDto);
        if (!resultValidate.getCode().equals(EErrorCode.Success)) {
            return JsonUtils.toJSONString(resultValidate);
        }
        if (!RegexValidate.isUuid(paramDto.getId())) {
            ResultMessage resultMessage = new ResultMessage();
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("id参数不合法." );
            return JsonUtils.toJSONString(resultMessage);
        }
        MemberInfo item = memberService.getObjectById(paramDto.getId());
        ResultDetail resultDetail = new ResultDetail();
        resultDetail.setCode(EErrorCode.Success);
        resultDetail.setMessage("操作成功." );
        resultDetail.setData(item);
        return JsonUtils.toJSONString(resultDetail);
    }


    /**
     * 新增、修改提交操作
     *
     * @return
     */
    @RequestMapping("/submitOperate" )
    @ResponseBody
    public String submitOperate(@RequestBody MemberSubmitParamDto paramDto) {
        ResultMessage resultValidate = ValidatorUtils.validateEntity(paramDto);
        if (!resultValidate.getCode().equals(EErrorCode.Success)) {
            return JsonUtils.toJSONString(resultValidate);
        }
        ResultMessage resultValidateItem = ValidatorUtils.validateEntity(paramDto.getItem());
        if (!resultValidateItem.getCode().equals(EErrorCode.Success)) {
            return JsonUtils.toJSONString(resultValidateItem);
        }
        MemberSubmitDto submitDto = new MemberSubmitDto();
        submitDto.setOperate(paramDto.getOperate());
        submitDto.setItem(paramDto.getItem());
        submitDto.setCurrentMemberDto(FrontUtils.getCurrentMemberDto());
        boolean isFlag = memberService.submit(submitDto);
        if (isFlag) {
            ResultMessage resultMessage = new ResultMessage();
            resultMessage.setCode(EErrorCode.Success);
            resultMessage.setMessage("操作成功." );
            return JsonUtils.toJSONString(resultMessage);
        } else {
            ResultMessage resultMessage = new ResultMessage();
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("操作失败." );
            return JsonUtils.toJSONString(resultMessage);
        }
    }

    /**
     * 删除操作
     *
     * @return
     */
    @RequestMapping("/deleteOperate" )
    @ResponseBody
    public String deleteOperate(@RequestBody DeleteParamDto paramDto) {
        ResultMessage resultValidate = ValidatorUtils.validateEntity(paramDto);
        if (!resultValidate.getCode().equals(EErrorCode.Success)) {
            return JsonUtils.toJSONString(resultValidate);
        }
        String paramIds = paramDto.getIds();
        if (StringUtils.isEmpty(paramIds)) {
            ResultMessage resultMessage = new ResultMessage();
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("ids参数不合法." );
            return JsonUtils.toJSONString(resultMessage);
        }

        String[] strids = paramIds.split("," );
        ArrayList<String> arrayList = new ArrayList<String>();
        for (int i = 0; i < strids.length; i++) {
            if (RegexValidate.isUuid(strids[i])) {
                arrayList.add(strids[i]);
            }
        }
        String[] array = new String[arrayList.size()];
        String[] ids = arrayList.toArray(array);
        if (ids.length <= 0) {
            ResultMessage resultMessage = new ResultMessage();
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("ids参数不合法." );
            return JsonUtils.toJSONString(resultMessage);
        }

        DeleteDto deleteDto = new DeleteDto();
        deleteDto.setIds(ids);
        deleteDto.setCurrentMemberDto(FrontUtils.getCurrentMemberDto());
        boolean isFlag = memberService.delete(deleteDto);
        if (isFlag) {
            ResultMessage resultMessage = new ResultMessage();
            resultMessage.setCode(EErrorCode.Success);
            resultMessage.setMessage("操作成功." );
            return JsonUtils.toJSONString(resultMessage);
        } else {
            ResultMessage resultMessage = new ResultMessage();
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("操作失败." );
            return JsonUtils.toJSONString(resultMessage);
        }
    }

}
