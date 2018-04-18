package com.bia.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.bia.redis.RedisConfig;
import com.bia.redis.model.Tweet;
import com.bia.redis.model.User;

import redis.clients.jedis.Jedis;



@Repository
public class UserRepositoryImpl implements UserRepository {
	
	
	//Creation d'un user 
	@Override
	public void signUp(User user) {
		
		String idUser = UUID.randomUUID().toString();
		
		
		Map<String, String> userProperties = new HashMap<String, String>();
		 userProperties.put("password", user.getPass());
		 userProperties.put("uid", idUser );
		 
		 
		Jedis jedis = RedisConfig.getJedis();
		 
		// Creation d'un user avec ses propres caracteristiques
		 jedis.hmset(User.USER_KEY + user.getLogin(), userProperties);
		 
		 // Enregistrement de tout les users 
		 jedis.rpush(User.USER_KEY, user.getLogin());
		 		
	}
	
	// Verification si user existe
	@Override
	public boolean isExist(String login) {
		
		Jedis jedis = RedisConfig.getJedis();
		List<String>elements = new ArrayList<>();
		
		//Listes de tout les users
		elements = jedis.lrange(User.USER_KEY,0, -1);
		System.out.println("Liste de tout les utisateurs " );
		System.out.println(elements);
		
		 //Parcours la liste de tout les utilisateurs et 
		 //je consisdere qu'1 utilisateur existe si le login rentré corespond à un element du tableau 
		  for(String user : elements) {
	            if(login.equals(user)){
	            	
	            	System.out.println("L'utilisateur " +  login + "existe deja en base " );
	                return true;
	            }
	        }
				
		return false;
	}
	
	
	
	@Override
	public String getLogin (String login) {
		
		Jedis jedis = RedisConfig.getJedis();
		List<String>elements = new ArrayList<>();
		
		//Listes de tout les users
		elements = jedis.lrange(User.USER_KEY,0, -1);
		System.out.println("Liste de tout les utisateurs " );
		System.out.println(elements);
		
		 //Parcours la liste de tout les utilisateurs et 
		 //je consisdere un utilisateur 
		  for(String user : elements) {
			  
			  System.out.println("Recuperation du user" );
			  System.out.println(user);
			  
			  System.out.println("comparaison du user" );
			  System.out.println(login);
			  
			  
	            if(login.equals(user)){
	            	
	            	System.out.println("L'utilisateur s'appelle  " +  login   );
	                return login;
	            }
	            
	            System.out.println("Je ne rentre pas dans la condition" );
	        }
				
		return login;
	}
	
	

	
	
	

	
	@Override
	public User  signIn(String login, String pwd) {
		
		Jedis jedis = RedisConfig.getJedis();

		String password = jedis.hget(User.USER_KEY + getLogin(login), "password");
		
		System.out.println(" -----     le password du user est " + password);
		
		User u = new User();
		
		  if( password.equals(pwd)) {
			  u.setLogin(login);
	           u.setPass(pwd);
	           
	           System.out.println(" -----     le user est " + u);
	           
	           System.out.println(" -----     le password du  " + u.getPass());
	            jedis.hget(User.USER_KEY + getLogin(login), "uid");
	           
	        }

	       return u;
		
	}






	
	
	
	
	

	
	
	
	
	
	
	
	

}
