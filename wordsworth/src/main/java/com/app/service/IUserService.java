package com.app.service;

import com.app.pojos.User;

public interface IUserService {
	User loginUser(String email,String password);
	
	String registerUser(User user);//return success or "user by this email already exists please login"
	
	String changePassword(Integer userId,String oldPassword,String newPassword);
	
	String updateMembership(Integer userId,Integer membershipId); //find membership by membership type then set the membership in user
	
}
