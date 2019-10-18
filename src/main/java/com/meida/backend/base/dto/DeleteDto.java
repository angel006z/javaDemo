package com.meida.backend.base.dto;

public class DeleteDto extends BaseOperateDto {

    public DeleteDto(){
        isCheckCurrentUserId=false;
    }
    /**
     * 是否是本人才能删除
     */
    private boolean isCheckCurrentUserId ;

    public boolean isCheckCurrentUserId() {
        return isCheckCurrentUserId;
    }

    public void setCheckCurrentUserId(boolean checkCurrentUserId) {
        isCheckCurrentUserId = checkCurrentUserId;
    }

}
