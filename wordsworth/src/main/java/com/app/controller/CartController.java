package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.CartSummaryDto;
import com.app.dto.UserCartDto;
import com.app.service.ICartItemService;
import com.app.service.IUserService;

@RestController
@RequestMapping("/cart")


@CrossOrigin(origins = "http://localhost:3000/")
public class CartController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ICartItemService cartItemService;

	@GetMapping("/{userId}")
	public List<UserCartDto> getUserCart(@PathVariable Integer userId){
		return userService.getUserCart(userId);
	}
	
	@GetMapping("/getcarttotal/{userId}")
	public CartSummaryDto getCartTotalByUserId(@PathVariable Integer userId) {
		return cartItemService.getCartTotalByUserId(userId);
	}
	
	@PutMapping("/increment/{userId}/{bookId}")
	public String incrementCartItem(@PathVariable Integer userId, @PathVariable Integer bookId) {
		return cartItemService.incrementCartItem(userId, bookId);
	}
	
	@PutMapping("/decrement/{userId}/{bookId}")
	public double decrementCartItem(@PathVariable Integer userId, @PathVariable Integer bookId) {
		return cartItemService.decrementCartItem(userId, bookId);
	}
}
