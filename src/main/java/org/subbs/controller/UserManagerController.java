package org.subbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.subbs.entity.Forum;
import org.subbs.entity.User;
import org.subbs.service.UserService;

import java.io.IOException;
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
    public Result getUser(@PathVariable("userId") int userId) {
        System.out.println("Fetching User with id " + userId);
        Object userInfo = userService.getUserById(userId, false);
        Map data = new HashMap();
        data.put("user",userInfo);
        if (userInfo == null) {
            return new Result(0, "not found user");
        } else {
            return new Result(1, "", data);
        }
    }


    @RequestMapping(value = "/put/{userId}",
            method = RequestMethod.POST)
    public Result updateUser(User user,
                             @PathVariable("userId") int USERID){
        System.out.println("Updating User " + USERID);
        if (user.getUserId() != USERID) {
            user.setUserId(USERID);
        }
        return userService.updateInfo(user);
    }

    @RequestMapping(value = "/put/photo/{userId}",
            method = RequestMethod.POST)
    public Result updateUserPhoto(User user,
                             @PathVariable("userId") int USERID,
                             @RequestParam("file")MultipartFile userPhotoFile
    ) throws IOException {
        System.out.println("Updating User " + USERID);
        if (user.getUserId() != USERID) {
            user.setUserId(USERID);
        }
        if(userPhotoFile != null){
            user.setUserPhoto(userPhotoFile.getBytes());
        }
        return userService.updateInfo(user);
    }

}
