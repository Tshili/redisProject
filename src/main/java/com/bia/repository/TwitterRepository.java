package com.bia.repository;

import java.util.List;

import com.bia.redis.model.Tweet;
import com.bia.redis.model.User;

public interface TwitterRepository {
	
	
	 /* ------------------ Tweet  ---------------*/
	
	 String newTweets(User user, Tweet tweet);
	 List<String>ShowAllTweetOfUser(User user);
	 
	 	 
	 /* ------------------ Follow  ---------------*/
	 
	 void addFollowing(User user, String idFollower );
	 void addFollower(User user, String idFollowing );
	 List <String> showAllPeopleFollowBy(User user);
	 List <String> showAllMyFollwer(User user);
	 long numberOfFollowers(User user);
	 long numberOfPeopleIFollow(User user);
	 long numberOfTweet(User user);
	 

}
