package com.bia.redis.controller;

import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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


private ValueOperations <String, String> valueOperations;



private HashOperations hashOperations;



// Inscription d'un utilisateur 
@RequestMapping(value="/signUp"   , method = RequestMethod.POST)
public String signUp (@RequestParam("name") String name, @RequestParam("pass") String pass, @RequestParam("confirmationMdp") String confirmationMdp, Model model ) throws JsonProcessingException {
	
			User user = new User();
				user.setName(name);
					user.setPass(pass);
					
					if (!pass.equals(confirmationMdp)) {
						model.addAttribute("Mot de passe ne sont pas identique ", Boolean.TRUE);
						return "mot de passe pas identique, enregistrement impossible ";
					}else {
						
						String uid = String.valueOf(UUID.randomUUID());
						 hashOperations = redisTemplate.opsForHash();
						
						 hashOperations.put("USER", uid, objectMapper.writeValueAsString(user));
						 
						 return " Save complete !!!";
						
					}
		
}



// Connection
@RequestMapping(value="/signIn"   , method = RequestMethod.POST)
public String signIn(  @RequestParam("name") String name, @RequestParam("pass") String pass, Model model ) throws JsonProcessingException {
	
	
	System.out.println("------    le name  --------");
	System.out.println(name);
	
	
	
	 valueOperations = redisTemplate.opsForValue();
	String  myName = valueOperations.get(name);
	
	
	System.out.println("------    le myName --------");
	System.out.println(valueOperations);
	System.out.println(myName);
	
	return null;
	
}









	

}
