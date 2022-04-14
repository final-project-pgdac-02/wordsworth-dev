package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.pojos.OrderDetail;

@Repository
public interface OrderdetailsRepository extends JpaRepository<OrderDetail, Integer> {
	List<OrderDetail> findAllOrderDetailsByOrderId(int orderId);

	List<OrderDetail> findByUserIdOrderByOrderOrderDateDescOrderIdDesc(int userId);

	@Modifying
	@Query("delete from OrderDetail od where od.user.id=:id")
	void deleteOrderDetailByUserId(@Param("id") Integer userId);

}
