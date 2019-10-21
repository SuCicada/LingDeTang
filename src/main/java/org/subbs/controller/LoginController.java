package org.subbs.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.subbs.cons.CommonConstant;
import org.subbs.entity.User;
import org.subbs.service.UserService;

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
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 用户登陆
     * @param request
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/doLogin",method = RequestMethod.POST)
    public Map login(HttpServletRequest request, User user) {

        User dbUser = userService.getUserByUserName(user.getUsername());
//        ModelAndView mav = new ModelAndView();
        System.out.println(dbUser);
        Map res = new HashMap();
        res.put("success",0);

        if (dbUser == null) {
            res.put("msg","用户名不存在");
        } else if (!dbUser.getUserPassword().equals(user.getUserPassword())) {
            res.put("msg","用户密码不正确");
//        } else if (dbUser.getLocked() == User.USER_LOCK) {
//            mav.addObject("errorMsg", "用户已经被锁定，不能登录。");
        } else {
            res.put("success",1);
//            dbUser.setLastIp(request.getRemoteAddr());
//            dbUser.setLastVisit(new Date());
            userService.loginSuccess(dbUser);
            setSessionUser(request,dbUser);
            String toUrl = (String)request.getSession().getAttribute(CommonConstant.LOGIN_TO_URL);
            request.getSession().removeAttribute(CommonConstant.LOGIN_TO_URL);
            //如果当前会话中没有保存登录之前的请求URL，则直接跳转到主页
            if(StringUtils.isEmpty(toUrl)){
                toUrl = "/index.html";
            }
        }
        return res;
    }
}
