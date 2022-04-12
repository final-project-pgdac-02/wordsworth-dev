package com.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dao.CardRepository;
import com.app.dao.UserRepository;
import com.app.dto.CardDto;
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

	@Override
	public List<CardDto> getCardsByUserId(Integer userId) {
		List<CardDto> cardList = new ArrayList<>();
		for (Card card : cardRepo.findByUserId(userId)) {
			CardDto current = new CardDto();
			current.setCardId(card.getId());
			current.setCardHolderName(card.getCardHolderName());
			current.setExpiryDate(card.getExpiryDate());
			current.setType(card.getType());
			String lastFourDigits = card.getCardNumber().substring(12, 16);
			current.setCardNumberLastFourDigits(lastFourDigits);
			cardList.add(current);
		}
		return cardList;
	}

	@Override
	public Card getCardByCardId(Integer cardId) {
		return cardRepo.findById(cardId).orElseThrow(()->new ResourceNotFoundException("Card with cardId: "+cardId+" not found in database"));
	}

}
