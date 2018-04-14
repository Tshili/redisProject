package com.bia.redis.model;

public class Tweet {
	
	
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
	

	
	

}
