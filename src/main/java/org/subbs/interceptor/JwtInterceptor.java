package org.subbs.interceptor;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.subbs.controller.Result;
import org.subbs.util.JavaWebTokenManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {

//    @Autowired
//    JavaWebTokenManager javaWebTokenManager;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
		System.out.println("经过了拦截器");
//		System.out.println(request.getRequestURL());

		Result result = new Result();
		Claims claims = null;

		if (request.getHeader(HttpHeaders.ORIGIN) != null) {
			String url = request.getRequestURL().toString();
			String tokenHeader = request.getHeader("sessionID");
			try {
				System.out.println(tokenHeader);
				claims = JavaWebTokenManager.verifyJavaWebToken(tokenHeader);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				result.setSuccess(0);
				result.setMsg("身份验证失败");
				String json = JSONObject.toJSONString(result);
				request.setCharacterEncoding("UTF-8");
				response.setContentType("application/json;charset=utf-8");
				response.getWriter().write(json);
				return false;
			}
		}
		System.out.println(claims);
		request.setAttribute("requestUser",claims);
		return true;
	}
}
