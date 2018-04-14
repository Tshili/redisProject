package com.bia.redis.model;



public class User  {
	

	public final static String USER_KEY = "USER:";
	
	private String login;
	private String pass;
	
	
		public User() {
		
	}
	

	public User(String id , String name, String pass) {
		
		this.login = name;
		this.pass = pass;
		
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String name) {
		this.login = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
	
	
	
	
	
	

}
