package com.manager.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.manager.ApplicationContextRunner;
import com.manager.model.User;

public class UserServiceTest extends ApplicationContextRunner {
 
	@Test
	public void testSave() {
	 UserService service=this.context.getBean(UserService.class);
     User user=service.getByUser("kalicat","12345");
     user.setPwd("12345");
	 service.save(user);
	 user=service.getByUser("kalicat","12345");
	 if(!"12345".equals(user.getPwd()))
	 assertEquals(0, 1);
	}
}
