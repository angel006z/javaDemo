package com.meida.backend.base.dto;

public class BaseOperateDto {
    /**
     * 业务主键id
     */
    private String[] ids;

    /**
     * 当前登录用户
     *
     * @return
     */
    private CurrentUserDto currentUserDto;

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }

    public CurrentUserDto getCurrentUserDto() {
        return currentUserDto;
    }

    public void setCurrentUserDto(CurrentUserDto currentUserDto) {
        this.currentUserDto = currentUserDto;
    }
}
