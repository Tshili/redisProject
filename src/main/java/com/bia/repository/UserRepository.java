package com.bia.repository;


import com.bia.redis.model.User;

public interface UserRepository {
	
	// USER
	
	void signUp (User user );
		
	User signIn(String login, String pwd );
	
	boolean isExist(String login);
	
	String getLogin(String login);
	
	
	
	
	
	
	


}
