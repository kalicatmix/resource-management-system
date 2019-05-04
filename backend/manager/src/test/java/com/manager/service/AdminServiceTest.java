package com.manager.service;

import org.apache.commons.codec.digest.Md5Crypt;
import org.junit.Test;

import com.manager.ApplicationContextRunner;
import com.manager.model.Admin;
import com.manager.validator.Md5Encrypt;

public class AdminServiceTest extends ApplicationContextRunner{
	AdminService service=this.context.getBean(AdminService.class);
@Test
public  void testGetByName() {
	System.out.println(service.getByUser("manager","manager").getChangeword());
}
@Test
public void testAdd() throws Exception {
	Admin admin=new Admin();
	admin.setUser("wonder");
	admin.setChangeword(111111);
	admin.setPwd("wonder");
	admin.setPwdmd5(Md5Encrypt.getInstance().encrypt("wonder"));
	service.add(admin);
}
}
