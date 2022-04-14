package com.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dao.OrderdetailsRepository;
import com.app.dto.OrderDetailDto;
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
	public String updateShippingStatus(Integer orderDetailId, ShippingStatus shippingStatus) {

		OrderDetail orderDetailsObject = orderDetailsRepo.findById(orderDetailId)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Given item with order detail id: " + orderDetailId + " not found in database!!"));

		orderDetailsObject.setStatus(shippingStatus);

		return "Shipping Status for Order Detail with id: " + orderDetailId + " has been updated to: "
				+ shippingStatus.toString();
	}

	@Override
	public List<OrderDetailDto> getOrderDetailsByUserId(Integer userId) {
		List<OrderDetail> orderDetails = orderDetailsRepo.findByUserIdOrderByOrderOrderDateDescOrderIdDesc(userId);
		List<OrderDetailDto> orderDetailDtoList = new ArrayList<>();
		for (OrderDetail o : orderDetails) {
			OrderDetailDto newOrderDetail = new OrderDetailDto();
			newOrderDetail.setOrderDetailId(o.getId());
			newOrderDetail.setPrice(o.getBook().getPrice());
			newOrderDetail.setQuantity(o.getQuantity());
			newOrderDetail.setShippingDate(o.getShippingDate());
			newOrderDetail.setShippingStatus(o.getStatus());
			newOrderDetail.setBookId(o.getBook().getId());
			newOrderDetail.setBookTitle(o.getBook().getBookTitle());
			newOrderDetail.setBookCover(o.getBook().getBookCover());
			newOrderDetail.setOrderId(o.getId());
			newOrderDetail.setOrderDate(o.getOrder().getOrderDate());
			newOrderDetail.setDiscountedPrice(o.getPrice());
			newOrderDetail.setUserId(userId);

			orderDetailDtoList.add(newOrderDetail);
		}

		return orderDetailDtoList;
	}

	@Override
	public String deleteOrderDetailByUserId(Integer userId) {
		orderDetailsRepo.deleteOrderDetailByUserId(userId);
		return "All Order Details Deleted of userId : " + userId + " Successfully!!";
	}

	@Override
	public String getShippingStatusByOrderDetailId(Integer orderDetailId) {
		OrderDetail orderDetail = orderDetailsRepo.findById(orderDetailId)
				.orElseThrow(() -> new ResourceNotFoundException(
						"OrderDetail with id: " + orderDetailId + " not found in database!!"));
		return orderDetail.getStatus().toString();
	}

}
