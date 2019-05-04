package com.manager.interceptor;

import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.manager.api.jsonmodel.Tokens;
import com.manager.validator.Md5Encrypt;

public class ApiInterceptor implements HandlerInterceptor {
    private String filterPaths[]= {
    	"/manager/api/user/add"
    };
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Object object=request.getSession().getAttribute("user");
		if(object!=null) {
	     return true;
		}
		
		String path=new URL(request.getRequestURL().toString()).getPath();
		for(String temp:filterPaths) {
			if(temp.equals(path))
			return true;
		}
		
		String token=request.getParameter("token");
		if(Tokens.hasToken(token))
		return true;
		
		return false;
			
	}
 
}
