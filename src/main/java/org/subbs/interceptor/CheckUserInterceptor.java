package org.subbs.interceptor;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.subbs.controller.Result;
import org.subbs.util.JavaWebTokenManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CheckUserInterceptor extends HandlerInterceptorAdapter {

//    @Autowired
//    JavaWebTokenManager javaWebTokenManager;

	/**
	 * 检查jwt验证
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public boolean checkJwt(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

	/**
	 * 检查session中的user, 以此判断有没有登录, 用于服务端主动登出
	 * 前后端完全分离的情况下, session是没有用的, 考虑使用redis吧
	 * @return
	 */
	public boolean checkSession(HttpServletRequest request){
		return request.getSession().getAttribute("user")!=null;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
		System.out.println("经过了拦截器");
//		System.out.println(request.getRequestURL());

		boolean a1 = checkJwt(request,response);
//		boolean a2 = checkSession(request);
		return  a1;
	}
}
