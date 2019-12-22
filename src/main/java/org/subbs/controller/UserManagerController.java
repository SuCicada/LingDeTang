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
    public Result getPost(@PathVariable("userId")int userId) {
        System.out.println("Fetching User with id " + userId);
        Map userInfo = (Map)userService.getUserById(userId,false);
        Result result = new Result(1,"");
        if(userInfo == null){
            return new Result(0,"not found user");
        }else{
            return new Result(1,"",userInfo);
        }
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable("userId") int userId, @RequestBody User user) {
        System.out.println("Updating User " + userId);

        User currentUser = userService.getUserById((int) userId);

        if (currentUser==null) {
            System.out.println("User with id " + userId + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        userService.update(currentUser);
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }
}
