package com.cognetyx.util;

// Simple POJO for holding information about our user
public class User {

	private final String name;
	private final String id;
	private final boolean admin;
	
	public User(String name, String id, boolean admin)
	{
		this.name = name;
		this.id = id;
		this.admin = admin;		
	}

	public String getName() {
		return name;
	}
	public String getId() {
		return id;
	}
	public boolean isAdmin() {
		return admin;
	}
}
