package com.meida.basebackend.dto;

import javax.validation.constraints.NotBlank;

public class SingleParamDto extends BaseParamDto {
    @NotBlank(message = "id参数不能为空")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
