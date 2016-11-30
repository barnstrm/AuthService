package com.cognetyx.AuthorizeService.entity;

public class User {

	private String username;
	private String password;
	private String email;
	private Role role;
	
	public User(String uName, String pWord, String eMail, Role role)
	{
		this.username = uName;
		this.password = pWord;
		this.email = eMail;
		this.role = role;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
}
