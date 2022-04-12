package com.app.service;

import java.util.List;

import com.app.dto.LoginResponse;
import com.app.dto.UserCartDto;
import com.app.pojos.User;

public interface IUserService {
	LoginResponse loginUser(String email,String password);
	
	String registerUser(User user);//return success or "user by this email already exists please login"
	
	String changePassword(Integer userId,String oldPassword,String newPassword);
	
	String updateMembership(Integer userId,Integer membershipId); //find membership by membership type then set the membership in user

	List<UserCartDto> getUserCart(Integer userId);
	
	double getUserDiscount(Integer userId);
	
	List<User> getAllUsers();
	
	String deleteAUser(Integer userId);
	
	String updateUserDetails(Integer userId,String fName,String lName,String phgoneNumber);

}
