package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.pojos.OrderDetail;

@Repository
public interface OrderdetailsRepository extends JpaRepository<OrderDetail, Integer>{
	List<OrderDetail> findAllOrderDetailsByOrderId(int orderId); 

}
