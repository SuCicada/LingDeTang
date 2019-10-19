package org.subbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.subbs.dao.UserDao;
import org.subbs.entity.User;
import org.subbs.exception.UserExistException;

import java.util.Date;
import java.util.List;

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
	public void register(User user) throws UserExistException {
		String username = user.getUsername();
		User u = this.getUserByUserName(username);
		if(u != null){
		    throw new UserExistException(username);
		}else{
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
	
}
