package org.subbs.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: peng
 * Date: 10/22/19
 * Time: 6:31 PM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class AuthUtil {
    private static Map<String, Object> getClientLoginInfo(HttpServletRequest request) throws Exception {
        Map<String, Object> r;
        String sessionId = request.getHeader("sessionId");
        if (sessionId != null) {
            r = decodeSession(sessionId);
            return r;
        }
        throw new Exception("session解析错误");
    }

    public static Long getUserId(HttpServletRequest request) throws Exception {
        return  Long.valueOf((Integer)getClientLoginInfo(request).get("userId"));

    }

    /**
     * session解密
     */
    public static Map<String, Object> decodeSession(String sessionId) {
        try {
            return JavaWebTokenManager.verifyJavaWebToken(sessionId);
        } catch (Exception e) {
            System.err.println("");
            return null;
        }
    }
}