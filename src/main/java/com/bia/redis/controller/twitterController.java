package com.bia.redis.controller;

import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bia.redis.model.User;
import com.bia.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/twitter")
public class twitterController {
	
	
	
	
@Autowired
private RedisTemplate<String , String >redisTemplate;

@Autowired
private ObjectMapper objectMapper;

private UserRepository UserRepository;



// Inscription d'un utilisateur 

@RequestMapping(value="/add"   , method = RequestMethod.POST)
public String signUp (@RequestBody User user, Model model ) throws JsonProcessingException {
	
	// Est ce que l'utilisateur existe deja ? 
	if (UserRepository.alreadyExist(objectMapper.writeValueAsString(user.getName()))) {
		model.addAttribute("L'utilisateur existe déjà!", Boolean.TRUE);
		
		return " + signin";	
	}else {
		
		System.out.println("L'utilisateur n'existe pas dans Redis ");
		
		
		String uid = String.valueOf(UUID.randomUUID());
		redisTemplate.opsForValue().set(uid, objectMapper.writeValueAsString(user));
	}
	
	
	
	
	return " Save complete !!!";
}





	

	
	
	

}
