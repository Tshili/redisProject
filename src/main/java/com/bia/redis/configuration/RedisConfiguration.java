package com.bia.redis.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.bia.redis.model.User;

@Configuration
public class RedisConfiguration {
	

	// Redis connection factory produce connection to a Redis key-value store.
		//En utilisant RedisConnection, nous pouvons stocker et lire des données.
		/*@Bean
		public JedisConnectionFactory jedisConnectionFactory() {
			
			JedisConnectionFactory jedisConnectionFactory =  new JedisConnectionFactory();
			jedisConnectionFactory.setPort(6379);
			jedisConnectionFactory.setHostName("Localhost");
			return  jedisConnectionFactory;
		
		}*/
		
		
		//A l'aide de jedisConnectionFactory, un RedisTemplate est défini, 
		//qui sera ensuite utilisé pour interroger les données avec un référentiel personnalisé.
		/*@Bean
		public RedisTemplate<String, Object> redisTemplate() {
		   RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		   redisTemplate.setConnectionFactory(jedisConnectionFactory());
		   redisTemplate.setHashValueSerializer(new StringRedisSerializer());
		   redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		   redisTemplate.setKeySerializer(new StringRedisSerializer());
		   redisTemplate.setValueSerializer(new StringRedisSerializer());
		   return redisTemplate;
		}*/

}
