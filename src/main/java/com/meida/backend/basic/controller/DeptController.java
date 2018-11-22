package com.meida.backend.basic.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.meida.backend.basic.dto.DeptListDto;
import com.meida.backend.basic.po.Dept;
import com.meida.backend.basic.service.impl.DeptServiceImpl;
import com.meida.base.controller.BaseBackendController;
import com.meida.base.vo.ResultList;
import com.meida.base.vo.ResultMessage;
import com.meida.common.pojo.Pagination;
import com.meida.common.util.JsonUtils;
import com.meida.common.util.RequestParameters;
import com.meida.common.util.Utits;
import com.meida.common.util.constant.EErrorCode;
import com.meida.common.util.nodepage.EDept;

/**
 * 用户管理
 */
@Controller
@RequestMapping(value = "/backend/basic/dept")
public class DeptController extends BaseBackendController {
	private int ListPageNodeId = EDept.ListPage;
	private int AddPageNodeId = EDept.AddPage;
	private int EditPageNodeId = EDept.EditPage;
	private int DetailPageNodeId = EDept.DetailPage;

	@RequestMapping(value = "/list")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
		int[] NodePages = { ListPageNodeId };
		int NodeId = RequestParameters.getInt("NodeId", request);
		ResultMessage accessPageAuth = Utits.AccessPageAuth(NodePages, NodeId);
		if (accessPageAuth.getErrorCode() != EErrorCode.Success) {
			return this.noAccessPageAuth(accessPageAuth, request);
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("title", "列表页面");
		System.out.println(request.getContextPath());
		modelAndView.addObject("OperateButton", Utits.AuthOperateButton(request));
		modelAndView.addObject("ListPageNodeId", ListPageNodeId);
		modelAndView.addObject("AddPageNodeId", AddPageNodeId);
		modelAndView.addObject("EditPageNodeId", EditPageNodeId);
		modelAndView.addObject("DetailPageNodeId", DetailPageNodeId);
		return modelAndView;
	}

	@RequestMapping(value = "/detail")
	public ModelAndView detail(HttpServletRequest request, HttpServletResponse response) {
		int[] NodePages = { AddPageNodeId, EditPageNodeId, DetailPageNodeId };
		int NodeId = RequestParameters.getInt("NodeId", request);
		ResultMessage accessPageAuth = Utits.AccessPageAuth(NodePages, NodeId);
		if (accessPageAuth.getErrorCode() != EErrorCode.Success) {
			return this.noAccessPageAuth(accessPageAuth, request);
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("title", "详细页面");
		modelAndView.addObject("OperateButton", Utits.AuthOperateButton(request));
		modelAndView.addObject("ListPageNodeId", ListPageNodeId);
		modelAndView.addObject("AddPageNodeId", AddPageNodeId);
		modelAndView.addObject("EditPageNodeId", EditPageNodeId);
		modelAndView.addObject("DetailPageNodeId", DetailPageNodeId);
		return modelAndView;
	}

	/************** JsonResult Begin ******************/
	@RequestMapping(value = "/SearchList")
	@ResponseBody
	public String SearchList(HttpServletRequest request) {
		// 权限控制
		int[] iRangePage = { ListPageNodeId };
		int iCurrentPageNodeId = RequestParameters.getInt("NodeId", request);
		ResultMessage tempAuth = Utits.AccessPageAuth(iRangePage, iCurrentPageNodeId);
		if (tempAuth.getErrorCode() != EErrorCode.Success)
			return JsonUtils.toJSONString(tempAuth);

		// 当前页
		int iCurrentPage = RequestParameters.getInt("currentPage", request);
		// 一页的数量
		int iPageSize = RequestParameters.getInt("pageSize", request);
		iPageSize = iPageSize <= 0 ? 5 : iPageSize;
		iPageSize = iPageSize > 100 ? 100 : iPageSize;

		String deptName = RequestParameters.getString("DeptName", request);

		DeptListDto whereItem = new DeptListDto();
		whereItem.setPagination(new Pagination(iCurrentPage, iPageSize));
		whereItem.setDeptName(deptName);

		DeptServiceImpl service = new DeptServiceImpl();
		List<Dept> list = service.searchByPageCondition(whereItem);

		ResultList resultList = new ResultList();
		resultList.setErrorCode(EErrorCode.Success);
		resultList.setPagination(whereItem.getPagination());
		resultList.setData(list);
		return JsonUtils.toJSONString(resultList);
	}

	/************** JsonResult End ******************/
}
