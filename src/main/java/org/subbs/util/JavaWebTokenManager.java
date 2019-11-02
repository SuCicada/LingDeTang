package org.subbs.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: peng
 * Date: 10/22/19
 * Time: 5:20 PM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Component
public class JavaWebTokenManager {

    private static Logger log = Logger.getLogger(JavaWebTokenManager.class);

    private static String keySecret = "SuCicada";

    private static final long EXPIREDURATION = 24*60*60*1000L;

    private static SecretKey getKeyInstance() {
//        return MacProvider.generateKey();
        //We will sign our JavaWebTokenManager with our ApiKey secret
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(keySecret);
        SecretKey signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        return signingKey;
    }

    public static String createJavaWebToken(Map<String, Object> claims) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        long expMillis = nowMillis + EXPIREDURATION;
        Date exp = new Date(expMillis);
        Key key = getKeyInstance();
        JwtBuilder jwtBuilder = Jwts.builder()
                .setClaims(claims)             //如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                .signWith(SignatureAlgorithm.HS256, key) //设置签名使用的签名算法和签名使用的秘钥
//                .setId(id)                   //设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
                .setIssuedAt(now)              //iat: jwt的签发时间
                .setExpiration(exp);           //设置过期时间
//                .setSubject(subject);        //sub(Subject)：代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
        return jwtBuilder.compact();
    }


    /**
     * 解密jwt
     * @param jwt
     * @return
     */
    public static Claims verifyJavaWebToken(String jwt) throws Exception{
        try {
            Claims jwtClaims = Jwts.parser()
                            .setSigningKey(getKeyInstance())
                            .parseClaimsJws(jwt).getBody();
            System.out.println(jwtClaims);
            return jwtClaims;
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println(e.getMessage());
            throw new Exception("json web token verify failed");
        }
    }

}