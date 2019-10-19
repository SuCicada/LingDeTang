package org.subbs.exception;

/**
 * Created with IntelliJ IDEA.
 * User: peng
 * Date: 10/19/19
 * Time: 1:40 PM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class UserExistException extends Exception {
    public UserExistException(String username){
        super("用户: " + username + " 存在");
    }
}
