package org.subbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.subbs.controller.Result;
import org.subbs.dao.UserDao;
import org.subbs.entity.User;
import org.subbs.exception.UserExistException;
import org.subbs.util.JavaWebTokenManager;
import org.subbs.util.O2M;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户管理服务类，负责查询用户、注册用户、锁定用户等操作
 */
@Service
public class UserService {
	
	private UserDao userDao;
//	private LoginLogDao loginLogDao;

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

//	@Autowired
//	public void setLoginLogDao(LoginLogDao loginLogDao) {
//		this.loginLogDao = loginLogDao;
//	}

	/**
	 * 注册一个新用户,如果用户名已经存在此抛出UserExistException的异常
	 * @param user
	 */
	public void save(User user) throws UserExistException {
		String username = user.getUsername();
		User u = this.getUserByUserName(username);
		if(u != null){
		    throw new UserExistException(username);
		}else{
			if(user.getUserSex() == null){
				user.setUserSex(0);
			}
			//		    user.setCredit(100);
//            user.setUserType(1);
            userDao.save(user);
		}
	}
	
	/**
     * 更新用户
     * @param user 
     */
    public void update(User user){
        userDao.update(user);
    }
	
	   /**
     * 根据用户名/密码查询 User对象
     * @param userName 用户名
     * @return User
     */
    public User getUserByUserName(String userName){
        return userDao.getUserByUserName(userName);
    }
	
	
	/**
	 * 根据userId加载User对象
	 * @param userId
	 * @return
	 */
	public User getUserById(int userId){
		return userDao.get(userId);
	}
	

	
	/**
	 * 根据用户名为条件，执行模糊查询操作 
	 * @param userName 查询用户名
	 * @return 所有用户名前导匹配的userName的用户
	 */
	public List<User> queryUserByUserName(String userName){
		return userDao.queryUserByUserName(userName);
	}
	
	/**
	 * 获取所有用户
	 * @return 所有用户
	 */
	public List<User> getAllUsers(){
		return userDao.loadAll();
	}

	public Result doLogin(User user){
		User dbUser = getUserByUserName(user.getUsername());
//        ModelAndView mav = new ModelAndView();
//        System.out.println(dbUser);
		Result res = new Result();
		res.setSuccess(0);

		if (dbUser == null) {
			res.setMsg("用户名不存在");
		} else if (!dbUser.getUserPassword().equals(user.getUserPassword())) {
			res.setMsg("用户密码不正确");
//        } else if (dbUser.getLocked() == User.USER_LOCK) {
//            mav.addObject("errorMsg", "用户已经被锁定，不能登录。");
		} else {
			res.setSuccess(1);

//            dbUser.setLastIp(request.getRemoteAddr());
//            dbUser.setLastVisit(new Date());
			Map<String,Object> loginInfo = new HashMap();
			loginInfo.put("userId",dbUser.getUserId());
			String sessionId = JavaWebTokenManager.createJavaWebToken(loginInfo);

			System.out.println("sessionID: "+sessionId);

			Map data = new HashMap();
//            Map userInfo = new HashMap();
			data.put("sessionID",sessionId);
			String info[] = new String[]{"userId","username","userPhoto"};
			data.put("userInfo", O2M.parse(user,info));

			res.setData(data);
			loginSuccess(dbUser);
//            setSessionUser(request,dbUser);
//            String toUrl = (String)request.getSession().getAttribute(CommonConstant.LOGIN_TO_URL);
//            request.getSession().removeAttribute(CommonConstant.LOGIN_TO_URL);
			//如果当前会话中没有保存登录之前的请求URL，则直接跳转到主页
//            if(StringUtils.isEmpty(toUrl)){
//                toUrl = "/index.html";
//            }
		}
		return res;
	}
	/**
	 * 登陆成功
	 * @param user
	 */
	public void loginSuccess(User user) {
//		user.setCredit( 5 + user.getCredit());
//		LoginLog loginLog = new LoginLog();
//		loginLog.setUser(user);
//		loginLog.setIp(user.getLastIp());
//		loginLog.setLoginDate(new Date());
        userDao.update(user);
//        loginLogDao.save(loginLog);
	}

	public boolean isUserExist(User user) {
		return getUserById(user.getUserId()) != null;
	}

	public void remove(User user){
		userDao.remove(user);
	}

	public void removeAll() {
		userDao.removeAll("User");
	}

}
