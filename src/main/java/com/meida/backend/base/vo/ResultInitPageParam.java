package com.meida.backend.base.vo;

import com.meida.backend.base.vo.CurrentUserVo;
import com.meida.base.vo.ResultMessage;

import java.util.UUID;

/**
 * 初始化页面返回结果
 */
public class ResultInitPageParam extends ResultMessage {
    private int listPageNodeId ;
    private int addPageNodeId ;
    private int editPageNodeId ;
    private int detailPageNodeId ;
    private Object operateButton ;
    private Object data ;
    private UUID workFlowId ;
    private CurrentUserVo currentUser;


    public int getListPageNodeId() {
        return listPageNodeId;
    }

    public void setListPageNodeId(int listPageNodeId) {
        this.listPageNodeId = listPageNodeId;
    }

    public int getAddPageNodeId() {
        return addPageNodeId;
    }

    public void setAddPageNodeId(int addPageNodeId) {
        this.addPageNodeId = addPageNodeId;
    }

    public int getEditPageNodeId() {
        return editPageNodeId;
    }

    public void setEditPageNodeId(int editPageNodeId) {
        this.editPageNodeId = editPageNodeId;
    }

    public int getDetailPageNodeId() {
        return detailPageNodeId;
    }

    public void setDetailPageNodeId(int detailPageNodeId) {
        this.detailPageNodeId = detailPageNodeId;
    }

    public Object getOperateButton() {
        return operateButton;
    }

    public void setOperateButton(Object operateButton) {
        this.operateButton = operateButton;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public UUID getWorkFlowId() {
        return workFlowId;
    }

    public void setWorkFlowId(UUID workFlowId) {
        this.workFlowId = workFlowId;
    }

    public CurrentUserVo getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(CurrentUserVo currentUser) {
        this.currentUser = currentUser;
    }
}
