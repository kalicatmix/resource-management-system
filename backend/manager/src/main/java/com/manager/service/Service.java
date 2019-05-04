package com.manager.service;

public interface Service<T>{
public boolean delete(T t);
public boolean save(T t);
public boolean add(T t);
public T getByUser(String user,String pwd);
public boolean validate(T t);
}
