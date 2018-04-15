package com.bia.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication(scanBasePackages={"com.bia.redis.controller","com.bia.redis", "com.bia.redis.model", "com.bia.repository"})
public class RedisApplication {
	
	
		

	public static void main(String[] args) {
		SpringApplication.run(RedisApplication.class, args);
	}
}
