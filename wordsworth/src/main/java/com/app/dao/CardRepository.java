package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.app.pojos.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
	List<Card> findByUserId(Integer userId);
}
