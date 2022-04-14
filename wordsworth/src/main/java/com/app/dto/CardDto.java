package com.app.dto;

import java.time.LocalDate;

import com.app.pojos.Type;

public class CardDto {

	private Integer cardId;
	private String cardHolderName;
	private String cardNumberLastFourDigits;
	private Type type;
	private LocalDate expiryDate;

	public CardDto() {
		super();
	}

	public CardDto(Integer cardId, String cardHolderName, String cardNumberLastFourDigits, Type type,
			LocalDate expiryDate) {
		super();
		this.cardId = cardId;
		this.cardHolderName = cardHolderName;
		this.cardNumberLastFourDigits = cardNumberLastFourDigits;
		this.type = type;
		this.expiryDate = expiryDate;
	}

	public Integer getCardId() {
		return cardId;
	}

	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public String getCardNumberLastFourDigits() {
		return cardNumberLastFourDigits;
	}

	public void setCardNumberLastFourDigits(String cardNumberLastFourDigits) {
		this.cardNumberLastFourDigits = cardNumberLastFourDigits;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Override
	public String toString() {
		return "CardDto [cardId=" + cardId + ", cardHolderName=" + cardHolderName + ", cardNumberLastFourDigits="
				+ cardNumberLastFourDigits + ", type=" + type + ", expiryDate=" + expiryDate + "]";
	}
}
