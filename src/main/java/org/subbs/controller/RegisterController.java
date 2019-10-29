
package org.subbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.subbs.entity.User;
import org.subbs.exception.UserExistException;
import org.subbs.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
	public Result register(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
//		ModelAndView view = new ModelAndView();
//		view.setViewName("/success");
//		System.out.println(user);
		try {
			userService.save(user);
		} catch (UserExistException e) {
            return new Result(0,"用户名已经存在");
		    //			view.addObject("errorMsg", "用户名已经存在，请选择其它的名字。");
//			view.setViewName("forward:/register.jsp");
		}
		setSessionUser(request,user);
		request.getRequestDispatcher("/doLogin").forward(request, response);
		return new Result(1);
		//		return view;
	}
	
}
