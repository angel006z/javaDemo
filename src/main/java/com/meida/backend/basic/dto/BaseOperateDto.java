package com.meida.backend.basic.dto;

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
    private CurrentUserDto currentUser;

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }

    public CurrentUserDto getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(CurrentUserDto currentUser) {
        this.currentUser = currentUser;
    }
}
