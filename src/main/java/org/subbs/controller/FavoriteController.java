package org.subbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.subbs.entity.Favorite;
import org.subbs.service.FavoriteService;

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
@RequestMapping("/favorite")
@RestController
public class FavoriteController extends BaseController {

    @Autowired
    private FavoriteService favoriteService;

//    /**
//     * 查找所有
//     * @return
//     */
//    @RequestMapping(value = {"","/"}, method = RequestMethod.GET)
//    public ResponseEntity<List<User>> listAllForums() {
//        List<Forum> forums = favoriteService.getAllForums();
//        System.out.println(forums);
//        Result result = new Result();
//        if (forums.isEmpty()) {
//            result.setSuccess(0);
//            return new ResponseEntity(HttpStatus.NO_CONTENT);
//        }
//        result.setSuccess(1);
//        Map data = new HashMap();
//        data.put("forums",forums);
//        result.setData(data);
//        return new ResponseEntity(result, HttpStatus.OK);
//    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public ResponseEntity createFavorite(Favorite favorite,boolean isLike){
        favoriteService.saveOrDelete(favorite,isLike);
        Result result = new Result();
        result.setSuccess(1);
        return new ResponseEntity(result, HttpStatus.OK);
    }
}
