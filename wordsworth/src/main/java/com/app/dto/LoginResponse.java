package com.app.dto;

import com.app.pojos.Role;

public class LoginResponse {

	private String email;
	private String firstName;
	private Role role;
	private int id;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LoginResponse(String email, String firstName, Role role, int id) {
		super();
		this.email = email;
		this.firstName = firstName;
		this.role = role;
		this.id = id;
	}
	
	
}
