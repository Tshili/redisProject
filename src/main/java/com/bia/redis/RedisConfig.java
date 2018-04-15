package com.bia.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;


import redis.clients.jedis.Jedis;

@Configuration
public class RedisConfig {
	
	
	
	//Connecting to Redis server on localhost 
   private static  Jedis jedis;
   
	public static Jedis getJedis() {
		return new Jedis("localhost");
	}
	

}
