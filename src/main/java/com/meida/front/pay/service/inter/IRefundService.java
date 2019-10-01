package com.meida.front.pay.service.inter;

import com.meida.base.vo.ResultMessage;
import com.meida.front.pay.dto.OriginalRefundDto;

/**
 * 退款
 */
public interface IRefundService {
    /**
     * 原路退款
     * @return
     */
    ResultMessage originalRefund(OriginalRefundDto originalRefundDto);
}
