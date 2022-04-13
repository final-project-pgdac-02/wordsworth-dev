package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.OrderDetailDto;
import com.app.dto.PlaceOrderDto;
import com.app.pojos.OrderDetail;
import com.app.service.IBookService;
import com.app.service.IOrderDetailsService;
import com.app.service.IOrderService;

@RestController
@RequestMapping("/orderdetails")
@CrossOrigin(origins = "http://localhost:3000/")

public class OrderDetailsController {
	
	@Autowired
	private IOrderDetailsService orderDetailsService;
	
	@GetMapping("/{userId}")
	public List<OrderDetailDto> getOrderDetailsByUserId(@PathVariable int userId){
		return orderDetailsService.getOrderDetailsByUserId(userId);
	}
	

}
