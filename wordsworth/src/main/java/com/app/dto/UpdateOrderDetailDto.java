package com.app.dto;

public class UpdateOrderDetailDto {
	private Integer orderDetailId;
	private String shippingStatus;

	public UpdateOrderDetailDto() {
		super();
	}

	public Integer getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(Integer orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public String getShippingStatus() {
		return shippingStatus;
	}

	public void setShippingStatus(String shippingStatus) {
		this.shippingStatus = shippingStatus;
	}

}
