package org.subbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.subbs.entity.User;
import org.subbs.service.UserService;
import org.subbs.util.AuthUtil;
import org.subbs.util.JavaWebTokenManager;
import org.subbs.util.O2M;

import javax.servlet.http.HttpServletRequest;
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
@CrossOrigin(origins = {
        "http://sxuldt.gitee.io",
        "http://localhost"},
        maxAge = 3600)
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
    public Result login(User user) {
        return userService.doLogin(user);
    }

    @ResponseBody
    @RequestMapping(value="/checkLogin", method = RequestMethod.GET)
    public void checkLogin(HttpServletRequest request){
        System.out.println("验证正确");
    }
}
