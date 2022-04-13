package com.app.service;

import java.util.List;

import com.app.dto.CardDto;
import com.app.pojos.Card;

public interface ICardService {

	String addCard(Integer userId,Card card);
	
	List<CardDto> getCardsByUserId(Integer userId);
	Card getCardByCardId(Integer cardId);
	
	String deleteAUserCard(Integer userId);
}
