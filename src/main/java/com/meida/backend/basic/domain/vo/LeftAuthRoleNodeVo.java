package com.meida.backend.basic.domain.vo;

public class LeftAuthRoleNodeVo {
    private int nodeId;
    private int parentId;
    private String nodeName;
    private String nodeTarget;
    private String nodePath;

    private String nodeClassName;


    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeTarget() {
        return nodeTarget;
    }

    public void setNodeTarget(String nodeTarget) {
        this.nodeTarget = nodeTarget;
    }

    public String getNodePath() {
        return nodePath;
    }

    public void setNodePath(String nodePath) {
        this.nodePath = nodePath;
    }

    public String getNodeClassName() {
        return nodeClassName;
    }

    public void setNodeClassName(String nodeClassName) {
        this.nodeClassName = nodeClassName;
    }
}
