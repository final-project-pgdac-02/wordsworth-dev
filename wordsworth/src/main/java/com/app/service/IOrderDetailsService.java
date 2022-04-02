package com.app.service;

import java.util.List;

import com.app.pojos.OrderDetail;

public interface IOrderDetailsService {
	List<OrderDetail> getOrderDetailsByOrderId(Integer orderId);
	
	String updateShippingStatus(Integer orderDetailId,String shippingStatus); 
	
	
}
