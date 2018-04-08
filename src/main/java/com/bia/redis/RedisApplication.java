package com.bia.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.bia.redis.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

//@SpringBootApplication
//@SpringBootApplication(scanBasePackages={"com.bia.redis.controller"})
@SpringBootApplication(scanBasePackages={"com.bia.redis.controller","com.bia.redis", "com.bia.redis.model", "com.bia.repository"})
public class RedisApplication {
	
	
		

	public static void main(String[] args) {
		SpringApplication.run(RedisApplication.class, args);
	}
}
