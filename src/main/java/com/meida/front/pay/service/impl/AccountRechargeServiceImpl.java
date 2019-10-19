package com.meida.front.pay.service.impl;

import com.meida.base.vo.ResultMessage;
import com.meida.common.constant.EErrorCode;
import com.meida.common.constant.ERechargeStatus;
import com.meida.common.util.StringUtils;
import com.meida.front.pay.dao.inter.*;
import com.meida.front.pay.po.*;
import com.meida.front.pay.dto.*;
import com.meida.front.base.dto.*;
import com.meida.front.pay.service.inter.AccountHistoryService;
import com.meida.front.pay.service.inter.AccountRechargeService;

import com.meida.common.constant.EOperate;
import com.meida.common.constant.ESystemStatus;
import com.meida.common.util.DateUtils;

import com.meida.pay.pojo.*;
import com.meida.pay.service.inter.ITradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * AccountRecharge业务实现类
 *
 * @author BING
 * @date 2019-10-19 15:27:58
 */
@Service("accountRechargeService")
public class AccountRechargeServiceImpl implements AccountRechargeService {
    @Autowired
    private AccountRechargeDao accountRechargeDao;

    @Autowired
    private AccountReceivableDao accountReceivableDao;

    @Autowired
    private AccountHistoryDao accountHistoryDao;

    @Autowired
    private AccountHistoryService accountHistoryService;

    @Autowired
    private AccountAmountDao accountAmountDao;

    @Autowired
    private AlipayNotifyDao alipayNotifyDao;

    @Autowired
    private AlipayReturnDao alipayReturnDao;

    @Autowired
    private ITradeService tradeService;

    @Override
    public AccountRechargeInfo getObjectById(Serializable id) {
        return accountRechargeDao.getObjectById(id);
    }

    @Override
    public List<AccountRechargeInfo> getListPage(AccountRechargeListDto whereItemDto) {
        whereItemDto.getPagination().setPageIndex((whereItemDto.getPagination().getCurrentPage() - 1) * whereItemDto.getPagination().getPageSize());
        List<AccountRechargeInfo> list = accountRechargeDao.getListPage(whereItemDto);
        long totalRecord = accountRechargeDao.getTotalRecord(whereItemDto);
        whereItemDto.getPagination().setTotalRecord(totalRecord);
        return list;
    }

    @Override
    public List<AccountRechargeInfo> getListByAll() {
        return accountRechargeDao.getListByAll();
    }

    @Override
    public List<AccountRechargeInfo> getListByValid() {
        return accountRechargeDao.getListByValid();
    }

    @Override
    public boolean submit(AccountRechargeSubmitDto submitDto) {
        Date nowTime = DateUtils.now();
        AccountRechargeInfo item = submitDto.getItem();
        CurrentMemberDto currentMemberDto = submitDto.getCurrentMemberDto();
        if (submitDto.getOperate().equals(EOperate.ADD)) {
            item.setIsValid(ESystemStatus.Valid);
            item.setCreateDate(nowTime);
            item.setCreateUserId(currentMemberDto.getMemberId().toString());
            item.setCreateUser(currentMemberDto.getAccount());
            item.setUpdateDate(nowTime);
            item.setUpdateUserId(currentMemberDto.getMemberId().toString());
            item.setUpdateUser(currentMemberDto.getAccount());
            return accountRechargeDao.save(item) > 0;
        } else {
            item.setUpdateDate(nowTime);
            item.setUpdateUserId(currentMemberDto.getMemberId().toString());
            item.setUpdateUser(currentMemberDto.getAccount());
            return accountRechargeDao.update(item) > 0;
        }
    }

    @Override
    public boolean delete(DeleteDto deleteDto) {
        boolean isFlag = accountRechargeDao.logicDelete(deleteDto) > 0;
        return isFlag;
    }


    @Override
    public boolean isExistOrderNo(String orderNo) {
        return accountRechargeDao.isExistOrderNo(orderNo) > 0;
    }

