package org.subbs.util.token;

/**
 * Created with IntelliJ IDEA.
 * User: peng
 * Date: 10/22/19
 * Time: 8:02 PM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class TokenDTO {
    long expire;
    String token;


    public void setExpire(long l) {
        expire = l;
    }

    public void setToken(String tokenString) {
        token = tokenString;
    }
    public long getExpire() {
        return expire;
    }

    public String getToken() {
        return token;
    }
}
