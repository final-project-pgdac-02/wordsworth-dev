package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dao.AddressRepository;
import com.app.dao.UserRepository;
import com.app.pojos.Address;
import com.app.pojos.User;

@Service
@Transactional
public class AddressServiceImpl implements IAddressService {
	
	@Autowired
	AddressRepository addressRepo;
	
	@Autowired
	UserRepository userRepo;
	
	
	@Override
	public String addAddress(Integer userId, Address address) {
		User temp= userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User by the given ID does not exist in database"));
		address.setUser(temp);
		addressRepo.save(address);
		return "Address "+address.getAddressName()+" added successfully for user with email: "+temp.getEmail();
	}
	
}
