package org.subbs.interceptor;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.subbs.util.JavaWebTokenManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {

//    @Autowired
//    JavaWebTokenManager javaWebTokenManager;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  {
		System.out.println("经过了拦截器");
		System.out.println(request.getRequestURL());

		String url = request.getRequestURL().toString();
		String tokenHeader = request.getHeader("sessionID");
		Claims claims;
		try {
			System.out.println(tokenHeader);
			claims = JavaWebTokenManager.verifyJavaWebToken(tokenHeader);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		System.out.println(claims.getSubject());
		return true;
	}
}
