package com.app.dto;

public class UserCartDto {

	private int bookId;
	private int userId;
	private int quantity;
	private String bookCover;
	private double price;
	private String bookTitle;
	
	
	public UserCartDto() {
		super();
	}


	public UserCartDto(int bookId, int userId, int quantity, String bookCover, double price, String bookTitle) {
		super();
		this.bookId = bookId;
		this.userId = userId;
		this.quantity = quantity;
		this.bookCover = bookCover;
		this.price = price;
		this.bookTitle = bookTitle;
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


	public String getBookCover() {
		return bookCover;
	}


	public void setBookCover(String bookCover) {
		this.bookCover = bookCover;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public String getBookTitle() {
		return bookTitle;
	}


	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	
	
}
