package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dao.CardRepository;
import com.app.dao.UserRepository;
import com.app.pojos.Card;
import com.app.pojos.User;

@Service
@Transactional
public class CardServiceImpl implements ICardService {
	
	@Autowired
	CardRepository cardRepo;
	
	@Autowired
	UserRepository userRepo;

	@Override
	public String addCard(Integer userId, Card card) {
		User temp= userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User by the given ID does not exist in database"));
		card.setUser(temp);
		cardRepo.save(card);
		return "Card "+card.getCardHolderName()+" added successfully for user with email: "+temp.getEmail();
	}

}
