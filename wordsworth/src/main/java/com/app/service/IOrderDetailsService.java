package com.app.service;

import java.util.List;

import com.app.dto.OrderDetailDto;
import com.app.pojos.OrderDetail;
import com.app.pojos.ShippingStatus;

public interface IOrderDetailsService {
	List<OrderDetail> getOrderDetailsByOrderId(Integer orderId);

	String updateShippingStatus(Integer orderDetailId, ShippingStatus shippingStatus);

	List<OrderDetailDto> getOrderDetailsByUserId(Integer userId);

	String deleteOrderDetailByUserId(Integer userId);

	String getShippingStatusByOrderDetailId(Integer orderDetailId);

}
