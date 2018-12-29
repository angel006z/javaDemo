package com.meida.backend.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.meida.base.controller.BaseBackendController;
import com.meida.base.vo.ResultMessage;
import com.meida.common.util.RequestParameters;
import com.meida.common.util.Utits;
import com.meida.common.util.constant.EErrorCode;
import com.meida.common.util.nodepage.EUser;


/**
 * 用户管理
 * http://localhost:9090/demo/backend/basic/user/list
 * http://localhost:9090/demo/backend/basic/user/detail
 */
@Controller
@RequestMapping(value = "/backend/basic/user")
public class UserController extends BaseBackendController {

	private int ListPageNodeId = EUser.ListPage;
	private int AddPageNodeId = EUser.AddPage;
	private int EditPageNodeId = EUser.EditPage;
	private int DetailPageNodeId = EUser.DetailPage;

	@RequestMapping(value = "/list")
	public ModelAndView list() {
		int[] NodePages = { ListPageNodeId };
		int NodeId = RequestParameters.getInt("NodeId");
		ResultMessage accessPageAuth = Utits.accessPageAuth(NodePages, NodeId);
		if (!accessPageAuth.getErrorCode().equals(EErrorCode.Success)) {
			return this.noAccessPageAuth(accessPageAuth);
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("title", "列表页面");
		modelAndView.addObject("OperateButton", Utits.authOperateButton());
		modelAndView.addObject("ListPageNodeId", ListPageNodeId);
		modelAndView.addObject("AddPageNodeId", AddPageNodeId);
		modelAndView.addObject("EditPageNodeId", EditPageNodeId);
		modelAndView.addObject("DetailPageNodeId", DetailPageNodeId);
		return modelAndView;
	}

	@RequestMapping(value = "/detail")
	public ModelAndView detail() {
		int[] NodePages = { AddPageNodeId, EditPageNodeId, DetailPageNodeId };
		int NodeId = RequestParameters.getInt("NodeId");
		ResultMessage accessPageAuth = Utits.accessPageAuth(NodePages, NodeId);
		if (!accessPageAuth.getErrorCode().equals(EErrorCode.Success)) {
			return this.noAccessPageAuth(accessPageAuth);
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("title", "详细页面");
		modelAndView.addObject("OperateButton", Utits.authOperateButton());
		modelAndView.addObject("ListPageNodeId", ListPageNodeId);
		modelAndView.addObject("AddPageNodeId", AddPageNodeId);
		modelAndView.addObject("EditPageNodeId", EditPageNodeId);
		modelAndView.addObject("DetailPageNodeId", DetailPageNodeId);
		return modelAndView;
	}

}
