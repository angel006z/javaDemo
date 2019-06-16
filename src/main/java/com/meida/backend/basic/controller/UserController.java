package com.meida.backend.basic.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.meida.backend.basic.domain.dto.UserListDto;
import com.meida.backend.basic.domain.po.Dept;
import com.meida.backend.basic.domain.po.Role;
import com.meida.backend.basic.domain.po.User;
import com.meida.backend.basic.domain.vo.UserVo;
import com.meida.backend.basic.service.inter.IDeptService;
import com.meida.backend.basic.service.inter.IRoleService;
import com.meida.backend.basic.service.inter.IUserService;
import com.meida.base.controller.BaseBackendController;
import com.meida.base.domain.vo.Pagination;
import com.meida.base.domain.vo.ResultDetail;
import com.meida.base.domain.vo.ResultList;
import com.meida.base.domain.vo.ResultMessage;
import com.meida.common.util.DateUtils;
import com.meida.common.util.JsonUtils;
import com.meida.common.util.RegexValidate;
import com.meida.common.util.RequestParameters;
import com.meida.common.util.StringUtils;
import com.meida.common.util.UUIDUtils;
import com.meida.common.util.Utits;
import com.meida.common.util.constant.EButtonType;
import com.meida.common.util.constant.EErrorCode;
import com.meida.common.util.constant.ENodePage;
import com.meida.common.util.constant.ESystemStatus;
import com.meida.common.util.security.HashEncryptUtils;
import com.meida.common.util.security.MD5Utils;

/**
 *用户管理
 */
@Controller
@RequestMapping(value = "/backend/basic/user")
public class UserController extends BaseBackendController {
	private int ListPageNodeId = ENodePage.UserListPage;
	private int AddPageNodeId = ENodePage.UserAddPage;
	private int EditPageNodeId = ENodePage.UserEditPage;
	private int DetailPageNodeId = ENodePage.UserDetailPage;
	
	@Autowired
	private IUserService userService;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IDeptService deptService;
   	
