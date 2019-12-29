package org.subbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.subbs.entity.Forum;
import org.subbs.entity.User;
import org.subbs.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: peng
 * Date: 10/22/19
 * Time: 9:51 AM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
//@CrossOrigin(origins = {
//        "http://sxuldt.gitee.io",
//        "http://localhost"},
//        maxAge = 3600)
//@Controller
@RequestMapping("/user")
@RestController
public class UserManagerController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Result getUser(@PathVariable("userId")int userId) {
        System.out.println("Fetching User with id " + userId);
        Map userInfo = (Map)userService.getUserById(userId,false);
        Result result = new Result(1,"");
        if(userInfo == null){
            return new Result(0,"not found user");
        }else{
            return new Result(1,"",userInfo);
        }
    }

    @RequestMapping(value = "/put/{userId}", method = RequestMethod.POST)
    public ResponseEntity<User> updateUser(User user,String userSignature,@PathVariable("userId") int USERID) {
        System.out.println("Updating User " + USERID);
        if(user.getUserId()!=USERID){
            user.setUserId(USERID);
        }
        userService.updateInfo(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
}
