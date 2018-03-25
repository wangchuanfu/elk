package com.demodashi.base;

import java.io.IOException;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demodashi.aop.annotation.ControllerLogAnnotation;

/**
 * 
 * @author xgchen
 *
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Inject
	UserService userApplication;

	@ResponseBody
	@RequestMapping("/login.do")
	public Map<String, Object> login(UserVO user, HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap) {
		try {
			if (StringUtils.isBlank(user.getPassword())) {
				return error("无此用户或登录密码错误！");
			}
			
			user = userApplication.validate(user.getId(), user.getPassword());
			if (user == null) {
				return error("无此用户或登录密码错误！");
			}

			request.getSession().setAttribute("USER", user);
			return success("登录成功！");
		} catch (Exception e) {
			return error(e);
		}
	}

	@RequestMapping("/logout.do")
	public String logout(UserVO vo, HttpServletRequest request) {
		request.getSession().removeAttribute("USER");
		return "redirect:/";
	}

	@ResponseBody
	@RequestMapping("/editPassword.do")
	@ControllerLogAnnotation(description = "接受修改密码的请求")
	public Map<String, Object> changePassword(ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String message = null;
		String result = null;
		Object vo = request.getSession().getAttribute("USER");
		if (vo == null) {
			message = "操作失败：对象不能为空！";
		} else if (StringUtils.isBlank(request.getParameter("newPassword"))) {
			message = "新登陆密码不能为空！";
		}
		if (message == null) {
			try {
				userApplication.changePassword((UserVO)vo, request.getParameter("newPassword"));
				message = "修改成功！";
				result = ConstantBean.SUCCESS;
			} catch (Exception e) {
				message = e.getMessage();
				result = ConstantBean.SYSERR;
			}
		} else {
			result = ConstantBean.SYSERR;
		}
		
		return toMap("data", message, "result", result);
	}

	
}
