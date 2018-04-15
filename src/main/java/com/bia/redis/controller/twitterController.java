package com.bia.redis.controller;

import java.util.List;
import java.util.UUID;

import javax.ws.rs.Produces;

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

import com.bia.redis.model.Tweet;
import com.bia.redis.model.User;
import com.bia.repository.TwitterRepositoryImpl;
import com.bia.repository.UserRepositoryImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@RestController
@RequestMapping("/twitter")
public class twitterController {
	
	

@Autowired
private UserRepositoryImpl userRepositoryImpl;

@Autowired
private TwitterRepositoryImpl twitterRepositoryImpl;


/* -------------------------------- Connection  ---------------------------------------------------*/

@RequestMapping(value="/signIn"   , method = RequestMethod.POST)
public String signIn (@RequestParam("name") String login, @RequestParam("pass") String pass, Model model ) throws JsonProcessingException {
	
		if (login != null && pass != null) {
			userRepositoryImpl.signIn(login, pass);	
			return " Le user " + login + "s'est logué";
		}else {
			
			model.addAttribute("Mot de passe ou login non renseigné ", Boolean.TRUE);
			return " pwd ou login pas renseigné";
		}
			
}



@RequestMapping(value="/signUp"   , method = RequestMethod.POST)
public String signUp (@RequestParam("name") String login, @RequestParam("pass") String pass, @RequestParam("confirmationMdp") String confirmationMdp, Model model ) throws JsonProcessingException {
	
	User user = new User();
	user.setLogin(login);
	user.setPass(pass);
		
	if (!pass.equals(confirmationMdp)) {
		model.addAttribute("Mot de passe ne sont pas identique ", Boolean.TRUE);
		return "mot de passe pas identique, enregistrement impossible ";
	}	
	
	
	if (!userRepositoryImpl.isExist(login)) {
					
			System.out.println("---- le user exsite pas on peut l'enregistrer");
			userRepositoryImpl.signUp(user);
		}
		
			
	return " Save complete !!!";
			
		
}




/* -------------------------------- Tweet  ---------------------------------------------------*/




@RequestMapping(value="/newTweet"   , method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public String newTweet (@RequestParam("login") String login, @RequestParam("content") String content) throws JsonProcessingException {
	
	User user = new User();
	user.setLogin(login);
	user.setPass("");
	
	Tweet tweet = new Tweet();
	tweet.setContent(content);
	
	return twitterRepositoryImpl.newTweets(user, tweet);
	
				
}


@RequestMapping(value="/showTweets"   , method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_VALUE)
public List<String> newTweet (@RequestParam("login") String login )throws JsonProcessingException {
	
	User user = new User();
	user.setLogin(login);
	user.setPass("");
	return twitterRepositoryImpl.ShowAllTweetOfUser(user);
	
				
}


@RequestMapping(value="/numberOfTweet"   , method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_VALUE)
public  Long  numberOfTweet (@RequestParam("login") String login )throws JsonProcessingException {
	
	User user = new User();
	user.setLogin(login);
	user.setPass("");
	
	return twitterRepositoryImpl.numberOfTweet(user);
					
}


@RequestMapping(value="/showAllTweetofFollowing"   , method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_VALUE)
public  List<String>  showAllTweetofFollowing (@RequestParam("user") String u )throws JsonProcessingException {
	
	User user = new User();
	user.setLogin(u);
	
	
	return twitterRepositoryImpl.showTweetOfPeopleIFollow(user);
					
}




/* -------------------------------- Follow  ---------------------------------------------------*/

@RequestMapping(value="/addFollower"   , method = RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE)
public void addFollower (@RequestParam("login") String login, @RequestParam("follower") String follower )throws JsonProcessingException {
	
	User user = new User();
	user.setLogin(login);
	user.setPass("");
	
	twitterRepositoryImpl.addFollower(user, follower );
					
}


@RequestMapping(value="/addFollowing"   , method = RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE)
public void addFollowing (@RequestParam("login") String login, @RequestParam("idFollower") String follow )throws JsonProcessingException {
	
	User user = new User();
	user.setLogin(login);
	user.setPass("");
	
	twitterRepositoryImpl.addFollowing(user, follow );
					
}


@RequestMapping(value="/showAllMyFollwer"   , method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_VALUE)
public List<String> showAllMyFollwer (@RequestParam("login") String login )throws JsonProcessingException {
	
	User user = new User();
	user.setLogin(login);
	user.setPass("");
	
	return twitterRepositoryImpl.showAllMyFollwer(user);
					
}

@RequestMapping(value="/showAllPeopleFollowBy"   , method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_VALUE)
public  List<String>  showAllPeopleFollowBy (@RequestParam("login") String login )throws JsonProcessingException {
	
	User user = new User();
	user.setLogin(login);
	user.setPass("");
	
	return twitterRepositoryImpl.showAllPeopleFollowBy(user);
					
}

@RequestMapping(value="/numberOfFollowers"   , method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_VALUE)
public  Long  numberOfFollowers (@RequestParam("login") String login )throws JsonProcessingException {
	
	User user = new User();
	user.setLogin(login);
	user.setPass("");
	
	return twitterRepositoryImpl.numberOfFollowers(user);
					
}

@RequestMapping(value="/numberOfPeopleIFollow"   , method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_VALUE)
public  Long  numberOfPeopleIFollow (@RequestParam("login") String login )throws JsonProcessingException {
	
	User user = new User();
	user.setLogin(login);
	user.setPass("");
	
	return twitterRepositoryImpl.numberOfPeopleIFollow(user);
					
}

@RequestMapping(value="/search"   , method = RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE)
public  List<String>  search (@RequestParam("hashtag") String hashtag )throws JsonProcessingException {
	
	Tweet query = new Tweet();
	query.setContent(hashtag);;
	query.getTime();
	
	return twitterRepositoryImpl.search(query);
					
}

















	

}
