package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import javax.persistence.Table;

@Entity
@Table(name="cart_items")
public class CartItem extends BaseEntity {
	
	
	@ManyToOne
	@JoinColumn(name="user_id", nullable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name="book_id", nullable = false)
	private Book book;
	
	@Min(value = 1)
	private int quantity;

	public CartItem() {
		super();
	}
	
	
	


	public CartItem(User user, Book book, int quantity) {
		super();
		this.user = user;
		this.book = book;
		this.quantity = quantity;
	}


	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	

	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public String toString() {
		return "CartItems [ bookTitle=" + book.getBookTitle() + ", quantity=" + quantity + "]";
	}
	
	
}
