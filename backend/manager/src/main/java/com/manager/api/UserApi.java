package com.manager.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.manager.api.jsonmodel.ErrorModel;
import com.manager.api.jsonmodel.SuccessModel;
import com.manager.model.User;
import com.manager.service.UserService;
import com.manager.validator.Md5Encrypt;

@RestController
@RequestMapping("api/user")
public class UserApi {
  @Autowired
  private UserService service;
  
  @PostMapping("add")
  public Object add(@RequestParam("user")String user,@RequestParam("pwd")String pwd,@RequestParam("changeword")String changeword) {
	  User userT;
	try {
		userT = new User(user,pwd,Integer.valueOf(changeword),Md5Encrypt.getInstance().encrypt(pwd));
		if(service.add(userT)) {
			  return SuccessModel.getDefault();
		  }
	} catch (Exception e) {
		e.printStackTrace();
	}
	  return  ErrorModel.getDefault();
  }
  
  @PostMapping("del")
  public Object  del(@RequestParam("user")String user,@RequestParam("pwd")String pwd,@RequestParam("changeword")String changeword)
  {   User userT;
	  try {
			userT = new User(user,pwd,Integer.valueOf(changeword),Md5Encrypt.getInstance().encrypt(pwd));
			if(service.delete(userT)) {
				  return SuccessModel.getDefault();
			  }
		} catch (Exception e) {
			e.printStackTrace();
		}
		  return  ErrorModel.getDefault();
  }
  @PostMapping("change")
  public Object change(@RequestParam("user")String user,@RequestParam("pwd")String pwd,@RequestParam("newpwd")String newpwd) {
	  User userT=service.getByUser(user, pwd);
	  if(userT!=null) {
		  try {
				userT.setPwd(newpwd);
				userT.setPwdmd5(Md5Encrypt.getInstance().encrypt(newpwd));
				if(service.save(userT)) {
					  return SuccessModel.getDefault();
				  }
			} catch (Exception e) {
				e.printStackTrace();
			}
	  }
	  return  ErrorModel.getDefault();
  }
  
  @PostMapping("query")
  public User query(@RequestParam("user")String user,@RequestParam("pwd")String pwd) {
	  User userT=service.getByUser(user,pwd);
      return userT;
  }
}
