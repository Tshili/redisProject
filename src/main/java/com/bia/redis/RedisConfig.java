package com.bia.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RedisConfig {
	

	// Redis connection factory produce connection to a Redis key-value store.
		//En utilisant RedisConnection, nous pouvons stocker et lire des données.
		@Bean
		public JedisConnectionFactory jedisConnectionFactory() {
			
			return new JedisConnectionFactory();
		
		}
		
		
		//A l'aide de jedisConnectionFactory, un RedisTemplate est défini, 
		//qui sera ensuite utilisé pour interroger les données avec un référentiel personnalisé.
		@Bean
		public RedisTemplate<String, String> stringRedisTemplate() {
		   RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
		   redisTemplate.setConnectionFactory(jedisConnectionFactory());
		   redisTemplate.setKeySerializer(new StringRedisSerializer());
		   redisTemplate.setValueSerializer(new StringRedisSerializer());
		   redisTemplate.setDefaultSerializer(new StringRedisSerializer());
		   redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		   redisTemplate.setHashValueSerializer(new StringRedisSerializer());
		   return redisTemplate;
		}
		
		@Bean
		public ObjectMapper ObjectMapper() {
			return new ObjectMapper();
		}
	

}
