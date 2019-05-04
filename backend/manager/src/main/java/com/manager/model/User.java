package com.manager.model;

public class User{
private String user;
private String pwd;
private int changeword;
private String pwdmd5;
public User() {
	
}
public User(String user,String pwd,int changeword,String pwdmd5) {
	this.user=user;
	this.pwd=pwd;
	this.changeword=changeword;
	this.pwdmd5=pwdmd5;
}
public String getPwdmd5() {
	return pwdmd5;
}
public void setPwdmd5(String pwdmd5) {
	this.pwdmd5 = pwdmd5;
}
public String getUser() {
	return user;
}
public int getChangeword() {
	return changeword;
}
public void setChangeword(int changeword) {
	this.changeword = changeword;
}
public void setUser(String user) {
	this.user = user;
}
public String getPwd() {
	return pwd;
}
public void setPwd(String pwd) {
	this.pwd = pwd;
}

}
