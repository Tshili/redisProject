package com.bia.repository;

import com.bia.redis.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface UserRepository {
	
	void addUser(User user) throws JsonProcessingException;
	
	//User findbyId(String id);
	
	Boolean alreadyExist (String name);
	

}