    @Override
    public AccountRechargeInfo getObjectByOrderNo(Serializable orderNo) {
        return accountRechargeDao.getObjectByOrderNo(orderNo);
    }

    /**
     * 待测试事务
     *
     * @param alipayNotifyParamDto
     * @return
     */
    @Transactional
    @Override
    public ResultMessage handleAlipayNotify(AlipayNotifyParamDto alipayNotifyParamDto) {
        ResultMessage resultMessage = new ResultMessage();
        if (alipayNotifyParamDto == null) {
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("通知接收参数为空");
            return resultMessage;
        }

        String trade_no = alipayNotifyParamDto.getTrade_no();
        if (StringUtils.isEmpty(trade_no)) {
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("支付宝交易号参数为空");
            return resultMessage;
        }

        String orderNo = alipayNotifyParamDto.getOut_trade_no();
        if (StringUtils.isEmpty(orderNo)) {
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("订单号参数为空");
            return resultMessage;
        }

//		String trade_status = alipayNotifyParamDto.getTrade_status();
//		if (StringUtils.isEmpty(trade_status)) {
//			resultMessage.setCode(EErrorCode.Error);
//			resultMessage.setMessage("支付宝交易状态参数为空");
//			return resultMessage;
//		}

        // 查询支付宝是否支付成功
//		Boolean isAlipayPaySuccess = trade_status.equals(AlipayTradeStatus.TRADE_SUCCESS)
//				|| trade_status.equals(AlipayTradeStatus.TRADE_FINISHED);


        ParametersTradeQuery parametersTradeQuery = new ParametersTradeQuery();
        parametersTradeQuery.setPayType(EPayType.Alipay);
        parametersTradeQuery.setOut_trade_no(orderNo);
        parametersTradeQuery.setTrade_no(trade_no);
        ResultMessage tradeIsPaySuccess = tradeService.tradeIsPaySuccess(parametersTradeQuery);
        if (!tradeIsPaySuccess.getCode().equals(EErrorCode.Success)) {
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("支付宝交易号：" + trade_no + "业务订单号：" + orderNo + "未支付成功.");
            return resultMessage;
        }


        AccountRechargeInfo queryRecharge = accountRechargeDao.getObjectByOrderNo(orderNo);
        if (queryRecharge == null) {
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("业务订单号：" + orderNo + "充值订单不存在");
            return resultMessage;
        }

        if (queryRecharge.getRechargeStatus().equals("yes")) {
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("业务订单号：" + orderNo + "充值订单状态已经是支付状态");
            return resultMessage;
        }

        long totalAmount = alipayNotifyParamDto.getTotal_amount().multiply(new BigDecimal(100)).longValue();
        if (totalAmount != queryRecharge.getRechargeAmount().multiply(new BigDecimal(100)).longValue()) {
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("业务订单号：" + orderNo + "充值订单金额和支付宝通知金额不相等");
            return resultMessage;
        }

        Date nowTime = DateUtils.now();

        // 事务begin
        Boolean isExistAlipayNotify = alipayNotifyDao.isExistOrderNo(orderNo) > 0;
        if (isExistAlipayNotify == false) {
            // 新增支付宝通知记录
            AlipayNotifyInfo alipayNotify = new AlipayNotifyInfo();
            alipayNotify.setAppId(alipayNotifyParamDto.getApp_id());
            alipayNotify.setAuthAppId(alipayNotifyParamDto.getAuth_app_id());
            alipayNotify.setBody(alipayNotifyParamDto.getBody());
            alipayNotify.setBuyerId(alipayNotifyParamDto.getBuyer_id());
            alipayNotify.setBuyerPayAmount(alipayNotifyParamDto.getBuyer_pay_amount());
            alipayNotify.setCharset(alipayNotifyParamDto.getCharset());
            alipayNotify.setFundBillList(alipayNotifyParamDto.getFund_bill_list());
            alipayNotify.setGmtClose(alipayNotifyParamDto.getGmt_close());
            alipayNotify.setGmtCreate(alipayNotifyParamDto.getGmt_create());
            alipayNotify.setGmtPayment(alipayNotifyParamDto.getGmt_payment());
            alipayNotify.setGmtRefund(alipayNotifyParamDto.getGmt_refund());
            alipayNotify.setInvoiceAmount(alipayNotifyParamDto.getInvoice_amount());
            alipayNotify.setNotifyId(alipayNotifyParamDto.getNotify_id());
            alipayNotify.setNotifyTime(alipayNotifyParamDto.getNotify_time());
            alipayNotify.setNotifyType(alipayNotifyParamDto.getNotify_type());
            alipayNotify.setOutBizNo(alipayNotifyParamDto.getOut_biz_no());
            alipayNotify.setOutTradeNo(alipayNotifyParamDto.getOut_trade_no());
            alipayNotify.setPassbackParams(alipayNotifyParamDto.getPassback_params());
            alipayNotify.setPointAmount(alipayNotifyParamDto.getPoint_amount());
            alipayNotify.setReceiptAmount(alipayNotifyParamDto.getReceipt_amount());
            alipayNotify.setRefundFee(alipayNotifyParamDto.getRefund_fee());
            alipayNotify.setSellerId(alipayNotifyParamDto.getSeller_id());
            alipayNotify.setSign(alipayNotifyParamDto.getSign());
            alipayNotify.setSignType(alipayNotifyParamDto.getSign_type());
            alipayNotify.setSubject(alipayNotifyParamDto.getSubject());
            alipayNotify.setTotalAmount(alipayNotifyParamDto.getTotal_amount());
            alipayNotify.setTradeNo(alipayNotifyParamDto.getTrade_no());
            alipayNotify.setTradeStatus(alipayNotifyParamDto.getTrade_status());
            alipayNotify.setVersion(alipayNotifyParamDto.getVersion());
            alipayNotify.setVoucherDetailList(alipayNotifyParamDto.getVoucher_detail_list());
            alipayNotify.setCreateDate(nowTime);
            alipayNotify.setCreateUserId("系统id");
            alipayNotify.setCreateUser("系统");
            alipayNotify.setUpdateDate(nowTime);
            alipayNotify.setUpdateUserId("系统id");
            alipayNotify.setUpdateUser("系统");
            alipayNotify.setIsValid(ESystemStatus.Valid);
            alipayNotify.setRemark("系统自动记录");
            alipayNotify.setSignature("待签名");
            Boolean isFlagAlipayNotify = alipayNotifyDao.save(alipayNotify) > 0;
            if (isFlagAlipayNotify == false) {
                resultMessage.setCode(EErrorCode.Error);
                resultMessage.setMessage("新增支付宝通知记录错误");
                return resultMessage;
            }
        }


        // 修改充值记录为已充值
        AccountRechargeInfo accountRecharge = new AccountRechargeInfo();
        accountRecharge.setOrderNo(orderNo);
        accountRecharge.setRechargeStatus(ERechargeStatus.YES);
        accountRecharge.setUpdateDate(nowTime);
        accountRecharge.setUpdateUserId("系统id");
        accountRecharge.setUpdateUser("系统");
        boolean isFlagPay = accountRechargeDao.updateByOrderNo(accountRecharge) > 0;


        // 入账记录
        Long memberId = queryRecharge.getMemberId();
        AccountReceivableInfo accountReceivable = new AccountReceivableInfo();
        accountReceivable.setMemberId(memberId);
        accountReceivable.setOrderNo(orderNo);
        accountReceivable.setReceivableDate(nowTime);
        accountReceivable.setReceivableAmount(alipayNotifyParamDto.getTotal_amount());
        accountReceivable.setReceivableType("alipay");
        accountReceivable.setReceivableChannel("alipay");
        accountReceivable.setReceivableReason("正常充值");
        accountReceivable.setCreateDate(nowTime);
        accountReceivable.setCreateUserId("系统id");
        accountReceivable.setCreateUser("系统");
        accountReceivable.setUpdateDate(nowTime);
        accountReceivable.setUpdateUserId("系统id");
        accountReceivable.setUpdateUser("系统");
        accountReceivable.setIsValid(ESystemStatus.Valid);
        accountReceivable.setRemark("系统自动记录");
        accountReceivable.setSignature("待签名");
        boolean isFlagAccountReceivable = accountReceivableDao.save(accountReceivable) > 0;

        //账单记录
        AccountHistoryInfo accountHistory = new AccountHistoryInfo();
        accountHistory.setInOutNo(accountHistoryService.getInOutNoByAccountHistory());
        accountHistory.setMemberId(memberId);
        accountHistory.setOrderNo(orderNo);
        accountHistory.setInOutAmount(alipayNotifyParamDto.getTotal_amount());
        accountHistory.setInOutDate(nowTime);
        accountHistory.setInOutType("alipay");
        accountHistory.setInOutChannel("alipay");
        accountHistory.setInOutStatus("yes");
        accountHistory.setAccountHistoryType("in");
        accountHistory.setCreateDate(nowTime);
        accountHistory.setCreateUserId("系统id");
        accountHistory.setCreateUser("系统");
        accountHistory.setUpdateDate(nowTime);
        accountHistory.setUpdateUserId("系统id");
        accountHistory.setUpdateUser("系统");
        accountHistory.setIsValid(ESystemStatus.Valid);
        accountHistory.setRemark("系统自动记录");
        accountHistory.setSignature("待签名");
        boolean isFlagAccountHistory = accountHistoryDao.save(accountHistory) > 0;

        // 总资金
        boolean isFlagAccountAmount = false;
        AccountAmountInfo queryAccountAmount = accountAmountDao.getObjectByMemberId(memberId);
        if (queryAccountAmount != null) {
            AccountAmountInfo accountAmount = new AccountAmountInfo();
            accountAmount.setMemberId(memberId);
            BigDecimal totalMoney = queryAccountAmount.getTotalAmount().add(alipayNotifyParamDto.getTotal_amount());
            accountAmount.setTotalAmount(totalMoney);
            accountAmount.setUpdateDate(nowTime);
            accountAmount.setUpdateUserId("系统id");
            accountAmount.setUpdateUser("系统");
            isFlagAccountAmount = accountAmountDao.updateByMemberId(accountAmount) > 0;
        } else {
            AccountAmountInfo accountAmount = new AccountAmountInfo();
            accountAmount.setMemberId(memberId);
            accountAmount.setTotalAmount(alipayNotifyParamDto.getTotal_amount());
            accountAmount.setCreateDate(nowTime);
            accountAmount.setCreateUserId("系统id");
            accountAmount.setCreateUser("系统");
            accountAmount.setUpdateDate(nowTime);
            accountAmount.setUpdateUserId("系统id");
            accountAmount.setUpdateUser("系统");
            accountAmount.setIsValid(ESystemStatus.Valid);
            accountAmount.setRemark("系统自动记录");
            accountAmount.setSignature("待签名");
            isFlagAccountAmount = accountAmountDao.save(accountAmount) > 0;
        }
        System.out.println("isFlagAccountAmount:" + isFlagAccountAmount);
        // 事务end

        resultMessage.setCode(EErrorCode.Success);
        resultMessage.setMessage("操作成功");
        return resultMessage;
    }

