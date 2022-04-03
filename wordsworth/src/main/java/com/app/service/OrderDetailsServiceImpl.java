package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dao.OrderdetailsRepository;
import com.app.pojos.OrderDetail;
import com.app.pojos.ShippingStatus;

@Service
@Transactional
public class OrderDetailsServiceImpl implements IOrderDetailsService {

	@Autowired
	private OrderdetailsRepository orderDetailsRepo;

	@Override
	public List<OrderDetail> getOrderDetailsByOrderId(Integer orderId) {

		return orderDetailsRepo.findAllOrderDetailsByOrderId(orderId);
	}

	@Override
	public OrderDetail updateShippingStatus(Integer orderDetailId, ShippingStatus shippingStatus) {

		OrderDetail orderDetailsObject = orderDetailsRepo.findById(orderDetailId)
				.orElseThrow(() -> new ResourceNotFoundException("order details not found by this id!!!"));

		orderDetailsObject.setStatus(shippingStatus);

		return orderDetailsObject;
	}

}
