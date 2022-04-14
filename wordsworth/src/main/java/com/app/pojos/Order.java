package com.app.pojos;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="orders")
public class Order extends BaseEntity {
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@FutureOrPresent
	@NotNull
	private LocalDate orderDate;
	
	@Range(min=0)
	@Column(precision = 2)
	private double orderTotal;
	
	@Range(min=0)
	@Column(precision = 2)
	private double discountedTotal;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="address_id")
	private Address address;
	
	@ManyToOne
	@JoinColumn(name="card_id")
	private Card card;

	public Order() {
		super();
	}
	
	
	
	

	public Order(@FutureOrPresent @NotNull LocalDate orderDate, @Range(min = 0) double orderTotal,
			@Range(min = 0) double discountedTotal, User user, Address address, Card card) {
		super();
		this.orderDate = orderDate;
		this.orderTotal = orderTotal;
		this.discountedTotal = discountedTotal;
		this.user = user;
		this.address = address;
		this.card = card;
	}





	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public Address getAddress() {
		return address;
	}



	public void setAddress(Address address) {
		this.address = address;
	}



	public Card getCard() {
		return card;
	}



	public void setCard(Card card) {
		this.card = card;
	}



	public double getDiscountedTotal() {
		return discountedTotal;
	}



	public void setDiscountedTotal(double discountedTotal) {
		this.discountedTotal = discountedTotal;
	}



	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public double getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(float orderTotal) {
		this.orderTotal = orderTotal;
	}



	@Override
	public String toString() {
		return "Order [orderDate=" + orderDate + ", orderTotal=" + orderTotal + ", discountedTotal=" + discountedTotal
				+ "]";
	}

	
	
	
}
