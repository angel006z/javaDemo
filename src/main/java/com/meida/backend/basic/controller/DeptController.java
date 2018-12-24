package com.meida.backend.basic.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.meida.backend.basic.dto.DeptListDto;
import com.meida.backend.basic.po.Dept;
import com.meida.backend.basic.service.inter.IDeptService;
import com.meida.base.controller.BaseBackendController;
import com.meida.base.vo.Pagination;
import com.meida.base.vo.ResultDetail;
import com.meida.base.vo.ResultList;
import com.meida.base.vo.ResultMessage;
import com.meida.common.util.DateUtils;
import com.meida.common.util.JsonUtils;
import com.meida.common.util.RegexValidate;
import com.meida.common.util.RequestParameters;
import com.meida.common.util.StringUtils;
import com.meida.common.util.UUIDUtils;
import com.meida.common.util.Utits;
import com.meida.common.util.constant.EButtonType;
import com.meida.common.util.constant.EErrorCode;
import com.meida.common.util.nodepage.EDept;

/**
 * 部门管理
 */
@Controller
@RequestMapping(value = "/backend/basic/dept")
public class DeptController extends BaseBackendController {
	private int ListPageNodeId = EDept.ListPage;
	private int AddPageNodeId = EDept.AddPage;
	private int EditPageNodeId = EDept.EditPage;
	private int DetailPageNodeId = EDept.DetailPage;
	
	@Autowired
	private IDeptService deptService;
   	
