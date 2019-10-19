package com.meida.backend.pay.dto;

import com.meida.backend.base.dto.CurrentUserDto;
import com.meida.backend.pay.po.AccountAmountInfo;

/**
 *  submitDto
 * @author BING
 * @date 2019-10-19 18:56:35
 */
public class AccountAmountSubmitDto{

	private static final long serialVersionUID=1L;
    /**
         * 提交操作参数
         * 新增传递固定值：add
         * 修改传递固定值：update
         */
    private String operate;

    private AccountAmountInfo item;

    private CurrentUserDto currentUserDto;

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public AccountAmountInfo getItem() {
        return item;
    }

    public void setItem(AccountAmountInfo item) {
        this.item = item;
    }

    public CurrentUserDto getCurrentUserDto() {
        return currentUserDto;
    }

    public void setCurrentUserDto(CurrentUserDto currentUserDto) {
        this.currentUserDto = currentUserDto;
    }
}
