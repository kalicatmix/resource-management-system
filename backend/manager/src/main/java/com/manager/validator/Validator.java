package com.manager.validator;

public interface Validator {
 boolean validate(String t);
 boolean validate(int t);
 boolean validate(Object t);
}
