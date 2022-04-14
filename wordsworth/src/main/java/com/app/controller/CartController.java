package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<?> getUserCart(@PathVariable Integer userId){
		return ResponseEntity.ok().body(userService.getUserCart(userId));
	}
	
	@PutMapping("/increment/{cartId}")
	public String incrementCartItem(@PathVariable Integer cartId) {
		return cartItemService.incrementCartItemById(cartId);
	}
	
	@PutMapping("/decrement/{cartId}")
	public String decrementCartItem(@PathVariable Integer cartId) {
		return cartItemService.decrementCartItemById(cartId);
	}
	
	@DeleteMapping("/{cartId}")
	public String deleteCartItem(@PathVariable Integer cartId) {
		return cartItemService.deleteCartItemByCartItemId(cartId);
	}
	
}
