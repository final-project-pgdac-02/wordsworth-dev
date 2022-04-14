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

//		CartItem newCartItem = new CartItem(book, 1, price); 
//		user.addCartItem(newCartItem);
//		double totalCartValue = user.getCartItems() //get all cart items
//				.stream() //convert to Stream<CartItem>
//				.mapToDouble(item -> item.getQuantity() * item.getActualPrice()) //for each item, map such that quantity * price
//				.sum(); //sum the prices
//		return totalCartValue;

		return message;
	}

//	@Override
//	public CartSummaryDto getCartTotalByUserId(Integer userId) {
//		User temp = userRepo.findById(userId)
//				.orElseThrow(() -> new ResourceNotFoundException("User by given User ID not found in database"));
//
//		Membership membership = membershipRepo.findById(temp.getMembership().getId())
//				.orElseThrow(() -> new ResourceNotFoundException("invalid category token"));
//
//		List<CartItem> cartItemList = temp.getCartItems();
//
//		CartSummaryDto cartSummary = new CartSummaryDto();
//
//		double[] values = { 0, 0 }; // index 0 = total items, index 1 = cart sub-total
//		cartItemList.stream().forEach(item -> {
//			values[0] += item.getQuantity();
//			values[1] += item.getActualPrice() * item.getQuantity();
//		});
//		cartSummary.setTotalItems((int) values[0]);
//		cartSummary.setCartSubTotal(Math.round(values[1] * 100.0) / 100.0);
//		double cartDiscountedTotal = 0;
//
////		cartSummary.setTotalItems(cartItemList.stream().mapToInt(c->c.getQuantity()).sum());
////		cartSummary.setCartSubTotal(cartItemList.stream().mapToDouble(c->c.getActualPrice()*c.getQuantity()).sum());
//
//		if (membership.isDiscountIsActive()) { // check if discount active
//			cartDiscountedTotal = cartSummary.getCartSubTotal() * (100 - membership.getDiscount()) / 100.0;
//		} else {
//			cartDiscountedTotal = cartSummary.getCartSubTotal();
//		}
//		cartSummary.setDiscountedTotal(Math.round(cartDiscountedTotal * 100.0) / 100.0);
//
//		return cartSummary;
//	}

	@Override
	public String incrementCartItemById(Integer cartId) {
		CartItem item = cartRepo.findById(cartId).orElseThrow(()-> new ResourceNotFoundException("CartItem by this ID not found! "));
		item.setQuantity(item.getQuantity()+1);
		return "Quantity incremented!";
	}

	@Override
	public String decrementCartItemById(Integer cartId) {
		CartItem item = cartRepo.findById(cartId).orElseThrow(()-> new ResourceNotFoundException("CartItem by this ID not found! "));
		item.setQuantity(item.getQuantity()-1);
		return "Quantity decremented!";
	}

	@Override
	public String setCartItemQuantityById(Integer cartId, int quantity) {
		CartItem item = cartRepo.findById(cartId).orElseThrow(()-> new ResourceNotFoundException("CartItem by this ID not found! "));
		item.setQuantity(quantity);
		return "Quantity set to "+quantity;
	}

	@Override
	public String deleteCartItemByCartItemId(Integer cartItemId) {
		cartRepo.deleteCartItemsByCartItemId(cartItemId);
		return "Cart Item deleted!!";
	}

}
