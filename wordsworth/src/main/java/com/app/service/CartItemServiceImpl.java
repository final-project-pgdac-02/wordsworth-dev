package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dao.BookRepository;
import com.app.dao.UserRepository;
import com.app.pojos.Book;
import com.app.pojos.CartItem;
import com.app.pojos.User;

@Service
@Transactional
public class CartItemServiceImpl implements ICartItemService {

	@Autowired
	UserRepository userRepo;

	@Autowired
	BookRepository bookRepo;

	@Override
	public double incrementCartItem(Integer userId, Integer bookId) {

		User temp = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User by given User ID not found in database"));

		List<CartItem> cartItemList = temp.getCartItems();
				
		CartItem cartItem=	cartItemList.stream().filter((c) -> c.getBook().getId() == bookId).findFirst()
				.orElseThrow(() -> new ResourceNotFoundException("Given Book ID not found in User Cart"));

		cartItem.setQuantity(cartItem.getQuantity()+1);
		
		return cartItemList.stream().mapToDouble(c->c.getActualPrice()*c.getQuantity()).sum();
		
	}

	@Override
	public double saveToCart(Integer userId, Integer bookId, int quantity) {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User by this ID not found!"));
		Book book = bookRepo.findById(bookId)
				.orElseThrow(() -> new ResourceNotFoundException("Book by this ID not found!"));
		double price = book.getPrice();
		CartItem newCartItem = new CartItem(book, quantity, price);
		user.addCartItem(newCartItem);
		double totalCartValue = user.getCartItems() //get all cart items
				.stream() //convert to Stream<CartItem>
				.mapToDouble(item -> item.getQuantity() * item.getActualPrice()) //for each item, map such that quantity * price
				.sum(); //sum the prices
		return totalCartValue;
	}

	@Override
	public double decrementCartItem(Integer userId, Integer bookId) {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User by this ID not found!"));
		double totalCartValue = 0;
		for (CartItem item : user.getCartItems()) {
			if(item.getBook().getId()==bookId) {
				item.setQuantity(item.getQuantity()-1);
			}
			totalCartValue+=item.getActualPrice()*item.getQuantity();
		}
		return totalCartValue;
	}
}
