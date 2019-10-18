package com.meida.backend.basic.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class BaseOperateParamDto extends BaseParamDto {
    @NotBlank(message = "ids参数不能为空")
    @Size(max = 1000, message = "ids参数字符长度已超出最大限制")
    private String ids;

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
}
