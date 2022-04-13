package com.app.service;

import java.util.List;

import com.app.dto.AddressDto;
import com.app.pojos.Address;

public interface IAddressService {

	String addAddress(Integer userId,Address address);
	
	List<AddressDto> getAddressListByUserId(Integer userId);
	
	Address getAddressByAddressId(Integer addressId);
	String deleteAddressByUserId(Integer userId);
	
}
