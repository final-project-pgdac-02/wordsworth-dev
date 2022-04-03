package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dao.BookRepository;
import com.app.dao.UserRepository;
import com.app.pojos.Book;
import com.app.pojos.User;

@Service
@Transactional
public class CartItemServiceImpl implements ICartItemService {
	
	@Autowired
	UserRepository userRepo;

	@Autowired
	BookRepository bookRepo;
	
	@Override
	public void incrementCartItem(Integer userId, Integer bookId) {
		Book bookTemp=bookRepo.findById(bookId).orElseThrow(()->new ResourceNotFoundException("Book by given Book ID not found in database"));
		
		User temp=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User by given User ID not found in database"));
		
		temp.getCartItems().stream().filter((c)->c.getBook().getId()==bookTemp.getId()).findFirst().orElseThrow(()->new ResourceNotFoundException("Given Book ID not found in User Cart"));

	}
}
