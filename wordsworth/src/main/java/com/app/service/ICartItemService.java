package com.app.service;

public interface ICartItemService {
	void saveToCart(Integer userId,Integer bookId,int quantity); //create cartItem obj -> fetch book by Id, set book & set quantity in CartItem obj -> fetch user by id -> call user's helper method to add cart item
	void incrementCartItem(Integer userId,Integer bookId);
	void decrementCartItem(Integer userId,Integer bookId); // need to be implemented!!!
}