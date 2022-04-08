package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "books")
public class Book extends BaseEntity {

	@Column(name = "book_title", length = 100, nullable = false)
	private String bookTitle;

	@Column(length = 100, nullable = false)
	private String author;

	@Column(length = 100, nullable = false)
	private String publication;

	@Column(nullable = false, length = 50)
	@Enumerated(EnumType.STRING)
	@NotNull
	private Category category;

	@Column(name = "book_cover")
	@Lob
	private byte[] bookCover;

	@Range(min = 0, message = "Stock cannot be negative")
	private int stock;

	@Range(min = 1, max = 5, message = "Rating must be between 1 and 5")
	@Column(precision = 2)
	private double averageRating;

	@Range(min = 1, message = "Price cannot be negative")
	@Column(precision = 2)
	private double price;

	@Column(name = "isbn", unique = true, nullable = false)
	@NotBlank
	@Length(min = 13, max = 13)
	private String isbn;

	public Book() {
		super();
	}

//	public Book(String bookTitle, String author, String publication, Category category, int stock, double averageRating,
//			double price, String isbn) {
//		super();
//		this.bookTitle = bookTitle;
//		this.author = author;
//		this.publication = publication;
//		this.category = category;
//		this.stock = stock;
//		this.averageRating = averageRating;
//		this.price = price;
//		this.isbn = isbn;
//	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublication() {
		return publication;
	}

	public void setPublication(String publication) {
		this.publication = publication;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public byte[] getBookCover() {
		return bookCover;
	}

	public void setBookCover(byte[] bookCover) {
		this.bookCover = bookCover;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}

	@Override
	public String toString() {
		return "Book [bookTitle=" + bookTitle + ", author=" + author + ", publication=" + publication + ", category="
				+ category + ", stock=" + stock + ", rating=" + averageRating + ", price=" + price + ", isbn=" + isbn
				+ "]";
	}

}
