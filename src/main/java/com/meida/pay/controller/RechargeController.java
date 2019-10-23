package com.meida.pay.controller;

import com.meida.common.constant.ERechargeStatus;
import com.meida.pay.dto.AccountRechargeListDto;
import com.meida.pay.dto.AccountRechargeListParamDto;
import com.meida.pay.dto.BuildRechargeOrderDto;
import com.meida.pay.po.AccountRechargeInfo;
import com.meida.basefront.util.*;
import com.meida.base.vo.*;
import com.meida.basefront.dto.*;
import com.meida.pay.service.inter.AccountRechargeService;

import com.meida.common.constant.EErrorCode;
import com.meida.common.util.*;
import com.meida.pay.dto.RechargeParamDto;
import com.meida.paysdk.pojo.EPayChannel;
import com.meida.paysdk.pojo.EPayType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 充值中心
 *
 * @author BING
 * @date 2019-10-19 15:27:58
 */
@Controller
@RequestMapping("/front/pay/recharge")
public class RechargeController {

    private static final long serialVersionUID = 1L;

    @Autowired
    private AccountRechargeService accountRechargeService;

    @RequestMapping(value = "/record")
    public ModelAndView record() {
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }

    @RequestMapping(value = "/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }

    @RequestMapping(value = "/jump")
    public ModelAndView jump() {
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }

    /**
     * 查询充值记录
     *
     * @return
     */
    @RequestMapping("/searchList")
    @ResponseBody
    public String searchList(@RequestBody AccountRechargeListParamDto paramDto) {
//        ResultMessage resultValidate = ValidatorUtils.validateEntity(paramDto);
//        if (!resultValidate.getCode().equals(EErrorCode.Success)) {
//            return JsonUtils.toJSONString(resultValidate);
//        }
        if (paramDto.getPagination() == null) {
            paramDto.setPagination(new Pagination());
        }
        if (paramDto.getPagination().getCurrentPage() <= 0) {
            paramDto.getPagination().setCurrentPage(1);
        }
        if (paramDto.getPagination().getPageSize() > 100) {
            paramDto.getPagination().setPageSize(100);
        }
        Date nowTime = DateUtils.now();
        AccountRechargeListDto whereItemDto = new AccountRechargeListDto();
        whereItemDto.setCurrentMemberDto(FrontUtils.getCurrentMemberDto());
        whereItemDto.setPagination(paramDto.getPagination());
        whereItemDto.setMemberId(1l);
        whereItemDto.setRechargeStatus(ERechargeStatus.YES);
        String timeType = paramDto.getTimeType();
        Date beginRechargeDate;
        Date endRechargeDate;
        switch (timeType) {
            case "1"://今天
                beginRechargeDate = DateUtils.parseDate(DateUtils.formatDate(nowTime, "yyyy-MM-dd"), "yyyy-MM-dd");
                endRechargeDate = nowTime;
                break;
            case "2"://本周
                beginRechargeDate =DateUtils.getThisWeekMonday(nowTime);
                endRechargeDate = nowTime;
                break;
            case "3"://本月
                beginRechargeDate =DateUtils.addMonths(DateUtils.parseDate(DateUtils.formatDate(nowTime, "yyyy-MM")+"-01", "yyyy-MM-dd"),-1);
                endRechargeDate = nowTime;
                break;
            case "4"://最近三个月
            default:
                beginRechargeDate =DateUtils.addMonths(DateUtils.parseDate(DateUtils.formatDate(nowTime, "yyyy-MM-dd"), "yyyy-MM-dd"),-3);
                endRechargeDate = nowTime;
                break;
        }
        whereItemDto.setBeginRechargeDate(beginRechargeDate);
        whereItemDto.setEndRechargeDate(endRechargeDate);

        List<AccountRechargeInfo> list = accountRechargeService.getListPage(whereItemDto);
        ResultList resultList = new ResultList();
        resultList.setCode(EErrorCode.Success);
        resultList.setMessage("操作成功.");
        resultList.setData(list);
        resultList.setPagination(whereItemDto.getPagination());
        return JsonUtils.toJSONString(resultList);
    }

    /**
     * 删除操作
     *
     * @return
     */
    @RequestMapping("/deleteOperate")
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
            resultMessage.setMessage("ids参数不合法.");
            return JsonUtils.toJSONString(resultMessage);
        }

        String[] strids = paramIds.split(",");
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
            resultMessage.setMessage("ids参数不合法.");
            return JsonUtils.toJSONString(resultMessage);
        }

        DeleteDto deleteDto = new DeleteDto();
        deleteDto.setIds(ids);
        deleteDto.setCurrentMemberDto(FrontUtils.getCurrentMemberDto());
        boolean isFlag = accountRechargeService.delete(deleteDto);
        if (isFlag) {
            ResultMessage resultMessage = new ResultMessage();
            resultMessage.setCode(EErrorCode.Success);
            resultMessage.setMessage("操作成功.");
            return JsonUtils.toJSONString(resultMessage);
        } else {
            ResultMessage resultMessage = new ResultMessage();
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("操作失败.");
            return JsonUtils.toJSONString(resultMessage);
        }
    }


    /**
     * 充值
     *
     * @param paramDto
     * @return
     */
    @RequestMapping(value = "/rechargeOperate")
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
