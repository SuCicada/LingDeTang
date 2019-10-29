package org.subbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.subbs.entity.Forum;
import org.subbs.entity.User;
import org.subbs.service.ForumService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: peng
 * Date: 10/22/19
 * Time: 9:51 AM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@CrossOrigin(origins = {
        "http://sxuldt.gitee.io",
        "http://localhost"},
        maxAge = 3600)
@RequestMapping(value="/forum")
@RestController
public class ForumManagerController extends BaseController {

    @Autowired
    private ForumService forumService;

    /**
     * 查找所有
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllForums() {
        List<Forum> forums = forumService.getAllForums();
        if (forums.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(forums, HttpStatus.OK);
    }

    
}