	@RequestMapping(value = "/list")
	public ModelAndView list() {
		int[] nodePages = { ListPageNodeId };
		int nodeId = RequestParameters.getInt("nodeId");
		ResultMessage accessPageAuth = Utits.accessPageAuth(nodePages, nodeId);
		if (!accessPageAuth.getCode().equals(EErrorCode.Success)) {
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
		int[] nodePages = { AddPageNodeId, EditPageNodeId, DetailPageNodeId };
		int nodeId = RequestParameters.getInt("nodeId");
		ResultMessage accessPageAuth = Utits.accessPageAuth(nodePages, nodeId);
		if (!accessPageAuth.getCode().equals(EErrorCode.Success)) {
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
		ResultMessage tempAuth = Utits.accessPageAuth(iRangePage, iCurrentPageNodeId);
		if (!tempAuth.getCode().equals(EErrorCode.Success)) {
			return JsonUtils.toJSONString(tempAuth);
		}
		// 当前页
		int iCurrentPage = RequestParameters.getInt("currentPage");
		// 一页的数量
		int iPageSize = RequestParameters.getInt("pageSize");
		iPageSize = iPageSize <= 0 ? 1 : iPageSize;
		iPageSize = iPageSize > 100 ? 100 : iPageSize;

		String userName = RequestParameters.getString("userName");

		UserListDto whereItem = new UserListDto();
		whereItem.setPagination(new Pagination(iCurrentPage, iPageSize));
		whereItem.setUserName(userName);
		
		List<UserVo> list = userService.getListByPage(whereItem);

		ResultList resultList = new ResultList();
		resultList.setCode(EErrorCode.Success);
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
		ResultMessage tempAuth = Utits.accessPageAuth(iRangePage, iCurrentPageNodeId);
		if (!tempAuth.getCode().equals(EErrorCode.Success)) {
			return JsonUtils.toJSONString(tempAuth);
		}
		
		UUID id = RequestParameters.getGuid("id");
		if (id != UUIDUtils.Empty) {			
			User item = userService.getObjectById(id.toString());
			ResultDetail resultDetail = new ResultDetail();
			resultDetail.setData(item);
			resultDetail.setCode(EErrorCode.Success);
			resultDetail.setMessage("操作成功.");
			return JsonUtils.toJSONString(resultDetail);
		} else {
			ResultMessage resultMessage = new ResultMessage();
			resultMessage.setCode(EErrorCode.Error);
			resultMessage.setMessage("参数不合法.");
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
		ResultMessage tempAuth = Utits.isOperateAuth(iRangePage, iCurrentPageNodeId, iCurrentButtonId);
		if (!tempAuth.getCode().equals(EErrorCode.Success)) {
			return JsonUtils.toJSONString(tempAuth);
		}

		String userCode = RequestParameters.getString("userCode");
		String userName = RequestParameters.getString("userName");
		String password = RequestParameters.getString("password");
		String deptId = RequestParameters.getString("deptId");
		String roleId = RequestParameters.getString("roleId");
		int userSort = RequestParameters.getInt("userSort");
		String remark = RequestParameters.getString("remark");
		if (userName.length() <= 0) {
			ResultMessage resultMessage = new ResultMessage();
			resultMessage.setCode(EErrorCode.Error);
			resultMessage.setMessage("用户名称不能为空.");
			return JsonUtils.toJSONString(resultMessage);
		}

		User item = new User();
		UUID id = RequestParameters.getGuid("id");
		if (isAdd) {
			item.setUserId(UUID.randomUUID().toString());
			item.setCreateDate(DateUtils.now());
			item.setIsValid(ESystemStatus.Valid);
			if(password.length()<=0) {
				password="111111";
			}			
		} else {
			item.setUserId(id.toString());			
		}
		if(password.length()>0) {
			item.setPassword(HashEncryptUtils.backendPassword(MD5Utils.md5(password)));
		}
		item.setOperateDate(DateUtils.now());
		item.setUserCode(userCode);
		item.setUserName(userName);		
		item.setDeptId(deptId);
		item.setRoleId(roleId);
		item.setUserSort(userSort);
		item.setRemark(remark);
		
		boolean isFlag = userService.addOrUpdate(item, isAdd);
		if (isFlag) {
			ResultMessage resultMessage = new ResultMessage();
			resultMessage.setCode(EErrorCode.Success);
			resultMessage.setMessage("操作成功.");
			return JsonUtils.toJSONString(resultMessage);
		} else {
			ResultMessage resultMessage = new ResultMessage();
			resultMessage.setCode(EErrorCode.Error);
			resultMessage.setMessage("操作失败.");
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
		ResultMessage tempAuth = Utits.isOperateAuth(iRangePage, iCurrentPageNodeId, iRangeButton, iCurrentButtonId);
		if (!tempAuth.getCode().equals(EErrorCode.Success)) {
			return JsonUtils.toJSONString(tempAuth);
		}

		String paramIds = RequestParameters.getString("ids");
		if (StringUtils.isEmpty(paramIds)) {
			ResultMessage resultMessage = new ResultMessage();
			resultMessage.setCode(EErrorCode.Error);
			resultMessage.setMessage("参数不合法.");
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
			resultMessage.setCode(EErrorCode.Error);
			resultMessage.setMessage("参数不合法.");
			return JsonUtils.toJSONString(resultMessage);
		}
		
		boolean isFlag = userService.batchExecuteByIds(ids, iCurrentButtonId + "");
		if (isFlag) {
			ResultMessage resultMessage = new ResultMessage();
			resultMessage.setCode(EErrorCode.Success);
			resultMessage.setMessage("操作成功.");
			return JsonUtils.toJSONString(resultMessage);
		} else {
			ResultMessage resultMessage = new ResultMessage();
			resultMessage.setCode(EErrorCode.Error);
			resultMessage.setMessage("操作失败.");
			return JsonUtils.toJSONString(resultMessage);
		}
	}
	
	
	@RequestMapping(value = "/initDataDept")
	@ResponseBody
	public String initDataDept() {
		// 权限控制
		int[] iRangePage = {ListPageNodeId, AddPageNodeId, EditPageNodeId, DetailPageNodeId };
		int iCurrentPageNodeId = RequestParameters.getInt("nodeId");		
		ResultMessage tempAuth = Utits.accessPageAuth(iRangePage, iCurrentPageNodeId);
		if (!tempAuth.getCode().equals(EErrorCode.Success)) {
			return JsonUtils.toJSONString(tempAuth);
		}
		
		List<Dept> list = deptService.getListByValid();
		ResultList resultList = new ResultList();
		resultList.setData(list);
		resultList.setCode(EErrorCode.Success);
		resultList.setMessage("操作成功.");
		return JsonUtils.toJSONString(resultList);
	}
	
	@RequestMapping(value = "/initDataRole")
	@ResponseBody
	public String initDataRole() {
		// 权限控制
		int[] iRangePage = {ListPageNodeId, AddPageNodeId, EditPageNodeId, DetailPageNodeId };
		int iCurrentPageNodeId = RequestParameters.getInt("nodeId");		
		ResultMessage tempAuth = Utits.accessPageAuth(iRangePage, iCurrentPageNodeId);
		if (!tempAuth.getCode().equals(EErrorCode.Success)) {
			return JsonUtils.toJSONString(tempAuth);
		}
		
		List<Role> list = roleService.getListByValid();
		ResultList resultList = new ResultList();
		resultList.setData(list);
		resultList.setCode(EErrorCode.Success);
		resultList.setMessage("操作成功.");
		return JsonUtils.toJSONString(resultList);
	}
	/************** JsonResult End ******************/
}
