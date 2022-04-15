package com.app.pojos;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "order_details")
public class OrderDetail extends BaseEntity {

	@Range(min = 1)
	@NotNull
	private int quantity;

	@Enumerated(EnumType.STRING)
	@Column(name = "shipping_status", columnDefinition = "varchar(45) default 'PENDING'")
	private ShippingStatus status;

	@Range(min = 0)
	@NotNull
	@Column(name = "price", precision = 2)
	private double price;

	@Column(name = "shipping_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate shippingDate;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;

	@ManyToOne
	@JoinColumn(name = "book_id")
	private Book book;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public OrderDetail() {
		super();
	}

	{
		status = ShippingStatus.PENDING;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
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

	public ShippingStatus getStatus() {
		return status;
	}

	public void setStatus(ShippingStatus status) {
		this.status = status;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDate getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(LocalDate shippingDate) {
		this.shippingDate = shippingDate;
	}

	@Override
	public String toString() {
		return "OrderDetail [quantity=" + quantity + ", status=" + status + ", price=" + price + ", shippingDate="
				+ shippingDate + "]";
	}

}
