package com.bia.repository;

import java.util.List;

import com.bia.redis.model.Tweet;
import com.bia.redis.model.User;

public interface UserRepository {
	
	// USER
	
	void signUp (User user );
		
	String signIn(String login, String pwd );
	
	boolean isExist(String login);
	
	String getLogin(String login);
	
	
	
	
	
	
	


}
