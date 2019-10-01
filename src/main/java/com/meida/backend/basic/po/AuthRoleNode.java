package com.meida.backend.basic.po;
import java.util.Date;

public class AuthRoleNode {
private String authRoleNodeId;
private Integer nodeId;
private String roleId;
private Date operateDate ;

public String getAuthRoleNodeId() {
	return authRoleNodeId;
}
public void setAuthRoleNodeId(String authRoleNodeId) {
	this.authRoleNodeId = authRoleNodeId;
}
public Integer getNodeId() {
	return nodeId;
}
public void setNodeId(Integer nodeId) {
	this.nodeId = nodeId;
}
public String getRoleId() {
	return roleId;
}
public void setRoleId(String roleId) {
	this.roleId = roleId;
}
public Date getOperateDate() {
	return operateDate;
}
public void setOperateDate(Date operateDate) {
	this.operateDate = operateDate;
}
}
