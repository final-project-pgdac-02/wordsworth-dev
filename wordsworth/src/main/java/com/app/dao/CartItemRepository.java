package com.app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.pojos.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
	List<CartItem> findByUserId(Integer userId);
	Optional<CartItem> findById(Integer id);
	Optional<CartItem> findByUserIdAndBookId(Integer userId,Integer bookId);
	
	@Modifying
	@Query("delete from CartItem c where c.user.id=:id")
	void deleteCartItemsByUserId(@Param("id") Integer userId);
}
