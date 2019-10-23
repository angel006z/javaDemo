package com.meida.basebackend.vo;

import java.util.UUID;

public class CurrentUserVo {
    private UUID currentUserId;
    private String currentUserName;
    private String currentRealName;

    private UUID currentRoleId;
    private String currentRoleName;

    private UUID currentDeptId;
    private String currentDeptName;


    public UUID getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(UUID currentUserId) {
        this.currentUserId = currentUserId;
    }

    public String getCurrentUserName() {
        return currentUserName;
    }

    public void setCurrentUserName(String currentUserName) {
        this.currentUserName = currentUserName;
    }

    public String getCurrentRealName() {
        return currentRealName;
    }

    public void setCurrentRealName(String currentRealName) {
        this.currentRealName = currentRealName;
    }

    public UUID getCurrentRoleId() {
        return currentRoleId;
    }

    public void setCurrentRoleId(UUID currentRoleId) {
        this.currentRoleId = currentRoleId;
    }

    public String getCurrentRoleName() {
        return currentRoleName;
    }

    public void setCurrentRoleName(String currentRoleName) {
        this.currentRoleName = currentRoleName;
    }

    public UUID getCurrentDeptId() {
        return currentDeptId;
    }

    public void setCurrentDeptId(UUID currentDeptId) {
        this.currentDeptId = currentDeptId;
    }

    public String getCurrentDeptName() {
        return currentDeptName;
    }

    public void setCurrentDeptName(String currentDeptName) {
        this.currentDeptName = currentDeptName;
    }
}
