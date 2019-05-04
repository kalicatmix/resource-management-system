package com.manager.api.jsonmodel;

import java.util.HashMap;

public class Tokens {
 private static HashMap<String,Token>tokens;
 public static Token getToken(String name) {
	return  tokens.get(name);
 }
 public static void addToken(String name,Token token) {
	 if(tokens==null)
	 tokens=new HashMap<String, Token>();
	 tokens.put(name, token);
 }
 public static boolean hasToken(String token) {
	 for(Token temp:tokens.values()) {
		 if(temp.getToken().equals(token)) {
			 return true;
		 }
	 }
	 return false;
 }
}
