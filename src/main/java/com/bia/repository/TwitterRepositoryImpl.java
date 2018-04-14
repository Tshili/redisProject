package com.bia.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bia.redis.RedisConfig;
import com.bia.redis.model.Tweet;
import com.bia.redis.model.User;

import redis.clients.jedis.Jedis;

@Repository
public class TwitterRepositoryImpl implements TwitterRepository {
	
	
	
	/* ------------------ Tweet  ---------------*/
	
	
	@Override
	public String newTweets(User user, Tweet tweet) {
		
		Jedis jedis = RedisConfig.getJedis();
		
		jedis.lpush(user.getLogin()+"Tweet:", tweet.getContent());
		jedis.lpush("ShowTweetOf:"+user.getLogin() , tweet.getContent());
		
		List<String> AllFollowers = new ArrayList<>();
		
		String key = "FollowersOf:"+ user.getLogin();
				
		AllFollowers = jedis.lrange(key, 0, -1);
		
		for (String follower : AllFollowers) {
			jedis.lpush("ShowTweetToFollower:"+ follower , tweet.getContent());
		}
		
		return null;
	}
	
	
	
	@Override
	public long numberOfTweet(User user) {
		
		Jedis jedis = RedisConfig.getJedis();
		String key = "ShowTweetOf:"+user.getLogin() ;
				
		return	jedis.llen(key);
	}
	
	

	@Override
	public List<String> ShowAllTweetOfUser(User user) {
			
		Jedis jedis = RedisConfig.getJedis();
		
		String key = "ShowTweetOf:"+user.getLogin();
		
		return jedis.lrange(key, 0, -1);
	}

	
	
	/* ----------------------------- Follow  -----------------------------------*/


	@Override
	public void addFollowing(User user, String follow) {
		
		Jedis jedis = RedisConfig.getJedis();	
		String key = user.getLogin()+"Follow";	
		jedis.lpush(key, follow);
				
	}
	
	
	@Override
	public void addFollower(User user, String idFollower) {
		
		Jedis jedis = RedisConfig.getJedis();	
		String key = "FollowersOf:"+ user.getLogin();
		
		jedis.lpush(key, idFollower);		
	}
	
	
	
	// List my following	
	@Override
	public List <String> showAllPeopleFollowBy(User user){
			
		List<String> AllPeopleIFolloww = new ArrayList<>();
		
		Jedis jedis = RedisConfig.getJedis();
		String key = user.getLogin()+"Follow" ;
				
		AllPeopleIFolloww = jedis.lrange(key, 0, -1);
		
		return AllPeopleIFolloww;
		
	}
	
	
	// List my followers
	
	@Override
	public List <String> showAllMyFollwer(User user){
			
		List<String> showAllMyFollwer = new ArrayList<>();
		
		Jedis jedis = RedisConfig.getJedis();
		String key = "FollowersOf:"+ user.getLogin() ;
				
		showAllMyFollwer = jedis.lrange(key, 0, -1);
		
		return showAllMyFollwer;
		
	}



	// Display my number of followers
	@Override
	public long numberOfFollowers(User user) {
			
		Jedis jedis = RedisConfig.getJedis();
		String key = "FollowersOf:"+ user.getLogin() ;
				
		return	jedis.llen(key);
		
	}



	@Override
	public long numberOfPeopleIFollow(User user) {
		
		Jedis jedis = RedisConfig.getJedis();
		String key = user.getLogin()+"Follow" ;
				
		return	jedis.llen(key);
	}



	
	
	
	
	
	
	
	
	
	
	

}
