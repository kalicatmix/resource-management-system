package com.manager.validator;

public class WebLoginValidator {
 public static String webLoginValidate(String type) {
	 if(type.equals(WebLoginType.ADMIN))
	 return WebLoginType.ADMIN;
	 else if(type.equals(WebLoginType.USER))
	 return WebLoginType.USER;
	 else 
	 return WebLoginType.UNKNOW;
 }
 public static class WebLoginType{
	 public static String USER="user";
	 public static String ADMIN="admin";
	 public static String UNKNOW="unknow";
 }
}
