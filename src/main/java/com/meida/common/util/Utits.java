package com.meida.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.omg.CORBA.Request;

import com.meida.backend.basic.po.vAuthRoleNodeButton;
import com.meida.backend.basic.service.impl.AuthRoleNodeButtonServiceImpl;
import com.meida.backend.basic.service.impl.AuthRoleNodeServiceImpl;
import com.meida.base.vo.ResultMessage;
import com.meida.common.util.constant.EErrorCode;

public class Utits {

	public static UUID CurrentUserId;
	public static boolean IsSuper = false;

	/**
	 * 动态生成查看、修改、增加、删除、等按钮
	 * 
	 * @return
	 */
	public static String AuthOperateButton() {
		// 页面权限及按钮权限
		int NodeId = RequestParameters.getInt("NodeId");
		// 判断登录
		UUID UserId = Utits.CurrentUserId;

		if (!new AuthRoleNodeServiceImpl().IsNodePageAuth(UserId, NodeId, IsSuper)) {
			return "";
		}

		// 获得页面带权限按钮
		List<vAuthRoleNodeButton> listOperateAuthButton = new AuthRoleNodeButtonServiceImpl()
				.getListByUserIdNodeId(UserId, NodeId, IsSuper);
		if (listOperateAuthButton == null)
			listOperateAuthButton = new ArrayList<vAuthRoleNodeButton>();
			
		StringBuffer buf = new StringBuffer();
		for (vAuthRoleNodeButton item : listOperateAuthButton) {
			buf.append(String.format("<a href=\"javascript:void(0);\" hidefocus id=\"%sOperate\" class=\"op-btn\">%s</a>",
					item.getBtnId(), item.getBtnName()));
		}
		
		return buf.toString();
	}

	/**
	 * 页面访问权
	 * 
	 * @return
	 */
	public static ResultMessage AccessPageAuth() {
		UUID iUSERID = Utits.CurrentUserId;
		if (iUSERID == UUIDUtils.Empty) {
			ResultMessage resultMessage = new ResultMessage();
			resultMessage.setErrorCode(EErrorCode.NoLogin);
			resultMessage.setErrorMessage("未登录.");
			return resultMessage;
		} else {
			ResultMessage resultMessage = new ResultMessage();
			resultMessage.setErrorCode(EErrorCode.Success);
			resultMessage.setErrorMessage("已登录.");
			return resultMessage;
		}
	}

	/**
	 * 页面访问权
	 * 
	 * @param iRangePage         能操作该按钮的页面数组
	 * @param iCurrentPageNodeId 当前操作页面的ID
	 * @return
	 */
	public static ResultMessage AccessPageAuth(int[] iRangePage, int iCurrentPageNodeId) {
		
		if (!ArrayUtils.contains(iRangePage, iCurrentPageNodeId)) {
			ResultMessage resultMessage = new ResultMessage();
			resultMessage.setErrorCode(EErrorCode.NoContainsNodeId);
			resultMessage.setErrorMessage("页面不包含该NodeId.");
			return resultMessage;
		}

		UUID iUSERID = Utits.CurrentUserId;
		if (iUSERID == UUIDUtils.Empty) {
			ResultMessage resultMessage = new ResultMessage();
			resultMessage.setErrorCode(EErrorCode.NoLogin);
			resultMessage.setErrorMessage("未登录.");
			return resultMessage;
		}

		boolean isFlag = new AuthRoleNodeServiceImpl().IsNodePageAuth(iUSERID, iCurrentPageNodeId, IsSuper);
		if (!isFlag) {
			ResultMessage resultMessage = new ResultMessage();
			resultMessage.setErrorCode(EErrorCode.NoOperateAuth);
			resultMessage.setErrorMessage("无操作权限.");
			return resultMessage;
		} else {
			ResultMessage resultMessage = new ResultMessage();
			resultMessage.setErrorCode(EErrorCode.Success);
			resultMessage.setErrorMessage("有操作权限.");
			return resultMessage;
		}
	}

	public static ResultMessage IsOperateAuth(int[] iRangePage, int iCurrentPageNodeId, int iCurrentButtonId) {
		// TODO Auto-generated method stub
		return null;
	}

	public static ResultMessage IsOperateAuth(int[] iRangePage, int iCurrentPageNodeId, int[] iRangeButton,
			int iCurrentButtonId) {
		// TODO Auto-generated method stub
		return null;
	}

}