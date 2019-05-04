package com.manager.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.manager.api.jsonmodel.ErrorModel;
import com.manager.api.jsonmodel.SuccessModel;
import com.manager.model.Admin;
import com.manager.service.AdminService;
import com.manager.validator.Md5Encrypt;

@RestController
@RequestMapping("api/admin")
public class AdminApi {
	  @Autowired
	  private AdminService service;
	  
	  @PostMapping("add")
	  public Object add(@RequestParam("user")String user,@RequestParam("pwd")String pwd,@RequestParam("changeword")String changeword) {
		  Admin adminT;
		try {
			adminT = new Admin(user,pwd,Integer.valueOf(changeword),Md5Encrypt.getInstance().encrypt(pwd));
			if(service.add(adminT)) {
				  return SuccessModel.getDefault();
			  }
		} catch (Exception e) {
			e.printStackTrace();
		}
		  return  ErrorModel.getDefault();
	  }
	  
	  @PostMapping("del")
	  public Object  del(@RequestParam("user")String user,@RequestParam("pwd")String pwd,@RequestParam("changeword")String changeword)
	  {   Admin adminT;
		  try {
				adminT = new Admin(user,pwd,Integer.valueOf(changeword),Md5Encrypt.getInstance().encrypt(pwd));
				if(service.delete(adminT)) {
					  return SuccessModel.getDefault();
				  }
			} catch (Exception e) {
				e.printStackTrace();
			}
			  return  ErrorModel.getDefault();
	  }
	  @PostMapping("change")
	  public Object change(@RequestParam("user")String user,@RequestParam("pwd")String pwd,@RequestParam("newpwd")String newpwd) {
		 Admin adminT=service.getByUser(user, pwd);
		  if(adminT!=null) {
			  try {
					adminT.setPwd(newpwd);
					adminT.setPwdmd5(Md5Encrypt.getInstance().encrypt(newpwd));
					if(service.save(adminT)) {
						  return SuccessModel.getDefault();
					  }
				} catch (Exception e) {
					e.printStackTrace();
				}
		  }
		  return  ErrorModel.getDefault();
	  }
	  
	  @PostMapping("query")
	  public Admin query(@RequestParam("user")String user,@RequestParam("pwd")String pwd) {
		  Admin adminT=service.getByUser(user,pwd);
	      return adminT;
	  }
}
