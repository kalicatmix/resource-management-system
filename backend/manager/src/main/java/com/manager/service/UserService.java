package com.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manager.mapper.UserMapper;
import com.manager.model.User;
import com.manager.validator.UserValidator;

@Service
@Transactional
public class UserService implements com.manager.service.Service<User>{
    @Autowired
    UserMapper userMapper;
    
    public boolean validate(User  user) {
    	return UserValidator.validate(user);
    }
	public boolean delete(User t) {
		if(validate(t)) {
	    userMapper.delete(t.getUser(),t.getPwd(),t.getChangeword());
		return true;
		}
		return false;
	}

	public boolean save(User t) {
		if(validate(t)) {
		userMapper.save(t.getUser(),t.getPwd(), t.getChangeword(),t.getPwdmd5());
		return true;
		}
		return false;
	}

	public User getByUser(String user,String pwd) {
		return userMapper.getByName(user,pwd);
	}
	public boolean add(User t) {
		if(validate(t)) {
			userMapper.insert(t.getUser(),t.getPwd(), t.getChangeword(),t.getPwdmd5());
			return true;
		}
		return false;
	}

}
