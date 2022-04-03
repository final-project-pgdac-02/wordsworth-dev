package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "feedbacks")
public class Feedback extends BaseEntity {

	@Range(min=1,max=5, message = "Rating should be between 1 and 5!")
	@Column(precision = 1)
	private double rating;
	
	@Column(length = 500)
	private String review;

	@ManyToOne
	@JoinColumn(name = "book_id", nullable = false)
	private Book book;
	
	
	public Feedback() {
		super();
	}
	
	

	public double getRating() {
		return rating;
	}

	public Book getBook() {
		return book;
	}



	public void setBook(Book book) {
		this.book = book;
	}



	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}



	@Override
	public String toString() {
		return "Feedback [rating=" + rating + ", review=" + review + "book= "+ book.getBookTitle()+" ]";
	}
	
	
	
}
