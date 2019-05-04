package com.manager.config;

import org.springframework.web.servlet.view.InternalResourceViewResolver;

public class PageResolver extends InternalResourceViewResolver{ 
 public PageResolver() {
	this.setContentType("text/html;charset=utf-8");
	this.setPrefix("WEB-INF/pages/");
	this.setSuffix(".jsp");
}  
 
}
