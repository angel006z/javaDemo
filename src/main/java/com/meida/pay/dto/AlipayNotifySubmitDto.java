package com.meida.pay.dto;

import com.meida.basefront.dto.CurrentMemberDto;
import com.meida.pay.po.AlipayNotifyInfo;

/**
 * 支付宝支付成功通知 submitDto
 * @author BING
 * @date 2019-10-19 15:27:58
 */
public class AlipayNotifySubmitDto{

	private static final long serialVersionUID=1L;
    /**
         * 提交操作参数
         * 新增传递固定值：add
         * 修改传递固定值：update
         */
    private String operate;

    private AlipayNotifyInfo item;

    private CurrentMemberDto currentMemberDto;

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public AlipayNotifyInfo getItem() {
        return item;
    }

    public void setItem(AlipayNotifyInfo item) {
        this.item = item;
    }

    public CurrentMemberDto getCurrentMemberDto() {
        return currentMemberDto;
    }

    public void setCurrentMemberDto(CurrentMemberDto currentMemberDto) {
        this.currentMemberDto = currentMemberDto;
    }
}
