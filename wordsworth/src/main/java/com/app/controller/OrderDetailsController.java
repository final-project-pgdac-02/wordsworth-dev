package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.OrderDetailDto;
import com.app.dto.UpdateOrderDetailDto;
import com.app.pojos.ShippingStatus;
import com.app.service.IOrderDetailsService;

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
	
	@PutMapping("/updatestatus")
	public String updateOrderDetailStatus(@RequestBody UpdateOrderDetailDto orderDetailDto) {
		return orderDetailsService.updateShippingStatus(orderDetailDto.getOrderDetailId(), ShippingStatus.valueOf(orderDetailDto.getShippingStatus().toUpperCase()));
	}
	
	@GetMapping("/shippingstatus/{orderDetailId}")
	public String getShippingStatus(@PathVariable int orderDetailId) {
		try {
			return orderDetailsService.getShippingStatusByOrderDetailId(orderDetailId);
		}catch(Exception e) {
			return "Order detail id: "+orderDetailId+" not found in database!!";
		}
			
	}
	

}
