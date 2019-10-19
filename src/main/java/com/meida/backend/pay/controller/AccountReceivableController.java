package com.meida.backend.pay.controller;

import com.meida.backend.pay.vo.AccountReceivableVo;
import com.meida.backend.pay.po.AccountReceivableInfo;
import com.meida.backend.pay.dto.*;
import com.meida.base.vo.*;
import com.meida.backend.base.dto.*;
import com.meida.backend.base.vo.*;
import com.meida.backend.base.util.*;
import com.meida.backend.pay.service.inter.AccountReceivableService;

import com.meida.common.constant.EErrorCode;
import com.meida.common.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * AccountReceivableController
 * @author BING
 * @date 2019-10-19 18:56:35
 */
@Controller("backendAccountReceivableController")
@RequestMapping("/backend/pay/accountReceivable")
public class AccountReceivableController {

	private static final long serialVersionUID = 1L;

	@Autowired
		private AccountReceivableService accountReceivableService;

	@RequestMapping("/list")
	public String list() {
        return "/backend/pay/AccountReceivable/list";
	}

	@RequestMapping("/detail")
	public String detail() {
        return "/backend/pay/AccountReceivable/detail";
    }

	private final int listPageNodeId = 200;
	private final int addPageNodeId = 201;
	private final int editPageNodeId = 202;
	private final int detailPageNodeId = 203;

	/**
	 * 初始化页面参数
	 *
	 * @return
	 */
	@RequestMapping("/initPageParam")
	@ResponseBody
	public String initPageParam(@RequestBody InitPageParamDto paramDto) {
		ResultMessage resultValidate = ValidatorUtils.validateEntity(paramDto);
		if (!resultValidate.getCode().equals(EErrorCode.Success)) {
			return JsonUtils.toJSONString(resultValidate);
		}
		ResultInitPageParam resultInitPageParam = new ResultInitPageParam();
		resultInitPageParam.setCode(EErrorCode.Success);
		resultInitPageParam.setMessage("初始化页面参数成功");
		resultInitPageParam.setListPageNodeId(listPageNodeId);
		resultInitPageParam.setAddPageNodeId(addPageNodeId);
		resultInitPageParam.setEditPageNodeId(editPageNodeId);
		resultInitPageParam.setDetailPageNodeId(detailPageNodeId);
		int nodeId = paramDto.getNodeId();
		CurrentUserDto currentUser = BackendUtils.getCurrentUserDto();
		String userId = currentUser.getUserId();
		boolean isSuper = currentUser.getIsSuper();
		resultInitPageParam.setOperateButton(BackendUtils.getOperateButton(nodeId, userId, isSuper));
		return JsonUtils.toJSONString(resultInitPageParam);
	}

	/**
	 * 查询多条记录
	 *
	 * @return
	 */
	@RequestMapping("/searchList")
	@ResponseBody
	public String searchList(@RequestBody AccountReceivableListParamDto paramDto) {
		ResultMessage resultValidate = ValidatorUtils.validateEntity(paramDto);
		if (!resultValidate.getCode().equals(EErrorCode.Success)) {
			return JsonUtils.toJSONString(resultValidate);
		}
		if (paramDto.getPagination() == null) {
			paramDto.setPagination(new Pagination());
		}
		if (paramDto.getPagination().getCurrentPage() <= 0) {
			paramDto.getPagination().setCurrentPage(1);
		}
		if (paramDto.getPagination().getPageSize() > 100) {
			paramDto.getPagination().setPageSize(100);
		}

		AccountReceivableListDto whereItemDto = new AccountReceivableListDto();
		whereItemDto.setCurrentUserDto(BackendUtils.getCurrentUserDto());
		whereItemDto.setPagination(paramDto.getPagination());
		   		whereItemDto.setReceivableNo(paramDto.getReceivableNo());
	   		whereItemDto.setMemberId(paramDto.getMemberId());
	   		whereItemDto.setOrderNo(paramDto.getOrderNo());
	   		whereItemDto.setReceivableDate(paramDto.getReceivableDate());
	   		whereItemDto.setReceivableAmount(paramDto.getReceivableAmount());
	   		whereItemDto.setReceivableReason(paramDto.getReceivableReason());
	   		whereItemDto.setReceivableType(paramDto.getReceivableType());
	   		whereItemDto.setReceivableChannel(paramDto.getReceivableChannel());
	   		whereItemDto.setCreateDate(paramDto.getCreateDate());
	   		whereItemDto.setCreateUserId(paramDto.getCreateUserId());
	   		whereItemDto.setCreateUser(paramDto.getCreateUser());
	   		whereItemDto.setUpdateDate(paramDto.getUpdateDate());
	   		whereItemDto.setUpdateUserId(paramDto.getUpdateUserId());
	   		whereItemDto.setUpdateUser(paramDto.getUpdateUser());
	   		whereItemDto.setIsValid(paramDto.getIsValid());
	   		whereItemDto.setRemark(paramDto.getRemark());
	   		whereItemDto.setSignature(paramDto.getSignature());
	   	
		List<AccountReceivableInfo> list = accountReceivableService.getListPage(whereItemDto);
		ResultList resultList = new ResultList();
		resultList.setCode(EErrorCode.Success);
		resultList.setMessage("操作成功.");
		resultList.setData(list);
		resultList.setPagination(whereItemDto.getPagination());
		return JsonUtils.toJSONString(resultList);
	}

