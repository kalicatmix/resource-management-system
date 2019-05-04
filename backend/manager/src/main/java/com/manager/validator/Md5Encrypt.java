package com.manager.validator;

import java.security.MessageDigest;

public class Md5Encrypt {
private static Md5Encrypt md5Encrypt;
private MessageDigest md5;
private Md5Encrypt() {
}
public static  Md5Encrypt getInstance() {
	synchronized(Md5Encrypt.class){
		if(md5Encrypt==null) {
			md5Encrypt=new Md5Encrypt();
		}
	}
	return md5Encrypt;
}
public String encrypt(String message) throws Exception {
	StringBuffer buffer=new StringBuffer();
	md5=MessageDigest.getInstance("md5");
	md5.reset();
	md5.update(message.getBytes());
	byte[]digested=md5.digest();
	for(byte item:digested) {
		String salt=Integer.toHexString(item&0xff);
		if(salt.length()!=1) {
			buffer.append(salt);
		}else {
			buffer.append("0").append(salt);
		}
	}
	
	return buffer.toString();
	
}
}
