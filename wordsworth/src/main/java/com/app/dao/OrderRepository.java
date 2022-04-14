package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.pojos.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

	List<Order> findAllOrdersByUserId(Integer userId);
	
	@Modifying
	@Query("delete from Order o where o.user.id=:id")
	void deleteOrderByUserId(@Param("id") Integer userId);
}
