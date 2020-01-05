package org.subbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.subbs.controller.Result;
import org.subbs.dao.UserDao;
import org.subbs.entity.User;
import org.subbs.exception.UserExistException;
import org.subbs.util.EntityUtil;
import org.subbs.util.JavaWebTokenManager;
import org.subbs.util.O2M;
import org.subbs.util.UploadUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

			byte[] img = UploadUtil.getFileBytes("userPhoto.jpg");
			user.setUserPhoto(img);
            user.setUserNickname(username);
			//		    user.setCredit(100);
//            user.setUserType(1);
            userDao.save(user);
		}
	}
	
	/**
     * 更新用户基本信息，不包括头像和密码
	 * 或者说user 里有什么 就更新什么
     * @param user 
     */
    public Result updateInfo(User user){
    	int userId = user.getUserId();
		Object currentUser = getUserById((int) userId,true);

		if (currentUser==null) {
			System.out.println("User with id " + userId + " not found");
//			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
			return new Result(0,"user not found");
		}

		User newUser = (User)EntityUtil.UpdateEntity(currentUser,user);
        userDao.update(newUser);
		Map res = new HashMap();

		res.put("user",O2M.parseExclude(newUser,"userPassword"));
		return new Result(1,"",res);
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
	public Object getUserById(int userId) {
		return getUserById(userId,false);
	}

	public Object getUserById(int userId,Boolean includePassword){
		User user = userDao.get(userId);
		if(user == null){
			return null;
		}
		if(includePassword == false){
			String info[] = new String[]{"userPassword"};
			Map userInfo = O2M.parseExclude(user,info);
			return userInfo;
		}else{
			return user;
		}
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

	public Result doLogin(User user, HttpServletRequest request, HttpSession session, Model model){
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
//			String info[] = new String[]{"userId","username","userPhoto"};
			String info[] = new String[]{"password"};
			Map userInfo = O2M.parseExclude(dbUser,info);
			data.put("userInfo", userInfo );

			res.setData(data);
			loginSuccess(dbUser);
//			request.getSession().setAttribute("user",userInfo);
//			session.setAttribute("user",userInfo);
			model.addAttribute("user",userInfo);
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

    public Result doLogout(User user) {
		System.out.println("user "+user.getUserId()+" logout");
//		request.removeAttribute("user");
		/*执行登出操作：
		* 方案1：redis存储登录的用户
		* 方案2：将前端放入后端项目中，使用session
		* */
		return new Result(1);
    }
}
