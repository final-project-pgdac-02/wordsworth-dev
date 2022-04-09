package com.app.service;

import com.app.dto.CartSummaryDto;

public interface ICartItemService {
	String saveToCart(Integer userId,Integer bookId); //create cartItem obj -> fetch book by Id, set book & set quantity in CartItem obj -> fetch user by id -> call user's helper method to add cart item
	String incrementCartItem(Integer userId,Integer bookId);
	double decrementCartItem(Integer userId,Integer bookId); // need to be implemented!!!
	CartSummaryDto getCartTotalByUserId(Integer userId);
	String setCartItemQuantityByBookId(Integer userId,Integer bookId, int quantity);
}