    @Transactional
    @Override
    public ResultMessage handleAlipayReturn(AlipayReturnParamDto alipayReturnParamDto) {
        ResultMessage resultMessage = new ResultMessage();
        if (alipayReturnParamDto == null) {
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("通知接收参数为空");
            return resultMessage;
        }

        String trade_no = alipayReturnParamDto.getTrade_no();
        if (StringUtils.isEmpty(trade_no)) {
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("支付宝交易号参数为空");
            return resultMessage;
        }

        String orderNo = alipayReturnParamDto.getOut_trade_no();
        if (StringUtils.isEmpty(orderNo)) {
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("订单号参数为空");
            return resultMessage;
        }


        ParametersTradeQuery parametersTradeQuery = new ParametersTradeQuery();
        parametersTradeQuery.setPayType(EPayType.Alipay);
        parametersTradeQuery.setOut_trade_no(orderNo);
        parametersTradeQuery.setTrade_no(trade_no);
        ResultMessage tradeIsPaySuccess = tradeService.tradeIsPaySuccess(parametersTradeQuery);
        if (!tradeIsPaySuccess.getCode().equals(EErrorCode.Success)) {
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("支付宝交易号：" + trade_no + "业务订单号：" + orderNo + "未支付成功.");
            return resultMessage;
        }


        AccountRechargeInfo queryReharge = accountRechargeDao.getObjectByOrderNo(orderNo);
        if (queryReharge == null) {
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("业务订单号：" + orderNo + "充值订单不存在");
            return resultMessage;
        }

        if (queryReharge.getRechargeStatus().equals("yes")) {
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("业务订单号：" + orderNo + "充值订单状态已经是支付状态");
            return resultMessage;
        }

        long total_amount = alipayReturnParamDto.getTotal_amount().multiply(new BigDecimal(100)).longValue();
        if (total_amount != queryReharge.getRechargeAmount().multiply(new BigDecimal(100)).longValue()) {
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("业务订单号：" + orderNo + "充值订单金额和支付宝通知金额不相等");
            return resultMessage;
        }

        Date nowTime = DateUtils.now();

        // 事务begin
        Boolean isExistAlipayReturn = alipayReturnDao.isExistOrderNo(orderNo) > 0;
        if (isExistAlipayReturn == false) {
            // 新增支付宝通知记录
            AlipayReturnInfo alipayReturn = new AlipayReturnInfo();
            alipayReturn.setCode(alipayReturnParamDto.getCode());
            alipayReturn.setMsg(alipayReturnParamDto.getMsg());
            alipayReturn.setCharset(alipayReturnParamDto.getCharset());
            alipayReturn.setOutTradeNo(alipayReturnParamDto.getOut_trade_no());
            //alipayReturn.setMethod(alipayReturnParamDto.getMethod());
            alipayReturn.setTotalAmount(alipayReturnParamDto.getTotal_amount());
            //alipayReturn.setSign(alipayReturnParamDto.getSign());
            alipayReturn.setTradeNo(alipayReturnParamDto.getTrade_no());
            //alipayReturn.setAuthAppId(alipayReturnParamDto.getAuth_app_id());
            //alipayReturn.setVersion(alipayReturnParamDto.getVersion());
            alipayReturn.setAppId(alipayReturnParamDto.getApp_id());
            //alipayReturn.setSignType(alipayReturnParamDto.getSign_type());
            alipayReturn.setSellerId(alipayReturnParamDto.getSeller_id());
            //alipayReturn.setTimestamp(alipayReturnParamDto.getTimestamp());
            alipayReturn.setCreateDate(nowTime);
            alipayReturn.setCreateUserId("系统id");
            alipayReturn.setCreateUser("系统");
            alipayReturn.setUpdateDate(nowTime);
            alipayReturn.setUpdateUserId("系统id");
            alipayReturn.setUpdateUser("系统");
            alipayReturn.setIsValid(ESystemStatus.Valid);
            alipayReturn.setRemark("系统自动记录");
            alipayReturn.setSignature("待签名");
            Boolean isFlagAlipayReturn = alipayReturnDao.save(alipayReturn) > 0;
            if (isFlagAlipayReturn == false) {
                resultMessage.setCode(EErrorCode.Error);
                resultMessage.setMessage("新增支付宝通知记录错误");
                return resultMessage;
            }
        }


        // 修改充值记录为已充值
        AccountRechargeInfo recharge = new AccountRechargeInfo();
        recharge.setOrderNo(orderNo);
        recharge.setRechargeStatus(ERechargeStatus.YES);
        recharge.setUpdateDate(nowTime);
        recharge.setUpdateUserId("系统id");
        recharge.setUpdateUser("系统");
        boolean isFlagPay = accountRechargeDao.updateByOrderNo(recharge) > 0;


        // 入账记录
        Long memberId = queryReharge.getMemberId();
        AccountReceivableInfo accountReceivable = new AccountReceivableInfo();
        accountReceivable.setMemberId(memberId);
        accountReceivable.setOrderNo(orderNo);
        accountReceivable.setReceivableDate(nowTime);
        accountReceivable.setReceivableAmount(alipayReturnParamDto.getTotal_amount());
        accountReceivable.setReceivableType("alipay");
        accountReceivable.setReceivableChannel("alipay");
        accountReceivable.setReceivableReason("正常充值");
        accountReceivable.setCreateDate(nowTime);
        accountReceivable.setCreateUserId("系统id");
        accountReceivable.setCreateUser("系统");
        accountReceivable.setUpdateDate(nowTime);
        accountReceivable.setUpdateUserId("系统id");
        accountReceivable.setUpdateUser("系统");
        accountReceivable.setIsValid(ESystemStatus.Valid);
        accountReceivable.setRemark("系统自动记录");
        accountReceivable.setSignature("待签名");
        accountReceivableDao.save(accountReceivable);

        //账单记录
        AccountHistoryInfo accountHistory = new AccountHistoryInfo();
        accountHistory.setInOutNo(accountHistoryService.getInOutNoByAccountHistory());
        accountHistory.setMemberId(memberId);
        accountHistory.setOrderNo(orderNo);
        accountHistory.setInOutAmount(alipayReturnParamDto.getTotal_amount());
        accountHistory.setInOutDate(nowTime);
        accountHistory.setInOutType("alipay");
        accountHistory.setInOutChannel("alipay");
        accountHistory.setInOutStatus("yes");
        accountHistory.setAccountHistoryType("in");
        accountHistory.setCreateDate(nowTime);
        accountHistory.setCreateUserId("系统id");
        accountHistory.setCreateUser("系统");
        accountHistory.setUpdateDate(nowTime);
        accountHistory.setUpdateUserId("系统id");
        accountHistory.setUpdateUser("系统");
        accountHistory.setIsValid(ESystemStatus.Valid);
        accountHistory.setRemark("系统自动记录");
        accountHistory.setSignature("待签名");
        boolean isFlagAccountHistory = accountHistoryDao.save(accountHistory) > 0;

        // 总资金
        boolean isFlagAccountAmount = false;
        AccountAmountInfo queryAccountAmount = accountAmountDao.getObjectByMemberId(memberId);
        if (queryAccountAmount != null) {
            AccountAmountInfo accountAmount = new AccountAmountInfo();
            accountAmount.setMemberId(memberId);
            BigDecimal totalMoney = queryAccountAmount.getTotalAmount().add(alipayReturnParamDto.getTotal_amount());
            accountAmount.setTotalAmount(totalMoney);
            accountAmount.setUpdateDate(nowTime);
            accountAmount.setUpdateUserId("系统id");
            accountAmount.setUpdateUser("系统");
            isFlagAccountAmount = accountAmountDao.updateByMemberId(accountAmount) > 0;
        } else {
            AccountAmountInfo accountAmount = new AccountAmountInfo();
            accountAmount.setMemberId(memberId);
            accountAmount.setTotalAmount(alipayReturnParamDto.getTotal_amount());
            accountAmount.setCreateDate(nowTime);
            accountAmount.setCreateUserId("系统id");
            accountAmount.setCreateUser("系统");
            accountAmount.setUpdateDate(nowTime);
            accountAmount.setUpdateUserId("系统id");
            accountAmount.setUpdateUser("系统");
            accountAmount.setIsValid(ESystemStatus.Valid);
            accountAmount.setRemark("系统自动记录");
            accountAmount.setSignature("待签名");
            isFlagAccountAmount = accountAmountDao.save(accountAmount) > 0;
        }
        System.out.println("isFlagAccountAmount:" + isFlagAccountAmount);
        // 事务end

        resultMessage.setCode(EErrorCode.Success);
        resultMessage.setMessage("操作成功");
        return resultMessage;
    }


