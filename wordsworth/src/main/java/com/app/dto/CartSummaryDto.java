package com.app.dto;

public class CartSummaryDto {
	private int totalItems;
	private double cartSubTotal;
	private double discountedTotal;
	
	
	
	
	public CartSummaryDto() {
		super();
	}
	public CartSummaryDto(int totalItems, double cartSubTotal, double discountedTotal) {
		super();
		this.totalItems = totalItems;
		this.cartSubTotal = cartSubTotal;
		this.discountedTotal = discountedTotal;
	}
	public int getTotalItems() {
		return totalItems;
	}
	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}
	public double getCartSubTotal() {
		return cartSubTotal;
	}
	public void setCartSubTotal(double cartSubTotal) {
		this.cartSubTotal = cartSubTotal;
	}
	public double getDiscountedTotal() {
		return discountedTotal;
	}
	public void setDiscountedTotal(double discountedTotal) {
		this.discountedTotal = discountedTotal;
	}
	
	
}
