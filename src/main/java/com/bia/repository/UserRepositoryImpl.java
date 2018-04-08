package com.bia.repository;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import com.bia.redis.model.User;

@Repository
public class UserRepositoryImpl implements UserRepository {
	
	private RedisTemplate<String, String> redisTemplate;
	
	private final StringRedisTemplate template = new StringRedisTemplate();
	
	
	@Override
	public Boolean alreadyExist(String name) {
		return template.hasKey(name);
		
	}
	
	
	
	
/*	@Override
	public void addUser(User user) {
		hashOperations.put("USER", user.getId(), user);
		
	}*/


	



	

}
