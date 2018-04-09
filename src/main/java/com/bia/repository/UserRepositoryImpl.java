package com.bia.repository;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import com.bia.redis.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class UserRepositoryImpl implements UserRepository {
	
	private RedisTemplate<String, String> redisTemplate;
	
	private HashOperations hashOperations;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private final StringRedisTemplate template = new StringRedisTemplate();
	
	
	@Override
	public Boolean alreadyExist(String name) {
		return template.hasKey(name);
		
	}


	@Override
	public void addUser(User user) throws JsonProcessingException {
		// TODO Auto-generated method stub
		
	}



	
	

	



	

}
