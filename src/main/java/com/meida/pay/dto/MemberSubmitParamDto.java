package com.meida.pay.dto;

import com.meida.basebackend.dto.BaseParamDto;
import com.meida.pay.po.MemberInfo;
import javax.validation.constraints.NotBlank;

/**
 *  submitParamDto
 * @author BING
 * @date 2019-10-19 15:27:58
 */
public class MemberSubmitParamDto extends BaseParamDto{

	private static final long serialVersionUID=1L;

    /**
     * 操作参数
     * 新增传递固定值：add
     * 修改传递固定值：update
     */
    @NotBlank(message = "operate参数不能为空")
    private String operate;

    private MemberInfo item;

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public MemberInfo getItem() {
        return item;
    }

	public void setItem(MemberInfo item) {
        this.item = item;
    }
}
