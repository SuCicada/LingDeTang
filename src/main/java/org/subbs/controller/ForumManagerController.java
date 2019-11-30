package org.subbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.subbs.entity.Forum;
import org.subbs.entity.User;
import org.subbs.service.ForumService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@RequestMapping("/forum")
@RestController
public class ForumManagerController extends BaseController {

    @Autowired
    private ForumService forumService;

    /**
     * 查找所有
     * @return
     */
    @RequestMapping(value = {"","/"}, method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllForums() {
        List<Forum> forums = forumService.getAllForums();
        System.out.println(forums);
        Result result = new Result();
        if (forums.isEmpty()) {
            result.setSuccess(0);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        result.setSuccess(1);
        Map data = new HashMap();
        data.put("forums",forums);
        result.setData(data);
        return new ResponseEntity(result, HttpStatus.OK);
    }

}
