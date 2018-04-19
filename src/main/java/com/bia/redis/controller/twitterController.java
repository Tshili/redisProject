package com.bia.redis.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bia.redis.model.Tweet;
import com.bia.redis.model.User;
import com.bia.repository.TwitterRepositoryImpl;
import com.bia.repository.UserRepositoryImpl;

import com.fasterxml.jackson.core.JsonProcessingException;



@CrossOrigin
@RestController
@RequestMapping("/twitter")

public class twitterController {
	
	

@Autowired
private UserRepositoryImpl userRepositoryImpl;

@Autowired
private TwitterRepositoryImpl twitterRepositoryImpl;


/* -------------------------------- Connection  ---------------------------------------------------*/

@CrossOrigin
@RequestMapping(value="/signIn"   , method = RequestMethod.POST)
public User signIn (@RequestBody User u ) throws JsonProcessingException {
	
	
	

		if (u.getLogin() != null && u.getPass() != null) {
			return userRepositoryImpl.signIn(u.getLogin(), u.getPass());	
			//return " Le user " + login + "s'est logué";
		}else {
			
			//model.addAttribute("Mot de passe ou login non renseigné ", Boolean.TRUE);
			
		}
		return u;
			
}


@CrossOrigin
@RequestMapping(value="/signUp"   , method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
/*public String signUp (@RequestParam("name") String login, @RequestParam("pass") String pass, @RequestParam("confirmationMdp") String confirmationMdp, Model model ) throws JsonProcessingException {*/
	public User signUp (@RequestBody User u)throws JsonProcessingException {	
	User user = new User();
	user.setLogin(u.getLogin());
	user.setPass(u.getPass());
	
	System.out.println("---- recupere signuppp ");
	System.out.println(u.getLogin());
	System.out.println(u.getPass());
	System.out.println(u.getpConfirmationPass());
		
	if (!u.getPass().equals(u.getpConfirmationPass())) {
		//model.addAttribute("Mot de passe ne sont pas identique ", Boolean.TRUE);
		
		System.out.println("---- existe ");
		
		
	}	
	
	
	if (!userRepositoryImpl.isExist(u.getLogin())) {
					
			System.out.println("---- le user exsite pas on peut l'enregistrer");
			userRepositoryImpl.signUp(user);
		}
		 
	return user;
			
		
}




/* -------------------------------- Tweet  ---------------------------------------------------*/




@RequestMapping(value="/newTweet"   , method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)

//public String newTweet (@RequestParam("login") String login, @RequestParam("content") String content) throws JsonProcessingException {
	public Tweet newTweet ( @RequestBody Tweet tweet )throws JsonProcessingException {
	
	User user = new User();
	user.setLogin(tweet.getLogin());
	user.setPass("");
	
	Tweet t = new Tweet();
	t.setContent(tweet.getContent());
	
	twitterRepositoryImpl.newTweets(user, tweet);
	return tweet;
	
				
}


@RequestMapping(value="/showTweets"   , method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_VALUE)
public List<String> showTweets (@RequestParam("login") String login )throws JsonProcessingException {
		
	User user = new User();
	user.setLogin(login);
	user.setPass("");
	return twitterRepositoryImpl.ShowAllTweetOfUser(user);
					
}

@RequestMapping(value="/showLastTweets"   , method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_VALUE)
public List<String> showLastTweets (@RequestParam("login") String login )throws JsonProcessingException {
		
	User user = new User();
	user.setLogin(login);
	user.setPass("");
	return twitterRepositoryImpl.ShowLastTweetOfUser(user);
					
}


/*@RequestMapping(value="/showTweets/{login}"   , method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_VALUE)
public List<String> newTweet (@PathVariable String login )throws JsonProcessingException {
	
	
	User user = new User();
	user.setLogin(login);
	user.setPass("");
	return twitterRepositoryImpl.ShowAllTweetOfUser(user);
	
				
}*/


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
//public void addFollowing (@RequestParam("login") String login, @RequestParam("idFollower") String follow )throws JsonProcessingException {
	public void addFollowing (@RequestBody User user )throws JsonProcessingException {
	
	//User user = new User();
	//user.setLogin(login);
	//user.setPass("");
	
	User u = new User();
	u.setLogin(user.getLogin());
	u.setPass("");
	u.setPeopleFollowByUser(user.getPeopleFollowByUser());
	u.setFollowers(user.getFollowers());
	
	//twitterRepositoryImpl.addFollowing(user, follow );
	
	twitterRepositoryImpl.addFollowing(u);
	
	
					
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

//public  List<String>  search (@RequestParam("hashtag") String hashtag )throws JsonProcessingException {
	public  List<String>  search ( @RequestBody Tweet t)throws JsonProcessingException {
	
	Tweet query = new Tweet();
	query.setContent(t.getContent());
	query.getTime();
	
	return twitterRepositoryImpl.search(query);
					
}




@RequestMapping(value="/suggestions"   , method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_VALUE)
public  List<String>  suggestions ()throws JsonProcessingException {
	

	return twitterRepositoryImpl.suggestions();
					
}

















	

}
