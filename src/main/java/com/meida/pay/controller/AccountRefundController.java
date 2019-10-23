package com.meida.pay.controller;

import com.meida.pay.dto.*;
import com.meida.pay.service.inter.AccountRechargeService;
import com.meida.pay.po.AccountRefundInfo;
import com.meida.basefront.util.*;
import com.meida.base.vo.*;
import com.meida.basefront.dto.*;
import com.meida.pay.service.inter.AccountRefundService;

import com.meida.common.constant.EErrorCode;
import com.meida.common.util.*;
import com.meida.paysdk.pojo.EPayChannel;
import com.meida.paysdk.pojo.EPayType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * AccountRefundController
 *
 * @author BING
 * @date 2019-10-19 15:27:58
 */
@Controller
@RequestMapping("/front/pay/accountRefund" )
public class AccountRefundController {

    private static final long serialVersionUID = 1L;

    @Autowired
    private AccountRefundService accountRefundService;

    @RequestMapping("/list" )
    public String list() {
        return "/front/pay/AccountRefund/list";
    }

    @RequestMapping("/detail" )
    public String detail() {
        return "/front/pay/AccountRefund/detail";
    }

    /**
     * 查询多条记录
     *
     * @return
     */
    @RequestMapping("/searchList" )
    @ResponseBody
    public String searchList(@RequestBody AccountRefundListParamDto paramDto) {
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

        AccountRefundListDto whereItemDto = new AccountRefundListDto();
        whereItemDto.setCurrentMemberDto(FrontUtils.getCurrentMemberDto());
        whereItemDto.setPagination(paramDto.getPagination());
        whereItemDto.setRefundNo(paramDto.getRefundNo());
        whereItemDto.setMemberId(paramDto.getMemberId());
        whereItemDto.setOrderNo(paramDto.getOrderNo());
        whereItemDto.setTradeNo(paramDto.getTradeNo());
        whereItemDto.setRefundDate(paramDto.getRefundDate());
        whereItemDto.setRefundAmount(paramDto.getRefundAmount());
        whereItemDto.setRefundReason(paramDto.getRefundReason());
        whereItemDto.setRefundStatus(paramDto.getRefundStatus());
        whereItemDto.setCreateDate(paramDto.getCreateDate());
        whereItemDto.setCreateUserId(paramDto.getCreateUserId());
        whereItemDto.setCreateUser(paramDto.getCreateUser());
        whereItemDto.setUpdateDate(paramDto.getUpdateDate());
        whereItemDto.setUpdateUserId(paramDto.getUpdateUserId());
        whereItemDto.setUpdateUser(paramDto.getUpdateUser());
        whereItemDto.setIsValid(paramDto.getIsValid());
        whereItemDto.setRemark(paramDto.getRemark());
        whereItemDto.setSignature(paramDto.getSignature());

        List<AccountRefundInfo> list = accountRefundService.getListPage(whereItemDto);
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
        AccountRefundInfo item = accountRefundService.getObjectById(paramDto.getId());
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
    public String submitOperate(@RequestBody AccountRefundSubmitParamDto paramDto) {
        ResultMessage resultValidate = ValidatorUtils.validateEntity(paramDto);
        if (!resultValidate.getCode().equals(EErrorCode.Success)) {
            return JsonUtils.toJSONString(resultValidate);
        }
        ResultMessage resultValidateItem = ValidatorUtils.validateEntity(paramDto.getItem());
        if (!resultValidateItem.getCode().equals(EErrorCode.Success)) {
            return JsonUtils.toJSONString(resultValidateItem);
        }
        AccountRefundSubmitDto submitDto = new AccountRefundSubmitDto();
        submitDto.setOperate(paramDto.getOperate());
        submitDto.setItem(paramDto.getItem());
        submitDto.setCurrentMemberDto(FrontUtils.getCurrentMemberDto());
        boolean isFlag = accountRefundService.submit(submitDto);
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
        boolean isFlag = accountRefundService.delete(deleteDto);
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


    @Autowired
    private AccountRechargeService accountRechargeService;

    @RequestMapping(value = "/index" )
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }

    @RequestMapping(value = "/jump" )
    public ModelAndView jump() {
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }

    @RequestMapping(value = "/rechargeOperate" )
    @ResponseBody
    public String rechargeOperate(@RequestBody RechargeParamDto paramDto) {
        String payChannel = paramDto.getPayChannel();
        String payType = "other";
        if (payChannel.equals(EPayChannel.Alipay_PAGE)) {
            payType = EPayType.Alipay;
        } else if (payChannel.equals(EPayChannel.Alipay_NATIVE)) {
            payType = EPayType.Alipay;
        } else if (payChannel.equals(EPayChannel.Weixin_NATIVE)) {
            payType = EPayType.Weixin;
        } else {
            payType = "other";
            payChannel = "other";
        }

        BuildRechargeOrderDto buildRechargeOrderDto = new BuildRechargeOrderDto();
        buildRechargeOrderDto.setRechargeMemberId(FrontUtils.getCurrentMemberDto().getMemberId());
        buildRechargeOrderDto.setPayType(payType);
        buildRechargeOrderDto.setPayChannel(payChannel);
        buildRechargeOrderDto.setTotalFee(paramDto.getTotalFee());
        buildRechargeOrderDto.setCurrentMemberDto(FrontUtils.getCurrentMemberDto());

        ResultMessage resultMessage = accountRechargeService.buildRechargeOrder(buildRechargeOrderDto);
        return JsonUtils.toJSONString(resultMessage);
    }
}
