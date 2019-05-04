package com.manager.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.SessionScope;

import com.manager.api.jsonmodel.ErrorModel;
import com.manager.api.jsonmodel.MessageCode;
import com.manager.api.jsonmodel.SuccessModel;
import com.manager.api.jsonmodel.Token;
import com.manager.api.jsonmodel.Tokens;
import com.manager.mapper.ItemMapper;
import com.manager.model.Admin;
import com.manager.model.User;
import com.manager.service.AdminService;
import com.manager.service.ItemService;
import com.manager.service.Service;
import com.manager.service.UserService;
import com.manager.validator.Md5Encrypt;
import com.manager.validator.WebLoginValidator;
import com.manager.validator.WebLoginValidator.WebLoginType;

@Controller
@RequestMapping("web")
@SessionScope
public class WebController {
@Autowired 
UserService userService;
@Autowired
AdminService adminService;
@Autowired
ItemService itemService;
@RequestMapping()
public String index(HttpServletRequest request,HttpServletResponse res) throws IOException {
	Object type=request.getSession().getAttribute("type");
	if(type!=null) {
	request.setAttribute("items",itemService.getAllItems());
	String page=type.equals(WebLoginType.ADMIN)?"manager":"user";
	return page;
	}
	return "index";
}

@PostMapping("login")
@ResponseBody
public Object login(HttpServletRequest request,@RequestParam("user")String user,@RequestParam("pwd")String pwd,@RequestParam("type")String type) throws Exception{
	HttpSession session=request.getSession();
	if(session.getAttribute("user")!=null)
	return SuccessModel.create(MessageCode.WEBLOGIN_ISLOGIN,null);
	
	String loginType=WebLoginValidator.webLoginValidate(type);
  	
	if(!loginType.equals(WebLoginType.UNKNOW)) {
  		boolean verified=false;
  		Token token=new Token(user+Md5Encrypt.getInstance().encrypt(user));
  		if(loginType.equals(WebLoginType.ADMIN)) {
  			Admin adminT=adminService.getByUser(user, pwd);
  			verified=adminT!=null?true:verified;
  			if(verified) {
  			session.setAttribute("user",adminT);
  			session.setAttribute("type",WebLoginType.ADMIN);
  			Tokens.addToken(user, token);
  			}
  			}else {
  			User userT=userService.getByUser(user, pwd);
  			verified=userT!=null?true:verified;
  			if(verified) {
  			session.setAttribute("user",userT);
  			session.setAttribute("type",WebLoginType.USER);
  			session.setMaxInactiveInterval(3600*60);
  			Tokens.addToken(user,token);
  			}
  			}
  		if(verified)
  		return SuccessModel.create(MessageCode.WEBLOGIN_SUCESSS, token);
  	}
	
  	return ErrorModel.create(MessageCode.WEBLOGIN_ERROR,null);
}

@PostMapping("password/change")
@ResponseBody
public Object change(HttpServletRequest request,@RequestParam("pwd")String pwd,@RequestParam("changeword")String changeword,@RequestParam("newpwd")String newpwd){
	HttpSession session=request.getSession();
	Object type=session.getAttribute("type");
	try {
		if(!type.toString().equals(WebLoginType.ADMIN)) {
			User user=(User) session.getAttribute("user");
			boolean check=user.getChangeword()==Integer.valueOf(changeword)&&user.getPwd().equals(pwd);
		    if(check) {
		    	user.setPwd(newpwd);
			    user.setPwdmd5(Md5Encrypt.getInstance().encrypt(newpwd));
				if(userService.save(user)) {
			    	return SuccessModel.create(MessageCode.WEBLOGIN_SUCESSS,null);
			    }
		    }
		}else {
			
				Admin admin=(Admin) session.getAttribute("user");
				boolean check=admin.getChangeword()==Integer.valueOf(changeword)&&admin.getPwd().equals(pwd);
			    if(check) {
			    	admin.setPwd(newpwd);
				    admin.setPwdmd5(Md5Encrypt.getInstance().encrypt(newpwd));
					if(adminService.save(admin)) {
				    	return SuccessModel.create(MessageCode.WEBLOGIN_SUCESSS,null);
				    
			    }
		}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	
	return ErrorModel.create(MessageCode.WEBLOGIN_ERROR,null);
}
}
