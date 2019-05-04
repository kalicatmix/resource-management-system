package com.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manager.mapper.AdminMapper;
import com.manager.model.Admin;
import com.manager.validator.AdminValidator;

@Service
@Transactional
public class AdminService implements com.manager.service.Service<Admin>{
  @Autowired
  private AdminMapper adminMapper;

public Admin getByUser(String user,String pwd) {
	return adminMapper.getByName(user,pwd);
}

public boolean delete(Admin t) {
	if(validate(t)) {
		adminMapper.delete(t.getUser(),t.getPwd());
		return true;
	}
	return false;
}

public boolean save(Admin t) {
	if(validate(t)) {
		adminMapper.save(t.getUser(),t.getPwd(), t.getChangeword(),t.getPwdmd5());
		return true;
	}
	return false;
}

public boolean validate(Admin t) {
	return AdminValidator.validate(t);
}

public boolean add(Admin t) {
	if(validate(t)) {
		 adminMapper.insert(t.getUser(),t.getPwd(), t.getChangeword(),t.getPwdmd5());
		return true;
	}
	return false;
}
 
}
