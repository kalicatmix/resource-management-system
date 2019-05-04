package com.manager.api.jsonmodel;

public class MessageModel <T>{
private int message;
private T t;
public MessageModel() {
	
}
public MessageModel(int message,T t) {
	this.message=message;
	this.t=t;
}
public int getMessage() {
	return message;
}
public void setMessage(int message) {
	this.message = message;
}
public T getT() {
	return t;
}
public void setT(T t) {
	this.t = t;
}

}
