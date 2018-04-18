package com.bia.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;


import redis.clients.jedis.Jedis;

@Configuration
public class RedisConfig implements WebMvcConfigurer	 {
	
	
	
	//Connecting to Redis server on localhost 
   private static  Jedis jedis;
   
	public static Jedis getJedis() {
		return new Jedis("localhost");
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
	  registry.addMapping("/info/**")
	   	  .allowedOrigins("http://localhost:4200")
		  .allowedMethods("POST", "GET",  "PUT", "OPTIONS", "DELETE")
		  .allowedHeaders("X-Auth-Token", "Content-Type")
		  .exposedHeaders("custom-header1", "custom-header2")
		  .allowCredentials(false)
		  .maxAge(4800);
	}
	
	   @Bean
	    public WebMvcConfigurer corsConfigurer() {
	        return new WebMvcConfigurerAdapter() {
	            @Override
	            public void addCorsMappings(CorsRegistry registry) {
	                registry.addMapping("/**");
	            }
	        };
	    }
	
	
	
	
	
	
	

}
