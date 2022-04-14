package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.app.custom_exception.ResourceNotFoundException;
import com.app.dao.BookRepository;
import com.app.dao.CartItemRepository;
import com.app.dao.MembershipRepository;
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

	@Autowired
	MembershipRepository membershipRepo;

	@Autowired
	CartItemRepository cartRepo;

	@Override
	public String saveToCart(Integer userId, Integer bookId) {

		String message = "";

		CartItem c = cartRepo.findByUserIdAndBookId(userId, bookId).orElse(null);

		if (c != null) {
			c.setQuantity(c.getQuantity() + 1);
			message = "Quantity incremented!";
			return message;
		}

		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User by this ID not found!"));
		Book book = bookRepo.findById(bookId)
				.orElseThrow(() -> new ResourceNotFoundException("Book by this ID not found!"));

		c = new CartItem(user, book, 1);
		cartRepo.save(c);
		message = "Added to cart!";
		return message;
	}

	@Override
	public String incrementCartItemById(Integer cartId) {
		CartItem item = cartRepo.findById(cartId)
				.orElseThrow(() -> new ResourceNotFoundException("CartItem by this ID not found! "));
		item.setQuantity(item.getQuantity() + 1);
		return "Quantity incremented!";
	}

	@Override
	public String decrementCartItemById(Integer cartId) {
		CartItem item = cartRepo.findById(cartId)
				.orElseThrow(() -> new ResourceNotFoundException("CartItem by this ID not found! "));
		item.setQuantity(item.getQuantity() - 1);
		return "Quantity decremented!";
	}

	@Override
	public String setCartItemQuantityById(Integer cartId, int quantity) {
		CartItem item = cartRepo.findById(cartId)
				.orElseThrow(() -> new ResourceNotFoundException("CartItem by this ID not found! "));
		item.setQuantity(quantity);
		return "Quantity set to " + quantity;
	}

	@Override
	public String deleteCartItemByCartItemId(Integer cartItemId) {
		cartRepo.deleteCartItemsByCartItemId(cartItemId);
		return "Cart Item deleted!!";
	}

}
