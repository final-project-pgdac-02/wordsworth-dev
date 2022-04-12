package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.app.pojos.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
	List<Card> findByUserId(Integer userId);
	
	@Modifying
	@Query("delete from Card c where c.user.id=:id")
	void deleteCardByUserId(@Param("id") Integer userId);
}
