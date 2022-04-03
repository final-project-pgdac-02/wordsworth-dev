package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dao.MembershipRepository;
import com.app.dao.UserRepository;
import com.app.pojos.Membership;
import com.app.pojos.User;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private MembershipRepository membershipDao;

	@Override
	public User loginUser(String email, String password) {
		return userRepo.findByEmailAndPassword(email, password)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Credentials!!!"));
	}

	@Override
	public String registerUser(User user) {

		userRepo.save(user);

		return "user registered successfully : " + user.getEmail();

	}

	@Override
	public String changePassword(Integer userId, String oldPassword, String newPassword) {
		User changedUserPassword = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user does not exists with this id!!!"));

		if (changedUserPassword.getPassword().equals(oldPassword)) {
			changedUserPassword.setPassword(newPassword);
			return "password changed successfully!!!";
		}
		return "old password does not matches!!!";

	}

	@Override
	public String updateMembership(Integer userId, Integer membershipId) {

		User updateUserMembership = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user does not exists with this id!!!"));

		Membership updatedMembership = membershipDao.findById(membershipId)
				.orElseThrow(() -> new ResourceNotFoundException("invalid membership id!!!"));

		updateUserMembership.setMembership(updatedMembership);

		return "membership updated successfully for user : " + updateUserMembership.getEmail();

	}

}
