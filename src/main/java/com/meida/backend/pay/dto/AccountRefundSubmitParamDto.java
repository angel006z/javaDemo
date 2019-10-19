package com.meida.backend.pay.dto;

import com.meida.backend.base.dto.BaseParamDto;
import com.meida.backend.pay.po.AccountRefundInfo;
import javax.validation.constraints.NotBlank;

/**
 *  submitParamDto
 * @author BING
 * @date 2019-10-19 18:56:35
 */
public class AccountRefundSubmitParamDto extends BaseParamDto{

	private static final long serialVersionUID=1L;

    /**
     * 操作参数
     * 新增传递固定值：add
     * 修改传递固定值：update
     */
    @NotBlank(message = "operate参数不能为空")
    private String operate;

    private AccountRefundInfo item;

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public AccountRefundInfo getItem() {
        return item;
    }

	public void setItem(AccountRefundInfo item) {
        this.item = item;
    }
}
