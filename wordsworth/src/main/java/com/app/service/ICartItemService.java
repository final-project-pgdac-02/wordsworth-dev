package com.app.service;

import com.app.dto.CartSummaryDto;

public interface ICartItemService {
	String saveToCart(Integer userId,Integer bookId); //create cartItem obj -> fetch book by Id, set book & set quantity in CartItem obj -> fetch user by id -> call user's helper method to add cart item
	String incrementCartItemById(Integer cartId);
	String decrementCartItemById(Integer cartId);
//	CartSummaryDto getCartTotalByUserId(Integer userId);
	String setCartItemQuantityById(Integer cartId, int quantity);
}
