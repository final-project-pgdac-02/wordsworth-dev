package com.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dao.AddressRepository;
import com.app.dao.UserRepository;
import com.app.dto.AddressDto;
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
		User temp = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User by the given ID does not exist in database"));
		address.setUser(temp);
		addressRepo.save(address);
		return "Address " + address.getAddressName() + " added successfully for user with email: " + temp.getEmail();
	}

	@Override
	public List<AddressDto> getAddressListByUserId(Integer userId) {
		List<AddressDto> addressList = new ArrayList<AddressDto>();
		for (Address addr : addressRepo.findByUserId(userId)) {
			addressList.add(new AddressDto(addr.getId(), addr.getDetailedAddress(), addr.getCity(),
					addr.getAddressName(), addr.getState(), addr.getCountry(), addr.getPinCode()));
		}
		return addressList;
	}

	@Override
	public Address getAddressByAddressId(Integer addressId) {
		return addressRepo.findById(addressId).orElseThrow(
				() -> new ResourceNotFoundException("Address with addressId: " + addressId + " not found in database"));
	}

	@Override
	public String deleteAddressByUserId(Integer userId) {
		// TODO Auto-generated method stub
		addressRepo.deleteAddressByUserId(userId);
		return "address deleted of user id " + userId;
	}

}
