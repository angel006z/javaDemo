package com.meida.backend.basic.po;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private String userId;

    private String userCode;

    private String userName;

    private String password;

    private String roleId;

    private String deptId;

    private String realName;

    private String realPassword;

    private String phone;

    private String telphone;

    private String email;

    private Date loginLastDate;

    private Date createDate;

    private Date operateDate;

    private String operatorId;

    private Integer isValid;

    private String remark;

    private static final long serialVersionUID = 1L;

    public User(String userId, String userCode, String userName, String password, String roleId, String deptId, String realName, String realPassword, String phone, String telphone, String email, Date loginLastDate, Date createDate, Date operateDate, String operatorId, Integer isValid, String remark) {
        this.userId = userId;
        this.userCode = userCode;
        this.userName = userName;
        this.password = password;
        this.roleId = roleId;
        this.deptId = deptId;
        this.realName = realName;
        this.realPassword = realPassword;
        this.phone = phone;
        this.telphone = telphone;
        this.email = email;
        this.loginLastDate = loginLastDate;
        this.createDate = createDate;
        this.operateDate = operateDate;
        this.operatorId = operatorId;
        this.isValid = isValid;
        this.remark = remark;
    }

    public User() {
        super();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getRealPassword() {
        return realPassword;
    }

    public void setRealPassword(String realPassword) {
        this.realPassword = realPassword == null ? null : realPassword.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone == null ? null : telphone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getLoginLastDate() {
        return loginLastDate;
    }

    public void setLoginLastDate(Date loginLastDate) {
        this.loginLastDate = loginLastDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId == null ? null : operatorId.trim();
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}