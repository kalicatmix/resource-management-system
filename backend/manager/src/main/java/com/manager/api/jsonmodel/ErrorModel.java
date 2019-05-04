package com.manager.api.jsonmodel;

public class ErrorModel {
private static MessageModel<Object> errorModel;
public static MessageModel<Object> getDefault(){
    if(errorModel==null) {
    	errorModel=new MessageModel<Object>();
    	errorModel.setMessage(MessageCode.ERROR);
    	errorModel.setT(null);
    }
	return errorModel;
}
public static  MessageModel<Object> create(int messageCode,Object obj){
	MessageModel<Object> messageModel=new MessageModel<Object>();
	messageModel.setMessage(messageCode);
	messageModel.setT(obj);
	return messageModel;
}
}
