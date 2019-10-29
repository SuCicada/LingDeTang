package org.subbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.subbs.dao.ForumDao;
import org.subbs.entity.Forum;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: peng
 * Date: 10/26/19
 * Time: 4:07 PM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Service
public class ForumService {
    @Autowired
    private ForumDao forumDao;

    public List<Forum> getAllForums(){
        return forumDao.loadAll();
    }

}
