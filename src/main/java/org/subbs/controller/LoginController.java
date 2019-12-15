package org.subbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.subbs.entity.User;
import org.subbs.service.UserService;
import org.subbs.util.AuthUtil;
import org.subbs.util.JavaWebTokenManager;
import org.subbs.util.O2M;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: peng
 * Date: 10/18/19
 * Time: 8:47 PM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
//@CrossOrigin(origins = {
//        "http://sxuldt.gitee.io",
//        "http://localhost"},
//        maxAge = 3600)
@Controller
public class LoginController extends BaseController{
    @Autowired
    private UserService userService;

    /**
     * 用户登陆
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/doLogin",method = RequestMethod.POST)
    public Result login(User user, HttpServletRequest request, HttpSession session, Model model) {
        return userService.doLogin(user,request,session,model);
    }

    /**
     * 用户登出
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/doLogout",method = RequestMethod.POST)
    public Result logout(User user) {
        Result a = userService.doLogout(user);
        return a;
    }

    @ResponseBody
    @RequestMapping(value="/checkLogin", method = RequestMethod.GET)
    public Result checkLogin(HttpServletRequest request,HttpSession session,Model model){
        System.out.println("验证正确");
        return new Result(1);
    }


}
