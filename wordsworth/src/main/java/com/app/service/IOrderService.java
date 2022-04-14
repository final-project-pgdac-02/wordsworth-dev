package com.app.service;

import java.util.List;

import com.app.pojos.Order;

public interface IOrderService {
	List<Order> getOrdersByUserId(Integer userId);

	String placeOrderByUserId(Integer userId, Integer addressId, Integer cardId);

	String deleteOrderByUserId(Integer userId);
}
