package com.manager.validator;

import com.manager.model.User;

public class UserValidator{
	private static InnerValidator validator;
	public static boolean validate(User user) {
		if(validator==null) {
			validator=new InnerValidator();
		}
		return true;
	}
    private static class InnerValidator implements Validator{

	public boolean validate(String t) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean validate(int t) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean validate(Object t) {
		// TODO Auto-generated method stub
		return false;
	}
	   
   }

}
