package com.meida.pay.dto;

import com.meida.basebackend.dto.BaseParamDto;
import com.meida.pay.po.AlipayReturnInfo;
import javax.validation.constraints.NotBlank;

/**
 * 支付宝同步通知 submitParamDto
 * @author BING
 * @date 2019-10-19 15:27:58
 */
public class AlipayReturnSubmitParamDto extends BaseParamDto{

	private static final long serialVersionUID=1L;

    /**
     * 操作参数
     * 新增传递固定值：add
     * 修改传递固定值：update
     */
    @NotBlank(message = "operate参数不能为空")
    private String operate;

    private AlipayReturnInfo item;

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public AlipayReturnInfo getItem() {
        return item;
    }

	public void setItem(AlipayReturnInfo item) {
        this.item = item;
    }
}
