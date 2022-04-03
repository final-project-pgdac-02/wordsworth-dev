package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.pojos.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

	List<Order> findAllOrdersByUserId(Integer userId);
}
