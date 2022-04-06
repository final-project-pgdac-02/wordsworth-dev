package com.app.dto;

public class CartItemDto {
	
	private int bookId;
	private int userId;
	private int quantity;
	
	
	
	public CartItemDto() {
		super();
	}



	public int getBookId() {
		return bookId;
	}



	public void setBookId(int bookId) {
		this.bookId = bookId;
	}



	public int getUserId() {
		return userId;
	}



	public void setUserId(int userId) {
		this.userId = userId;
	}



	public int getQuantity() {
		return quantity;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
