package org.subbs.interceptor;

import org.omg.PortableInterceptor.Interceptor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: peng
 * Date: 10/29/19
 * Time: 11:05 PM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Component
public class CorsInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String[] allowDomains = {
                "http://localhost",
                "http://sxuldt.gitee.io",
                "http://lan.pc"};
        Set allowOrigins = new HashSet(Arrays.asList(allowDomains));
        String originHeads = request.getHeader("Origin");
        System.out.println(request.getRequestURL());
        System.out.println(originHeads);
        if (request.getHeader(HttpHeaders.ORIGIN) != null) {
            if(allowOrigins.contains(originHeads)){
                //设置允许跨域的配置
                // 这里填写你允许进行跨域的主机ip（正式上线时可以动态配置具体允许的域名和IP）
                response.setHeader("Access-Control-Allow-Origin", originHeads);
            }
//            response.addHeader("Access-Control-Allow-Credentials", "true");
            response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT, HEAD");
            String accessHeaders = request.getHeader(HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS);
            String allowHeaders = "Content-Type";
            if(accessHeaders != null){
                allowHeaders = allowHeaders + "," + accessHeaders;
            }
            response.addHeader("Access-Control-Allow-Headers", allowHeaders);
            //            response.setHeader("Access-Control-Allow-Headers", "Authentication,Origin, X-Requested-With, Content-Type, Accept");
            response.addHeader("Access-Control-Max-Age", "3600");
        }
        return true;
    }
}
