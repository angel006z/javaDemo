package com.meida.backend.basic.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.meida.backend.basic.vo.LeftAuthRoleNodeVo;
import com.meida.backend.basic.service.inter.IAuthRoleNodeService;
import com.meida.base.vo.TreeVo;
import com.meida.common.cookie.CookieUtils;
import com.meida.common.util.SessionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.meida.backend.basic.po.User;
import com.meida.backend.basic.service.inter.IUserService;
import com.meida.base.vo.ResultMessage;
import com.meida.common.util.JsonUtils;
import com.meida.common.util.RequestParameters;
import com.meida.common.util.Utils;
import com.meida.common.util.constant.EErrorCode;
import com.meida.common.util.security.HashEncryptUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/backend/basic/home")
public class HomeController {
    @Autowired
    private IUserService userService;

    @Autowired
    private IAuthRoleNodeService authRoleNodeService;

    @RequestMapping(value = "/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title", "");
        return modelAndView;
    }

    @RequestMapping(value = "/modifyPassword")
    @ResponseBody
    public String modifyPassword() {
        ResultMessage resultMessage = new ResultMessage();
        if (!Utils.isLogin()) {
            resultMessage.setCode(EErrorCode.NoLogin);
            resultMessage.setMessage("未登录.");
            return JsonUtils.toJSONString(resultMessage);
        }
        String oldPassword = RequestParameters.getString("OldPassword");
        if (oldPassword.length() <= 0) {
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("旧密码不能为空.");
            return JsonUtils.toJSONString(resultMessage);
        }
        String newPassword = RequestParameters.getString("NewPassword");
        if (newPassword.length() <= 0) {
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("新密码不能为空.");
            return JsonUtils.toJSONString(resultMessage);
        }

        UUID currentUserId = Utils.getCurrentUserId();
        User item = userService.getObjectById(currentUserId.toString());
        if (item == null) {
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("当前用户不存在.");
            return JsonUtils.toJSONString(resultMessage);
        }
        if (!HashEncryptUtils.backendPassword(oldPassword).equals(item.getPassword())) {
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("旧密码有误.");
            return JsonUtils.toJSONString(resultMessage);
        }

        boolean isFlag = userService.changePassword(item.getUserId(), HashEncryptUtils.backendPassword(newPassword));
        if (isFlag) {
            resultMessage.setCode(EErrorCode.Success);
            resultMessage.setMessage("操作成功.");
            return JsonUtils.toJSONString(resultMessage);
        } else {
            resultMessage.setCode(EErrorCode.Error);
            resultMessage.setMessage("操作失败.");
            return JsonUtils.toJSONString(resultMessage);
        }
    }

    @RequestMapping(value = "/quitLogin")
    @ResponseBody
    public String quitLogin() {

        SessionHelper.removeString("USERID");

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        CookieUtils.deleteCookie(request, response, "USERNAME");
        CookieUtils.deleteCookie(request, response, "PASSWORD");

        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setCode(EErrorCode.Success);
        resultMessage.setMessage("退出系统成功.");
        return JsonUtils.toJSONString(resultMessage);
    }

    @RequestMapping(value = "/achieveLeftAuthNode")
    @ResponseBody
    public String achieveLeftAuthNode() {
        if (Utils.isLogin()) {
            List<LeftAuthRoleNodeVo> listLeftAuthNode = authRoleNodeService.getListByLeftUserId(Utils.getCurrentUserId(), Utils.isSuper());
            if (listLeftAuthNode != null) {
                List<TreeVo> listTree = new ArrayList<TreeVo>();
                TreeVo treeVo;
                for (LeftAuthRoleNodeVo itemNode : listLeftAuthNode) {
                    treeVo = new TreeVo();
                    treeVo.setId(itemNode.getNodeId() + "");
                    treeVo.setPid(itemNode.getParentId() + "");
                    treeVo.setName(itemNode.getNodeName());
                    treeVo.setTarget(itemNode.getNodeName());
                    treeVo.setUrl(itemNode.getNodePath());
                    treeVo.setNodeClassName(itemNode.getNodeClassName());
                    listTree.add(treeVo);
                }
                if (listTree.size() > 0) {
                    return JsonUtils.toJSONString(listTree);
                }
            }
        }

        return JsonUtils.toJSONString("[]");
    }
}
