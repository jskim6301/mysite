package com.douzone.mysite.config.web;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.security.AuthInterceptor;
import com.douzone.security.AuthUserHandlerMethodArgumentResolver;
import com.douzone.security.LoginInterceptor;
import com.douzone.security.LogoutInterceptor;

@Configuration
public class SecurityConfig extends WebMvcConfigurerAdapter {
	
	// Argument Resolver
	@Bean
	public HandlerMethodArgumentResolver authUserHandlerMethodArgumentResolver() {
		return new AuthUserHandlerMethodArgumentResolver();
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(authUserHandlerMethodArgumentResolver());		
	}
	
	//interceptors
	@Bean
	public HandlerInterceptorAdapter loginInterceptor() {
		return new LoginInterceptor();
	}
	@Bean
	public LogoutInterceptor logoutInterceptor() {
		return new LogoutInterceptor();
	}
	@Bean
	public HandlerInterceptorAdapter authInterceptor() {
		return new AuthInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry
		.addInterceptor(loginInterceptor())
		.addPathPatterns("/user/auth");
		
		registry.addInterceptor(logoutInterceptor())
		.addPathPatterns("/user/logout");
		
		registry.addInterceptor(authInterceptor())
		.addPathPatterns("/**")
		.excludePathPatterns("/user/auth")
		.excludePathPatterns("/user/logout")
		.excludePathPatterns("/assets/**");
		

	}
	
	
}
