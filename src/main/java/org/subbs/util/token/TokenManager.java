package org.subbs.util.token;

import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.subbs.entity.User;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: peng
 * Date: 10/22/19
 * Time: 7:59 PM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Component
public class TokenManager {

    private static final Logger logger = LoggerFactory.getLogger(TokenManager.class);

    //token公共池 ，使用线程安全的map
    private static final ConcurrentHashMap<String, TokenDTO> tokenPool = new ConcurrentHashMap<>();


    //过期时长
    private static final long EXPIREDURATION = 24*60*60*1000L;

    /**
     * 生成token 并放到池中
     * @param user
     * @return
     */
    public TokenDTO makeToken(User user) {

        //主要是跟user和当前时间戳生成token
        String tokenString = MD5.generate(user.toString()+System.currentTimeMillis());
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setExpire(System.currentTimeMillis()+EXPIREDURATION);
        tokenDTO.setToken(tokenString);
//        tokenDTO.setUser(user);

        //存放到池中
        tokenPool.put(tokenString,tokenDTO);
        return tokenDTO;
    }

    /**
     * 获取token
     * @param token
     * @return
     */
    public TokenDTO query(String token) {
        return tokenPool.get(token);
    }

    /**
     * 删除token
     * @param token
     * @param tokenDTO
     */
    public void deleteToken(String token, TokenDTO tokenDTO) {
        tokenPool.remove(token);
    }

    /**
     * 定时清理无效token
     */
    @Scheduled(initialDelay = EXPIREDURATION,fixedDelay = EXPIREDURATION )
    private void cleanToken() {
        logger.info("cleanToken","开始clean token");
        tokenPool.forEach((key, value) -> {
            if (value.getExpire() <= System.currentTimeMillis()) {
                tokenPool.remove(key,value);
            }
        });
    }

}