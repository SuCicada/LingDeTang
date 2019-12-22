package org.subbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.subbs.dao.Page;
import org.subbs.entity.Post;
import org.subbs.service.PostService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: peng
 * Date: 10/22/19
 * Time: 9:51 AM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@RestController
@RequestMapping(value = "/post")
public class PostManagerController extends BaseController {
    @Autowired
    PostService postService;

    @RequestMapping(value="/", method = RequestMethod.POST)
    public ResponseEntity createTopic(Post post, int userId){
        post.setUserId(userId);
        postService.save(post);

        Result result = new Result();
        result.setSuccess(1);
        Map data = new HashMap();
        data.put("postId", post.getPostId());
        result.setData(data);
        return new ResponseEntity(result, HttpStatus.OK);
    }


    /**
     * 分页查询
     * @return
     */
    @RequestMapping(value = {"","/"}, method = RequestMethod.GET)
    public ResponseEntity listPosts(int topicId, int pageNo, int pageSize) {
        Page posts = postService.getPagedTopics(topicId,pageNo,pageSize);
        System.out.println(posts);
        Result result = new Result();
        result.setSuccess(1);
        Map data = new HashMap();
        data.put("posts",posts);
        result.setData(data);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    /**
     * 查找一个
     */
    @RequestMapping(value = "/{postId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Result getPost(@PathVariable("topicId") int postId) {
        System.out.println("Fetching Topic with id " + postId);
        Result result = postService.getPostById(postId);
        return result;
    }
}