    /**
     * 构建充值订单
     * 产生订单号，返回相应支付路径
     * 网页支付：微信返回二维码，支付宝返回支付宝付款路径
     */
    @Transactional
    @Override
    public ResultMessage buildRechargeOrder(BuildRechargeOrderDto buildRehargeOrderDto) {
        CurrentMemberDto currentMember = buildRehargeOrderDto.getCurrentMemberDto();

        Date nowTime = DateUtils.now();
        ResultMessage resultMessage = new ResultMessage();
        String orderNo = getOrderNoByRecharge();
        if (this.isExistOrderNo(orderNo)) {
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("该订单号已存在，请重新充值");
            return resultMessage;
        }

        BigDecimal total_fee = buildRehargeOrderDto.getTotalFee();
        if (total_fee.signum() <= 0) {
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("充值金额必需大于0，请重新充值");
            return resultMessage;
        }

        Long chargeMemberId = buildRehargeOrderDto.getRechargeMemberId();
        if (chargeMemberId <= 0) {
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("充值会员id不能为空");
            return resultMessage;
        }

        String payType = buildRehargeOrderDto.getPayType();
        String payChannel = buildRehargeOrderDto.getPayChannel();

        // 系统生成订单信息
        AccountRechargeInfo recharge = new AccountRechargeInfo();
        recharge.setMemberId(chargeMemberId);
        recharge.setOrderNo(orderNo);
        recharge.setRechargeAmount(total_fee);
        recharge.setRechargeDate(nowTime);
        recharge.setRechargeType(payType);
        recharge.setRechargeChannel(payChannel);
        recharge.setRechargeStatus(ERechargeStatus.NO);
        recharge.setCreateDate(nowTime);
        recharge.setCreateUserId(currentMember.getMemberId().toString());
        recharge.setCreateUser(currentMember.getAccount());
        recharge.setUpdateDate(nowTime);
        recharge.setUpdateUserId(currentMember.getMemberId().toString());
        recharge.setUpdateUser(currentMember.getAccount());
        recharge.setIsValid(ESystemStatus.Valid);
        recharge.setRemark(String.format("充值，产品订单号：[%s],日期：[%s]", orderNo,
                DateUtils.formatDate(nowTime, "yyyy-MM-dd HH:mm:ss.SSS")));
        recharge.setSignature("待签名");
        boolean isFlagRecharge = accountRechargeDao.save(recharge) > 0;
        if (isFlagRecharge == false) {
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("系统产生订单错误，请重新充值");
            return resultMessage;
        }


        // 调用充值接口
        ParametersTradePay builderParameters = new ParametersTradePay();
        builderParameters.setPayType(payType);
        builderParameters.setPayChannel(payChannel);
        builderParameters.setOut_trade_no(orderNo);
        builderParameters.setSubject("充值");
        builderParameters.setAttach("");
        builderParameters.setBody(
                String.format("产品订单号：[%s],日期：[%s]", orderNo, DateUtils.formatDate(nowTime, "yyyy-MM-dd HH:mm:ss.SSS")));
        builderParameters.setTotal_fee(total_fee);
        ResultTradePay resultTradePay = tradeService.tradePay(builderParameters);

        boolean isFlag = resultTradePay.getCode().equals(EErrorCode.Success);
        if (isFlag) {
            resultMessage.setCode(EErrorCode.Success);
            if (payChannel.equals(EPayChannel.Alipay_NATIVE)) {
                resultMessage.setMessage(resultTradePay.getQr_code());
            } else {
                resultMessage.setMessage(resultTradePay.getForm());
            }
            return resultMessage;
        } else {
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage(resultTradePay.getMessage());
            return resultMessage;
        }

    }

    /**
     * 订单号
     *
     * @return 返回20位订单号
     */
    @Override
    public String getOrderNoByRecharge() {

        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {// 有可能是负数
            hashCodeV = -hashCodeV;
        }
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        return "D" + DateUtils.formatDate(DateUtils.now(), "yyyyMMdd") + String.format("%011d", hashCodeV);
        // 1+8+11
    }


}
