package com.app.pojos;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="cards")
public class Card extends BaseEntity {
	
	@Column(length = 50)
	@NotBlank
	private String cardHolderName;
	
	@Column(nullable = false,length=16)
	@NotBlank
	@Length(min=16,max=16)
	private String cardNumber;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(length = 30)
	private Type type;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate expiryDate;
	
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	public Card() {
		super();
	}
	
	


	public String getCardHolderName() {
		return cardHolderName;
	}




	public void setCardHolderName(String cardName) {
		this.cardHolderName = cardName;
	}




	public User getUser() {
		return user;
	}




	public void setUser(User user) {
		this.user = user;
	}




	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
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
		return "Card [cardHolderName = "+cardHolderName+" cardNumber=" + cardNumber + ", type=" + type
				+ ", expiryDate=" + expiryDate + " userEmail:" + user.getEmail()+"]";
	}
	
	
	
	
}
