package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.PlaceOrderDto;
import com.app.service.IOrderService;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:3000/")

public class OrderController {

	@Autowired
	private IOrderService orderService;

	@PostMapping("/placeorder")
	public String testPlaceOrder(@RequestBody PlaceOrderDto orderDto) {
		try {
			return orderService.placeOrderByUserId(orderDto.getUserId(), orderDto.getAddressId(), orderDto.getCardId());
		} catch (Exception e) {
			return e.getMessage();
		}
	}
}
