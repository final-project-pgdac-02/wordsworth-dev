package com.app.service;

import java.util.List;

import com.app.dto.LoginResponse;
import com.app.dto.UserCartDto;
import com.app.pojos.User;

public interface IUserService {
	
	User getUserByUserId(Integer userId);
	
	LoginResponse loginUser(String email,String password);
	
	String registerUser(User user);//return success or "user by this email already exists please login"
	
	String changePassword(Integer userId,String oldPassword,String newPassword);
	
	String updateMembership(Integer userId,Integer membershipId); //find membership by membership type then set the membership in user
	
	List<UserCartDto> getUserCart(Integer userId);
	
	double getUserDiscount(Integer userId);
}
