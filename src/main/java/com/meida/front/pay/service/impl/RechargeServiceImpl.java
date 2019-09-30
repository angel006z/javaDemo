package com.meida.front.pay.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import com.meida.front.pay.dao.inter.*;
import com.meida.front.pay.domain.dto.BuildRechargeOrderDto;
import com.meida.front.pay.domain.dto.CurrentMemberDto;
import com.meida.front.pay.domain.po.*;
import com.meida.pay.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meida.base.domain.vo.ResultMessage;
import com.meida.common.util.DateUtils;
import com.meida.common.util.StringUtils;
import com.meida.common.util.constant.EErrorCode;
import com.meida.common.util.constant.ESystemStatus;
import com.meida.front.pay.domain.dto.AlipayNotifyParamDto;
import com.meida.front.pay.domain.dto.AlipayReturnParamDto;
import com.meida.front.pay.service.inter.IRechargeService;
import com.meida.pay.service.inter.ITradeService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RechargeServiceImpl implements IRechargeService {
    @Autowired
    private IRechargeDao rechargeDao;

    @Autowired
    private IFundInDao fundInDao;

    @Autowired
    private IFundAmountDao fundAmountDao;

    @Autowired
    private IAlipayNotifyDao alipayNotifyDao;

    @Autowired
    private IAlipayReturnDao alipayReturnDao;

    @Autowired
    private ITradeService tradeService;


    @Override
    public boolean addOrUpdate(Recharge item, boolean isAdd) {
        if (isAdd) {
            return rechargeDao.save(item) > 0;
        } else {
            return rechargeDao.update(item) > 0;
        }
    }

    @Override
    public boolean isExistOrderNo(String orderNo) {
        return rechargeDao.isExistOrderNo(orderNo) > 0;
    }

    @Override
    public Recharge getObjectByOrderNo(Serializable orderNo) {
        return rechargeDao.getObjectByOrderNo(orderNo);
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


        Recharge queryRecharge = rechargeDao.getObjectByOrderNo(orderNo);
        if (queryRecharge == null) {
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("业务订单号：" + orderNo + "充值订单不存在");
            return resultMessage;
        }

        if (queryRecharge.getIsPay().equals("yes")) {
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("业务订单号：" + orderNo + "充值订单状态已经是支付状态");
            return resultMessage;
        }

        long total_amount = alipayNotifyParamDto.getTotal_amount().multiply(new BigDecimal(100)).longValue();
        if (total_amount != queryRecharge.getRechargeMoney().multiply(new BigDecimal(100)).longValue()) {
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("业务订单号：" + orderNo + "充值订单金额和支付宝通知金额不相等");
            return resultMessage;
        }

        Date nowTime = DateUtils.now();

        // 事务begin
        Boolean isExistAlipayNotify = alipayNotifyDao.isExistOrderNo(orderNo) > 0;
        if (isExistAlipayNotify == false) {
            // 新增支付宝通知记录
            AlipayNotify alipayNotify = new AlipayNotify();
            alipayNotify.setApp_id(alipayNotifyParamDto.getApp_id());
            alipayNotify.setAuth_app_id(alipayNotifyParamDto.getAuth_app_id());
            alipayNotify.setBody(alipayNotifyParamDto.getBody());
            alipayNotify.setBuyer_id(alipayNotifyParamDto.getBuyer_id());
            alipayNotify.setBuyer_pay_amount(alipayNotifyParamDto.getBuyer_pay_amount());
            alipayNotify.setCharset(alipayNotifyParamDto.getCharset());
            alipayNotify.setFund_bill_list(alipayNotifyParamDto.getFund_bill_list());
            alipayNotify.setGmt_close(alipayNotifyParamDto.getGmt_close());
            alipayNotify.setGmt_create(alipayNotifyParamDto.getGmt_create());
            alipayNotify.setGmt_payment(alipayNotifyParamDto.getGmt_payment());
            alipayNotify.setGmt_refund(alipayNotifyParamDto.getGmt_refund());
            alipayNotify.setInvoice_amount(alipayNotifyParamDto.getInvoice_amount());
            alipayNotify.setNotify_id(alipayNotifyParamDto.getNotify_id());
            alipayNotify.setNotify_time(alipayNotifyParamDto.getNotify_time());
            alipayNotify.setNotify_type(alipayNotifyParamDto.getNotify_type());
            alipayNotify.setOut_biz_no(alipayNotifyParamDto.getOut_biz_no());
            alipayNotify.setOut_trade_no(alipayNotifyParamDto.getOut_trade_no());
            alipayNotify.setPassback_params(alipayNotifyParamDto.getPassback_params());
            alipayNotify.setPoint_amount(alipayNotifyParamDto.getPoint_amount());
            alipayNotify.setReceipt_amount(alipayNotifyParamDto.getReceipt_amount());
            alipayNotify.setRefund_fee(alipayNotifyParamDto.getRefund_fee());
            alipayNotify.setSeller_id(alipayNotifyParamDto.getSeller_id());
            alipayNotify.setSign(alipayNotifyParamDto.getSign());
            alipayNotify.setSign_type(alipayNotifyParamDto.getSign_type());
            alipayNotify.setSubject(alipayNotifyParamDto.getSubject());
            alipayNotify.setTotal_amount(alipayNotifyParamDto.getTotal_amount());
            alipayNotify.setTrade_no(alipayNotifyParamDto.getTrade_no());
            alipayNotify.setTrade_status(alipayNotifyParamDto.getTrade_status());
            alipayNotify.setVersion(alipayNotifyParamDto.getVersion());
            alipayNotify.setVoucher_detail_list(alipayNotifyParamDto.getVoucher_detail_list());
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
        Recharge recharge = new Recharge();
        recharge.setOrderNo(orderNo);
        recharge.setIsPay("yes");
        recharge.setUpdateDate(nowTime);
        recharge.setUpdateUserId("系统id");
        recharge.setUpdateUser("系统");
        boolean isFlagPay = rechargeDao.updateByOrderNo(recharge) > 0;


        // 入账记录
        Long memberId = queryRecharge.getMemberId();
        FundIn fundIn = new FundIn();
        fundIn.setMemberId(memberId);
        fundIn.setOrderNo(orderNo);
        fundIn.setInDate(nowTime);
        fundIn.setInMoney(alipayNotifyParamDto.getTotal_amount());
        fundIn.setInWay("alipay");
        fundIn.setCreateDate(nowTime);
        fundIn.setCreateUserId("系统id");
        fundIn.setCreateUser("系统");
        fundIn.setUpdateDate(nowTime);
        fundIn.setUpdateUserId("系统id");
        fundIn.setUpdateUser("系统");
        fundIn.setIsValid(ESystemStatus.Valid);
        fundIn.setRemark("系统自动记录");
        fundIn.setSignature("待签名");
        fundInDao.save(fundIn);

        // 总资金
        boolean isFlagFundAmount = false;
        FundAmount queryFundAmount = fundAmountDao.getObjectByMemberId(memberId);
        if (queryFundAmount != null) {
            FundAmount fundAmount = new FundAmount();
            fundAmount.setMemberId(memberId);
            BigDecimal totalMoney = queryFundAmount.getTotalMoney().add(alipayNotifyParamDto.getTotal_amount());
            fundAmount.setTotalMoney(totalMoney);
            fundAmount.setUpdateDate(nowTime);
            fundAmount.setUpdateUserId("系统id");
            fundAmount.setUpdateUser("系统");
            isFlagFundAmount = fundAmountDao.updateByMemberId(fundAmount) > 0;
        } else {
            FundAmount fundAmount = new FundAmount();
            fundAmount.setMemberId(memberId);
            fundAmount.setTotalMoney(alipayNotifyParamDto.getTotal_amount());
            fundAmount.setCreateDate(nowTime);
            fundAmount.setCreateUserId("系统id");
            fundAmount.setCreateUser("系统");
            fundAmount.setUpdateDate(nowTime);
            fundAmount.setUpdateUserId("系统id");
            fundAmount.setUpdateUser("系统");
            fundAmount.setIsValid(ESystemStatus.Valid);
            fundAmount.setRemark("系统自动记录");
            fundAmount.setSignature("待签名");
            isFlagFundAmount = fundAmountDao.save(fundAmount) > 0;
        }
        System.out.println("isFlagFundAmount:" + isFlagFundAmount);
        // 事务end

        resultMessage.setCode(EErrorCode.Success);
        resultMessage.setMessage("操作成功");
        return resultMessage;
    }

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


        Recharge queryReharge = rechargeDao.getObjectByOrderNo(orderNo);
        if (queryReharge == null) {
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("业务订单号：" + orderNo + "充值订单不存在");
            return resultMessage;
        }

        if (queryReharge.getIsPay().equals("yes")) {
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("业务订单号：" + orderNo + "充值订单状态已经是支付状态");
            return resultMessage;
        }

        long total_amount = alipayReturnParamDto.getTotal_amount().multiply(new BigDecimal(100)).longValue();
        if (total_amount != queryReharge.getRechargeMoney().multiply(new BigDecimal(100)).longValue()) {
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("业务订单号：" + orderNo + "充值订单金额和支付宝通知金额不相等");
            return resultMessage;
        }

        Date nowTime = DateUtils.now();

        // 事务begin
        Boolean isExistAlipayReturn = alipayReturnDao.isExistOrderNo(orderNo) > 0;
        if (isExistAlipayReturn == false) {
            // 新增支付宝通知记录
            AlipayReturn alipayReturn = new AlipayReturn();
            alipayReturn.setCode(alipayReturnParamDto.getCode());
            alipayReturn.setMsg(alipayReturnParamDto.getMsg());
            alipayReturn.setCharset(alipayReturnParamDto.getCharset());
            alipayReturn.setOut_trade_no(alipayReturnParamDto.getOut_trade_no());
            alipayReturn.setMethod(alipayReturnParamDto.getMethod());
            alipayReturn.setTotal_amount(alipayReturnParamDto.getTotal_amount());
            alipayReturn.setSign(alipayReturnParamDto.getSign());
            alipayReturn.setTrade_no(alipayReturnParamDto.getTrade_no());
            alipayReturn.setAuth_app_id(alipayReturnParamDto.getAuth_app_id());
            alipayReturn.setVersion(alipayReturnParamDto.getVersion());
            alipayReturn.setApp_id(alipayReturnParamDto.getApp_id());
            alipayReturn.setSign_type(alipayReturnParamDto.getSign_type());
            alipayReturn.setSeller_id(alipayReturnParamDto.getSeller_id());
            alipayReturn.setTimestamp(alipayReturnParamDto.getTimestamp());

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
        Recharge recharge = new Recharge();
        recharge.setOrderNo(orderNo);
        recharge.setIsPay("yes");
        recharge.setUpdateDate(nowTime);
        recharge.setUpdateUserId("系统id");
        recharge.setUpdateUser("系统");
        boolean isFlagPay = rechargeDao.updateByOrderNo(recharge) > 0;


        // 入账记录
        Long memberId = queryReharge.getMemberId();
        FundIn fundIn = new FundIn();
        fundIn.setMemberId(memberId);
        fundIn.setOrderNo(orderNo);
        fundIn.setInDate(nowTime);
        fundIn.setInMoney(alipayReturnParamDto.getTotal_amount());
        fundIn.setInWay("alipay");
        fundIn.setCreateDate(nowTime);
        fundIn.setCreateUserId("系统id");
        fundIn.setCreateUser("系统");
        fundIn.setUpdateDate(nowTime);
        fundIn.setUpdateUserId("系统id");
        fundIn.setUpdateUser("系统");
        fundIn.setIsValid(ESystemStatus.Valid);
        fundIn.setRemark("系统自动记录");
        fundIn.setSignature("待签名");
        fundInDao.save(fundIn);

        // 总资金
        boolean isFlagFundAmount = false;
        FundAmount queryFundAmount = fundAmountDao.getObjectByMemberId(memberId);
        if (queryFundAmount != null) {
            FundAmount fundAmount = new FundAmount();
            fundAmount.setMemberId(memberId);
            BigDecimal totalMoney = queryFundAmount.getTotalMoney().add(alipayReturnParamDto.getTotal_amount());
            fundAmount.setTotalMoney(totalMoney);
            fundAmount.setUpdateDate(nowTime);
            fundAmount.setUpdateUserId("系统id");
            fundAmount.setUpdateUser("系统");
            isFlagFundAmount = fundAmountDao.updateByMemberId(fundAmount) > 0;
        } else {
            FundAmount fundAmount = new FundAmount();
            fundAmount.setMemberId(memberId);
            fundAmount.setTotalMoney(alipayReturnParamDto.getTotal_amount());
            fundAmount.setCreateDate(nowTime);
            fundAmount.setCreateUserId("系统id");
            fundAmount.setCreateUser("系统");
            fundAmount.setUpdateDate(nowTime);
            fundAmount.setUpdateUserId("系统id");
            fundAmount.setUpdateUser("系统");
            fundAmount.setIsValid(ESystemStatus.Valid);
            fundAmount.setRemark("系统自动记录");
            fundAmount.setSignature("待签名");
            isFlagFundAmount = fundAmountDao.save(fundAmount) > 0;
        }
        System.out.println("isFlagFundAmount:" + isFlagFundAmount);
        // 事务end

        resultMessage.setCode(EErrorCode.Success);
        resultMessage.setMessage("操作成功");
        return resultMessage;
    }



    /**
     * 构建充值订单
     * 产生订单号，返回相应支付路径
     * 网页支付：微信返回二维码，支付宝返回支付宝付款路径
     *
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

        BigDecimal total_fee = buildRehargeOrderDto.getTotal_fee();
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

        String payType= buildRehargeOrderDto.getPayType();
        String payChannel= buildRehargeOrderDto.getPayChannel();

        // 系统生成订单信息
        Recharge recharge = new Recharge();
        recharge.setMemberId(chargeMemberId);
        recharge.setOrderNo(orderNo);
        recharge.setRechargeMoney(total_fee);
        recharge.setRechargeDate(nowTime);
        recharge.setPayType(payType);
        recharge.setPayChannel(payChannel);
        recharge.setIsPay("no");
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
        boolean isFlagRecharge = this.addOrUpdate(recharge, true);
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
            if(payChannel.equals(EPayChannel.Alipay_NATIVE)){
                resultMessage.setMessage(resultTradePay.getQr_code());
            }else {
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