	@RequestMapping(value = "/list")
	public ModelAndView list() {
		int[] nodePages = { ListPageNodeId };
		int nodeId = RequestParameters.getInt("nodeId");
		ResultMessage accessPageAuth = Utits.AccessPageAuth(nodePages, nodeId);
		if (!accessPageAuth.getErrorCode().equals(EErrorCode.Success)) {
			return this.noAccessPageAuth(accessPageAuth);
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("title", "列表页面");
		modelAndView.addObject("OperateButton", Utits.AuthOperateButton());
		modelAndView.addObject("ListPageNodeId", ListPageNodeId);
		modelAndView.addObject("AddPageNodeId", AddPageNodeId);
		modelAndView.addObject("EditPageNodeId", EditPageNodeId);
		modelAndView.addObject("DetailPageNodeId", DetailPageNodeId);
		return modelAndView;
	}

	@RequestMapping(value = "/detail")
	public ModelAndView detail() {
		int[] nodePages = { AddPageNodeId, EditPageNodeId, DetailPageNodeId };
		int nodeId = RequestParameters.getInt("nodeId");
		ResultMessage accessPageAuth = Utits.AccessPageAuth(nodePages, nodeId);
		if (!accessPageAuth.getErrorCode().equals(EErrorCode.Success)) {
			return this.noAccessPageAuth(accessPageAuth);
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("title", "详细页面");
		modelAndView.addObject("OperateButton", Utits.AuthOperateButton());
		modelAndView.addObject("ListPageNodeId", ListPageNodeId);
		modelAndView.addObject("AddPageNodeId", AddPageNodeId);
		modelAndView.addObject("EditPageNodeId", EditPageNodeId);
		modelAndView.addObject("DetailPageNodeId", DetailPageNodeId);
		return modelAndView;
	}

	/************** JsonResult Begin ******************/
	/**
	 * 查询多条记录
	 * 
	 * @return
	 */
	@RequestMapping(value = "/searchList")
	@ResponseBody
	public String searchList() {
		// 权限控制
		int[] iRangePage = { ListPageNodeId };
		int iCurrentPageNodeId = RequestParameters.getInt("nodeId");
		ResultMessage tempAuth = Utits.AccessPageAuth(iRangePage, iCurrentPageNodeId);
		if (!tempAuth.getErrorCode().equals(EErrorCode.Success)) {
			return JsonUtils.toJSONString(tempAuth);
		}
		// 当前页
		int iCurrentPage = RequestParameters.getInt("currentPage");
		// 一页的数量
		int iPageSize = RequestParameters.getInt("pageSize");
		iPageSize = iPageSize <= 0 ? 5 : iPageSize;
		iPageSize = iPageSize > 100 ? 100 : iPageSize;

		String deptName = RequestParameters.getString("deptName");

		DeptListDto whereItem = new DeptListDto();
		whereItem.setPagination(new Pagination(iCurrentPage, iPageSize));
		whereItem.setDeptName(deptName);
		
		List<Dept> list = deptService.getListByPage(whereItem);

		ResultList resultList = new ResultList();
		resultList.setErrorCode(EErrorCode.Success);
		resultList.setPagination(whereItem.getPagination());
		resultList.setData(list);
		return JsonUtils.toJSONString(resultList);
	}

	/**
	 * 查询单条记录
	 * 
	 * @return
	 */
	@RequestMapping(value = "/initSingle")
	@ResponseBody
	public String initSingle() {
		// 权限控制
		int[] iRangePage = { AddPageNodeId, EditPageNodeId, DetailPageNodeId };
		int iCurrentPageNodeId = RequestParameters.getInt("nodeId");		
		ResultMessage tempAuth = Utits.AccessPageAuth(iRangePage, iCurrentPageNodeId);
		if (!tempAuth.getErrorCode().equals(EErrorCode.Success)) {
			return JsonUtils.toJSONString(tempAuth);
		}
		
		UUID id = RequestParameters.getGuid("id");
		if (id != UUIDUtils.Empty) {			
			Dept item = deptService.getObjectById(id.toString());
			ResultDetail resultDetail = new ResultDetail();
			resultDetail.setData(item);
			resultDetail.setErrorCode(EErrorCode.Success);
			return JsonUtils.toJSONString(resultDetail);
		} else {
			ResultMessage resultMessage = new ResultMessage();
			resultMessage.setErrorCode(EErrorCode.Error);
			resultMessage.setErrorMessage("参数不合法.");
			return JsonUtils.toJSONString(resultMessage);
		}
	}

	/**
	 * submitOperate
	 * 
	 * @return
	 */
	@RequestMapping(value = "/submitOperate")
	@ResponseBody
	public String submitOperate() {
		// 权限控制
		int[] iRangePage = { AddPageNodeId, EditPageNodeId };
		int iCurrentPageNodeId = RequestParameters.getInt("nodeId");
		boolean isAdd = iCurrentPageNodeId == AddPageNodeId ? true : false;
		int iCurrentButtonId = EButtonType.Submit;
		ResultMessage tempAuth = Utits.IsOperateAuth(iRangePage, iCurrentPageNodeId, iCurrentButtonId);
		if (!tempAuth.getErrorCode().equals(EErrorCode.Success)) {
			return JsonUtils.toJSONString(tempAuth);
		}

		String deptCode = RequestParameters.getString("deptCode");
		String deptName = RequestParameters.getString("deptName");
		String remark = RequestParameters.getString("remark");
		if (deptName.length() <= 0) {
			ResultMessage resultMessage = new ResultMessage();
			resultMessage.setErrorCode(EErrorCode.Error);
			resultMessage.setErrorMessage("部门名称不能为空.");
			return JsonUtils.toJSONString(resultMessage);
		}

		Dept item = new Dept();
		UUID id = RequestParameters.getGuid("id");
		if (isAdd) {
			item.setCreateDate(DateUtils.now());
			item.setIsValid(1);
		} else {
			item.setDeptId(id.toString());
		}
		item.setOperateDate(DateUtils.now());
		item.setDeptCode(deptCode);
		item.setDeptName(deptName);
		item.setRemark(remark);
		
		boolean isFlag = deptService.addOrUpdate(item, isAdd);
		if (isFlag) {
			ResultMessage resultMessage = new ResultMessage();
			resultMessage.setErrorCode(EErrorCode.Success);
			resultMessage.setErrorMessage("操作成功.");
			return JsonUtils.toJSONString(resultMessage);
		} else {
			ResultMessage resultMessage = new ResultMessage();
			resultMessage.setErrorCode(EErrorCode.Error);
			resultMessage.setErrorMessage("操作失败.");
			return JsonUtils.toJSONString(resultMessage);
		}
	}

	/**
	 * 批操作记录
	 * 
	 * @return
	 */
	@RequestMapping(value = "/batchOperate")
	@ResponseBody
	public String batchOperate() {
		// 权限控制
		int[] iRangePage = { ListPageNodeId };
		int iCurrentPageNodeId = RequestParameters.getInt("nodeId");
		int[] iRangeButton = { EButtonType.PhyDelete, EButtonType.Delete, EButtonType.Enable, EButtonType.Disable };
		int iCurrentButtonId = RequestParameters.getInt("oButtonId");
		ResultMessage tempAuth = Utits.IsOperateAuth(iRangePage, iCurrentPageNodeId, iRangeButton, iCurrentButtonId);
		if (!tempAuth.getErrorCode().equals(EErrorCode.Success)) {
			return JsonUtils.toJSONString(tempAuth);
		}

		String paramIds = RequestParameters.getString("ids");
		if (StringUtils.isEmpty(paramIds)) {
			ResultMessage resultMessage = new ResultMessage();
			resultMessage.setErrorCode(EErrorCode.Error);
			resultMessage.setErrorMessage("参数不合法.");
			return JsonUtils.toJSONString(resultMessage);
		}

		String[] strids = paramIds.split(",");
		ArrayList<String> arrayList = new ArrayList<String>();
		for (int i = 0; i < strids.length; i++) {
			if (RegexValidate.isGuid(strids[i])) {
				arrayList.add(strids[i]);
			}
		}
		String[] array = new String[arrayList.size()];
		String[] ids = arrayList.toArray(array);

		if (ids.length <= 0) {
			ResultMessage resultMessage = new ResultMessage();
			resultMessage.setErrorCode(EErrorCode.Error);
			resultMessage.setErrorMessage("参数不合法.");
			return JsonUtils.toJSONString(resultMessage);
		}
		
		boolean isFlag = deptService.batchExecuteByIds(ids, iCurrentButtonId + "");
		if (isFlag) {
			ResultMessage resultMessage = new ResultMessage();
			resultMessage.setErrorCode(EErrorCode.Success);
			resultMessage.setErrorMessage("操作成功.");
			return JsonUtils.toJSONString(resultMessage);
		} else {
			ResultMessage resultMessage = new ResultMessage();
			resultMessage.setErrorCode(EErrorCode.Error);
			resultMessage.setErrorMessage("操作失败.");
			return JsonUtils.toJSONString(resultMessage);
		}
	}
	/************** JsonResult End ******************/
}
