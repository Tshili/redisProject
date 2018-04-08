package com.bia.redis.model;



public class User  {
	
	//private String id;
	private String name;
	private String pass;
	//private String authKey;
	
public User() {
		
	}
	

	public User(String id , String name, String pass) {
		
		this.name = name;
		this.pass = pass;
		//this.id = id;
	}
	
	
	/*public String getId() {
		return id;
	}*/
	/*public void setId(String id) {
		this.id = id;
	}*/
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	/*public String getAuthKey() {
		return authKey;
	}*/
	/*public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}*/


	@Override
	public String toString() {
		return "User [ name=" + name + ", pass=" + pass   + "]";
	}


	
	
	
	
	
	
	

}
