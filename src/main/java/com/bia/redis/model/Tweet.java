package com.bia.redis.model;

public class Tweet {
	
	User user ;
	
	private String login;
	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	private String content;
	private String time = String.valueOf(System.currentTimeMillis());
	
	public Tweet() {
		
	}
	
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	

	
	

}
