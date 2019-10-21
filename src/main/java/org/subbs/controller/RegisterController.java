
package org.subbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.subbs.entity.User;
import org.subbs.exception.UserExistException;
import org.subbs.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户注册控制器
 */
@Controller                   
public class RegisterController extends BaseController {
	/**
	 * 自动注入
	 */
	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 用户登录
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public Map register(HttpServletRequest request, User user){
//		ModelAndView view = new ModelAndView();
//		view.setViewName("/success");
		System.out.println(user);
		try {
			userService.save(user);
		} catch (UserExistException e) {
//			view.addObject("errorMsg", "用户名已经存在，请选择其它的名字。");
//			view.setViewName("forward:/register.jsp");
		}
		setSessionUser(request,user);
		Map res = new HashMap();
		res.put("success","1");
		return res;
		//		return view;
	}
	
}
