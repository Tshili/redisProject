package com.bia.repository;

import com.bia.redis.model.User;

public interface UserRepository {
	
	//void addUser(User user);
	
	//User findbyId(String id);
	
	Boolean alreadyExist (String name);
	

}
