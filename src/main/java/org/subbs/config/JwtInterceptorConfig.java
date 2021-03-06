package org.subbs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.subbs.interceptor.CheckUserInterceptor;

/**
 * Created with IntelliJ IDEA.
 * User: peng
 * Date: 10/23/19
 * Time: 4:41 PM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
//@Configuration
//@EnableWebMvc
@ComponentScan(basePackages = "org.subbs.interceptor")
public class JwtInterceptorConfig extends WebMvcConfigurationSupport {

    @Autowired
    private CheckUserInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("");
        super.addInterceptors(registry);
    }
}