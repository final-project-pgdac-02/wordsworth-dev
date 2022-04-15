package com.app.service;

public interface ICartItemService {
	String saveToCart(Integer userId, Integer bookId);

	String incrementCartItemById(Integer cartId);

	String decrementCartItemById(Integer cartId);

	String setCartItemQuantityById(Integer cartId, int quantity);

	String deleteCartItemByCartItemId(Integer cartItemId);
}
