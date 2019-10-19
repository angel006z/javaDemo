package com.meida.front.pay.dto;

import com.meida.front.base.dto.CurrentMemberDto;
import com.meida.front.pay.po.AccountPayableInfo;

/**
 *  submitDto
 * @author BING
 * @date 2019-10-19 15:27:58
 */
public class AccountPayableSubmitDto{

	private static final long serialVersionUID=1L;
    /**
         * 提交操作参数
         * 新增传递固定值：add
         * 修改传递固定值：update
         */
    private String operate;

    private AccountPayableInfo item;

    private CurrentMemberDto currentMemberDto;

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public AccountPayableInfo getItem() {
        return item;
    }

    public void setItem(AccountPayableInfo item) {
        this.item = item;
    }

    public CurrentMemberDto getCurrentMemberDto() {
        return currentMemberDto;
    }

    public void setCurrentMemberDto(CurrentMemberDto currentMemberDto) {
        this.currentMemberDto = currentMemberDto;
    }
}
