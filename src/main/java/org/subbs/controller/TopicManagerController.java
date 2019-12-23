package org.subbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.subbs.dao.Page;
import org.subbs.entity.Forum;
import org.subbs.entity.Topic;
import org.subbs.entity.User;
import org.subbs.service.TopicService;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.sql.Timestamp;
import java.util.Date;
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
@RestController
@RequestMapping(value = "/topic")
public class TopicManagerController extends BaseController {
    @Autowired
    TopicService topicService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity createTopic(Topic topic, String userId, HttpServletRequest request, UriComponentsBuilder ucBuilder) {
//        int userId = ((Integer) ((Map)request.getAttribute("requestUser")).get("userId"));
        topic.setUserId(Integer.parseInt(userId));
        topic.setTopicViewCount(0);
        topic.setTopicPostCount(0);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        topic.setTopicCreateDate(now);
        topic.setTopicUpdateDate(now);
        topicService.save(topic);

        /* 需要302重定向到新发布的页面使用这个*/
//        HttpHeaders headers = new HttpHeaders();
//        URI uri = ucBuilder.path("/topic/{id}").buildAndExpand(topic.getTopicId()).toUri();
//        headers.setLocation(uri);
//        return new ResponseEntity(headers, HttpStatus.FOUND);
        Result result = new Result();
        result.setSuccess(1);
        Map data = new HashMap();
        data.put("topicId", topic.getTopicId());
        result.setData(data);
        return new ResponseEntity(result, HttpStatus.OK);

    }


    /**
     * 分页查询
     *
     * @return
     */
    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public ResponseEntity listTopics(int forumId, int pageNo, int pageSize) {
        Page topics = topicService.getPagedTopics(forumId, pageNo, pageSize);
        System.out.println(topics);
        Result result = new Result();
        result.setSuccess(1);
        Map data = new HashMap();
        data.put("topics", topics);
        result.setData(data);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/search/", method = RequestMethod.GET)
    public ResponseEntity listSearchTopics(int searchForumId, String searchContent, int pageNo, int pageSize) {
        Page topics = topicService.getSearchPageTopics(searchForumId, searchContent, pageNo, pageSize);
        System.out.println(topics);
        Result result = new Result();
        result.setSuccess(1);
        Map data = new HashMap();
        data.put("topics", topics);
        result.setData(data);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    /**
     * 查找一个
     */
    @RequestMapping(value = "/{topicId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Result getTopic(@PathVariable("topicId") int topicId) {
        System.out.println("Fetching Topic with id " + topicId);
        Result result = topicService.getTopicById(topicId);
        topicService.addViewCount(topicId);
        return result;
    }


}
