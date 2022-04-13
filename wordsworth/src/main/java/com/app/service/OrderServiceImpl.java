package com.app.service;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exception.InsufficientStockException;
import com.app.custom_exception.PaymentException;
import com.app.custom_exception.ResourceNotFoundException;
import com.app.dao.AddressRepository;
import com.app.dao.CardRepository;
import com.app.dao.CartItemRepository;
import com.app.dao.OrderRepository;
import com.app.dao.OrderdetailsRepository;
import com.app.dao.UserRepository;
import com.app.pojos.Address;
import com.app.pojos.Book;
import com.app.pojos.Card;
import com.app.pojos.CartItem;
import com.app.pojos.Order;
import com.app.pojos.OrderDetail;
import com.app.pojos.ShippingStatus;
import com.app.pojos.User;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private CartItemRepository cartItemRepo;

	@Autowired
	private IUserService userService;
	
	@Autowired
	private ICardService cardService;
	
	@Autowired
	private IAddressService addressService;
	
	@Autowired
	private OrderdetailsRepository orderDetailsRepo;
	
	
	@Override
	public List<Order> getOrdersByUserId(Integer userId) {

		return orderRepo.findAllOrdersByUserId(userId);
	}

	@Override
	public String placeOrderByUserId(Integer userId, Integer addressId, Integer cardId) {
		User user=userService.getUserByUserId(userId);
		
		Address address=addressService.getAddressByAddressId(addressId);
		
		if(address.getUser().getId()!=userId)
			throw new RuntimeException("Address with add id: "+addressId+" is not linked to user with userId: "+userId);
		
		Card card= cardService.getCardByCardId(cardId);
		
		if(card.getUser().getId()!=userId)
			throw new RuntimeException("Card witd cardId: "+cardId+" is not linked to user with userId: "+userId);
		
		if(card.getExpiryDate().isBefore(LocalDate.now())) {
			throw new PaymentException("Card used for placing order has already expired!!");
		}
		
		List<CartItem> userCart= cartItemRepo.findByUserId(userId);
		if(userCart.size()==0) {
			throw new ResourceNotFoundException("Can't place order, Cart is Empty!!");
		}
		
		double totalCost=0;
		for(CartItem c: userCart) {
			Book currentBook=c.getBook();
			if(currentBook.getStock()<c.getQuantity()) {
				throw new InsufficientStockException("Sorry! "+currentBook.getBookTitle()+" has insufficient stock!! Pls try again later");
			}
			
			currentBook.setStock(currentBook.getStock()-c.getQuantity());
			
			totalCost+=c.getQuantity()*currentBook.getPrice();
			
		}
		
		
		
		double userDiscount=0;
		if(user.getMembership().isDiscountIsActive()) {
			userDiscount=user.getMembership().getDiscount();
		}
		
		totalCost=Math.ceil(totalCost*100)/100;
		
		double discountedTotal=totalCost*(100-userDiscount)/100;
		
		discountedTotal=Math.ceil(discountedTotal*100)/100;
	
		Order order=new Order(LocalDate.now(),totalCost,discountedTotal,user,address,card);
		
		Order newOrder=orderRepo.save(order);
		
//		System.err.println("order placed with id: "+o.getId()+"    orderId:: "+order.getId());
		
		for(CartItem c: userCart) {
			OrderDetail orderItem=new OrderDetail();
			orderItem.setPrice(Math.ceil((c.getBook().getPrice()*(100-userDiscount)/100)*100)/100);
			orderItem.setQuantity(c.getQuantity());
			orderItem.setShippingDate(null);
			orderItem.setStatus(ShippingStatus.PENDING);
			orderItem.setBook(c.getBook());
			orderItem.setOrder(newOrder);
			orderItem.setUser(user);
			orderDetailsRepo.save(orderItem);
		}
		
		cartItemRepo.deleteCartItemsByUserId(userId);
		
		return "order placed with id: " + newOrder.getId();
	}
	

}
