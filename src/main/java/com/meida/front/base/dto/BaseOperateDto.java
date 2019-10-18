package com.meida.front.base.dto;

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
    private CurrentMemberDto currentMemberDto;

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }

    public CurrentMemberDto getCurrentMemberDto() {
        return currentMemberDto;
    }

    public void setCurrentMemberDto(CurrentMemberDto currentMemberDto) {
        this.currentMemberDto = currentMemberDto;
    }
}
