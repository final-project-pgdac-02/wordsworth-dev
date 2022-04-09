package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dao.BookRepository;
import com.app.dao.MembershipRepository;
import com.app.dao.UserRepository;
import com.app.dto.CartSummaryDto;
import com.app.pojos.Book;
import com.app.pojos.CartItem;
import com.app.pojos.Membership;
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

	@Override
	public String incrementCartItem(Integer userId, Integer bookId) {

		User temp = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User by given User ID not found in database"));

		List<CartItem> cartItemList = temp.getCartItems();
				
		CartItem cartItem=	cartItemList.stream().filter((c) -> c.getBook().getId() == bookId).findFirst()
				.orElseThrow(() -> new ResourceNotFoundException("Given Book ID not found in User Cart"));

		cartItem.setQuantity(cartItem.getQuantity()+1);
		
		return "Quantity incremented for bookId= " +bookId;
		

		
	}

	@Override
	public String saveToCart(Integer userId, Integer bookId) {
		
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User by this ID not found!"));
		Book book = bookRepo.findById(bookId)
				.orElseThrow(() -> new ResourceNotFoundException("Book by this ID not found!"));
		double price = book.getPrice();
		String message[] = { "" }; //because local variable can't be accessed in lambda unless it's final/effectively final, make array and change the value inside array
		user.getCartItems().stream().filter(c -> c.getBook().getId() == bookId).findAny().ifPresentOrElse(c-> {c.setQuantity(c.getQuantity()+1); message[0]=book.getBookTitle()+" quantity incremented!";}, ()->{CartItem newCartItem = new CartItem(book, 1, price); user.addCartItem(newCartItem); message[0]=book.getBookTitle()+" added to cart!";});
		
//		CartItem newCartItem = new CartItem(book, 1, price); 
//		user.addCartItem(newCartItem);
//		double totalCartValue = user.getCartItems() //get all cart items
//				.stream() //convert to Stream<CartItem>
//				.mapToDouble(item -> item.getQuantity() * item.getActualPrice()) //for each item, map such that quantity * price
//				.sum(); //sum the prices
//		return totalCartValue;
		
		return message[0];
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

	@Override
	public CartSummaryDto getCartTotalByUserId(Integer userId) {
		User temp = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User by given User ID not found in database"));

		Membership membership= membershipRepo.findById(temp.getMembership().getId()).orElseThrow(()->new ResourceNotFoundException("invalid category token"));	
		
		List<CartItem> cartItemList = temp.getCartItems();
		
		CartSummaryDto cartSummary=new CartSummaryDto();
		
		double[] values = {0,0}; //index 0 = total items, index 1 = cart sub-total
		cartItemList.stream().forEach(item -> {
			values[0]+=item.getQuantity();
			values[1]+=item.getActualPrice()*item.getQuantity();
		});
		cartSummary.setTotalItems((int)values[0]);
		cartSummary.setCartSubTotal(values[1]);
		
//		cartSummary.setTotalItems(cartItemList.stream().mapToInt(c->c.getQuantity()).sum());
//		cartSummary.setCartSubTotal(cartItemList.stream().mapToDouble(c->c.getActualPrice()*c.getQuantity()).sum());
		
		double cartDiscountedTotal = cartSummary.getCartSubTotal()*(100-membership.getDiscount())/100.0;
		cartSummary.setDiscountedTotal(Math.round(cartDiscountedTotal*100.0)/100.0);
		
		return cartSummary;
	}

	@Override
	public String setCartItemQuantityByBookId(Integer userId, Integer bookId, int quantity) {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User by this ID not found!"));
		Book book = bookRepo.findById(bookId)
				.orElseThrow(() -> new ResourceNotFoundException("Book by this ID not found!"));
		String message[] = { "" }; //because local variable can't be accessed in lambda unless it's final/effectively final, make array and change the value inside array
		user.getCartItems().stream().filter(c -> c.getBook().getId() == bookId).findAny().ifPresentOrElse(c-> {c.setQuantity(quantity); message[0]=book.getBookTitle()+" quantity set to "+quantity;}, ()-> message[0]=book.getBookTitle()+" not present in cart!");
		return message[0];
	}
}