	/**
	 * 查询单条记录
	 *
	 * @return
	 */
	@RequestMapping("/initSingle")
	@ResponseBody
	public String initSingle(@RequestBody SingleParamDto paramDto) {
		ResultMessage resultValidate = ValidatorUtils.validateEntity(paramDto);
		if (!resultValidate.getCode().equals(EErrorCode.Success)) {
			return JsonUtils.toJSONString(resultValidate);
		}
		if (!RegexValidate.isUuid(paramDto.getId())) {
			ResultMessage resultMessage = new ResultMessage();
			resultMessage.setCode(EErrorCode.Error);
			resultMessage.setMessage("id参数不合法.");
			return JsonUtils.toJSONString(resultMessage);
		}
		AccountReceivableInfo item = accountReceivableService.getObjectById(paramDto.getId());
		ResultDetail resultDetail = new ResultDetail();
		resultDetail.setCode(EErrorCode.Success);
		resultDetail.setMessage("操作成功.");
		resultDetail.setData(item);
		return JsonUtils.toJSONString(resultDetail);
	}


	/**
	 * 新增、修改提交操作
	 *
	 * @return
	 */
	@RequestMapping("/submitOperate")
	@ResponseBody
	public String submitOperate(@RequestBody AccountReceivableSubmitParamDto paramDto) {
		ResultMessage resultValidate = ValidatorUtils.validateEntity(paramDto);
		if (!resultValidate.getCode().equals(EErrorCode.Success)) {
			return JsonUtils.toJSONString(resultValidate);
		}
		ResultMessage resultValidateItem = ValidatorUtils.validateEntity(paramDto.getItem());
		if (!resultValidateItem.getCode().equals(EErrorCode.Success)) {
			return JsonUtils.toJSONString(resultValidateItem);
		}
		AccountReceivableSubmitDto submitDto = new AccountReceivableSubmitDto();
		submitDto.setOperate(paramDto.getOperate());
		submitDto.setItem(paramDto.getItem());
		submitDto.setCurrentUserDto(BackendUtils.getCurrentUserDto());
		boolean isFlag = accountReceivableService.submit(submitDto);
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
	 * 删除操作
	 *
	 * @return
	 */
	@RequestMapping("/deleteOperate")
	@ResponseBody
	public String deleteOperate(@RequestBody DeleteParamDto paramDto) {
		ResultMessage resultValidate = ValidatorUtils.validateEntity(paramDto);
		if (!resultValidate.getCode().equals(EErrorCode.Success)) {
			return JsonUtils.toJSONString(resultValidate);
		}
		String paramIds = paramDto.getIds();
		if (StringUtils.isEmpty(paramIds)) {
			ResultMessage resultMessage = new ResultMessage();
			resultMessage.setCode(EErrorCode.Error);
			resultMessage.setMessage("ids参数不合法.");
			return JsonUtils.toJSONString(resultMessage);
		}

		String[] strids = paramIds.split(",");
		ArrayList<String> arrayList = new ArrayList<String>();
		for (int i = 0; i < strids.length; i++) {
			if (RegexValidate.isUuid(strids[i])) {
				arrayList.add(strids[i]);
			}
		}
		String[] array = new String[arrayList.size()];
		String[] ids = arrayList.toArray(array);
		if (ids.length <= 0) {
			ResultMessage resultMessage = new ResultMessage();
			resultMessage.setCode(EErrorCode.Error);
			resultMessage.setMessage("ids参数不合法.");
			return JsonUtils.toJSONString(resultMessage);
		}

		DeleteDto deleteDto = new DeleteDto();
		deleteDto.setIds(ids);
		deleteDto.setCurrentUserDto(BackendUtils.getCurrentUserDto());
		boolean isFlag = accountReceivableService.delete(deleteDto);
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
	 * 启用操作
	 *
	 * @return
	 */
	@RequestMapping("/enableOperate")
	@ResponseBody
	public String enableOperate(@RequestBody EnableParamDto paramDto) {
		ResultMessage resultValidate = ValidatorUtils.validateEntity(paramDto);
		if (!resultValidate.getCode().equals(EErrorCode.Success)) {
			return JsonUtils.toJSONString(resultValidate);
		}
		String paramIds = paramDto.getIds();
		if (StringUtils.isEmpty(paramIds)) {
			ResultMessage resultMessage = new ResultMessage();
			resultMessage.setCode(EErrorCode.Error);
			resultMessage.setMessage("ids参数不合法.");
			return JsonUtils.toJSONString(resultMessage);
		}

		String[] strids = paramIds.split(",");
		ArrayList<String> arrayList = new ArrayList<String>();
		for (int i = 0; i < strids.length; i++) {
			if (RegexValidate.isUuid(strids[i])) {
				arrayList.add(strids[i]);
			}
		}
		String[] array = new String[arrayList.size()];
		String[] ids = arrayList.toArray(array);
		if (ids.length <= 0) {
			ResultMessage resultMessage = new ResultMessage();
			resultMessage.setCode(EErrorCode.Error);
			resultMessage.setMessage("ids参数不合法.");
			return JsonUtils.toJSONString(resultMessage);
		}

		EnableDto enableDto = new EnableDto();
		enableDto.setIds(ids);
		enableDto.setCurrentUserDto(BackendUtils.getCurrentUserDto());
		boolean isFlag = accountReceivableService.enable(enableDto);
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
	 * 禁用操作
	 *
	 * @return
	 */
	@RequestMapping("/disableOperate")
	@ResponseBody
	public String disableOperate(@RequestBody DisableParamDto paramDto) {
		ResultMessage resultValidate = ValidatorUtils.validateEntity(paramDto);
		if (!resultValidate.getCode().equals(EErrorCode.Success)) {
			return JsonUtils.toJSONString(resultValidate);
		}
		String paramIds = paramDto.getIds();
		if (StringUtils.isEmpty(paramIds)) {
			ResultMessage resultMessage = new ResultMessage();
			resultMessage.setCode(EErrorCode.Error);
			resultMessage.setMessage("ids参数不合法.");
			return JsonUtils.toJSONString(resultMessage);
		}

		String[] strids = paramIds.split(",");
		ArrayList<String> arrayList = new ArrayList<String>();
		for (int i = 0; i < strids.length; i++) {
			if (RegexValidate.isUuid(strids[i])) {
				arrayList.add(strids[i]);
			}
		}
		String[] array = new String[arrayList.size()];
		String[] ids = arrayList.toArray(array);
		if (ids.length <= 0) {
			ResultMessage resultMessage = new ResultMessage();
			resultMessage.setCode(EErrorCode.Error);
			resultMessage.setMessage("ids参数不合法.");
			return JsonUtils.toJSONString(resultMessage);
		}

		DisableDto disableDto = new DisableDto();
		disableDto.setIds(ids);
		disableDto.setCurrentUserDto(BackendUtils.getCurrentUserDto());
		boolean isFlag = accountReceivableService.disable(disableDto);
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
	 * 彻底删除操作
	 *
	 * @return
	 */
	@RequestMapping("/physicalDeleteOperate")
	@ResponseBody
	public String PhysicalDeleteOperate(@RequestBody PhysicalDeleteParamDto paramDto) {
		ResultMessage resultValidate = ValidatorUtils.validateEntity(paramDto);
		if (!resultValidate.getCode().equals(EErrorCode.Success)) {
			return JsonUtils.toJSONString(resultValidate);
		}
		String paramIds = paramDto.getIds();
		if (StringUtils.isEmpty(paramIds)) {
			ResultMessage resultMessage = new ResultMessage();
			resultMessage.setCode(EErrorCode.Error);
			resultMessage.setMessage("ids参数不合法.");
			return JsonUtils.toJSONString(resultMessage);
		}

		String[] strids = paramIds.split(",");
		ArrayList<String> arrayList = new ArrayList<String>();
		for (int i = 0; i < strids.length; i++) {
			if (RegexValidate.isUuid(strids[i])) {
				arrayList.add(strids[i]);
			}
		}
		String[] array = new String[arrayList.size()];
		String[] ids = arrayList.toArray(array);
		if (ids.length <= 0) {
			ResultMessage resultMessage = new ResultMessage();
			resultMessage.setCode(EErrorCode.Error);
			resultMessage.setMessage("ids参数不合法.");
			return JsonUtils.toJSONString(resultMessage);
		}

		PhysicalDeleteDto physicalDeleteDto = new PhysicalDeleteDto();
		physicalDeleteDto.setIds(ids);
		physicalDeleteDto.setCurrentUserDto(BackendUtils.getCurrentUserDto());
		boolean isFlag = accountReceivableService.physicalDelete(physicalDeleteDto);
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

}
