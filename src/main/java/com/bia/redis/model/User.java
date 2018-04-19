package com.bia.redis.model;



public class User  {
	

	public final static String USER_KEY = "USER:";
	
	
	private String userID;
	public String getUserID() {
		return userID;
	}


	public void setUserID(String userID) {
		this.userID = userID;
	}
	private String login;
	private String pass;
	private String pConfirmationPass;
	private String peopleFollowByUser;
	private String followers;
	
	
	
	
	
	
	
		public String getFollowers() {
		return followers;
	}


	public void setFollowers(String followers) {
		this.followers = followers;
	}


		public String getPeopleFollowByUser() {
		return peopleFollowByUser;
	}


	public void setPeopleFollowByUser(String peopleFollowByUser) {
		this.peopleFollowByUser = peopleFollowByUser;
	}


		public String getpConfirmationPass() {
		return pConfirmationPass;
	}


	public void setpConfirmationPass(String pConfirmationPass) {
		this.pConfirmationPass = pConfirmationPass;
	}


		public User() {
		
	}
	

	public User(String id , String name, String pass) {
		
		this.login = name;
		this.pass = pass;
		
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String name) {
		this.login = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
	
	
	
	
	
	

}
