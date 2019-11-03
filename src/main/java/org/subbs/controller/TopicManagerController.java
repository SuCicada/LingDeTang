package org.subbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.subbs.entity.Topic;
import org.subbs.service.TopicService;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.sql.Timestamp;
import java.util.Date;
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

    @RequestMapping(value="/", method = RequestMethod.POST)
    public ResponseEntity createTopic(Topic topic, HttpServletRequest request, UriComponentsBuilder ucBuilder){
        int userId = ((Integer) ((Map)request.getAttribute("requestUser")).get("userId"));
        topic.setUserId(userId);
        topic.setTopicViewCount(0);
        topic.setTopicPostCount(0);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        topic.setTopicCreateDate(now);
        topic.setTopicUpdateDate(now);
        topicService.save(topic);

        HttpHeaders headers = new HttpHeaders();
        URI uri = ucBuilder.path("/topic/{id}").buildAndExpand(topic.getTopicId()).toUri();
        headers.setLocation(uri);

        return new ResponseEntity(headers, HttpStatus.CREATED);

    }
}
