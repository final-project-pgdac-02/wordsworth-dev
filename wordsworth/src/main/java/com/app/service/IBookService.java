package com.app.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.pojos.Book;
import com.app.pojos.Feedback;

public interface IBookService {
	
	List<Book> getAllBooks();
	
	Book getBookById(Integer id);
	
	List<Book> getBooksByCategory(String category); //need to implement
	
	String updateBookStock(Integer bookId,int stock); //need to implement
	
	String updateBookCover(Integer bookId, String bookCover );
	
	String updateBookPrice(Integer bookId,double price);
	
	List<Feedback> getFeedbackByBookId(Integer bookId);
	
	double updateRatingByBookId(Integer bookId); 
	
	String addBook(Book book);
	

	List<Book> getBookByTitle(String title);

	List<Book> advancedFilterBooks(String category, String rating, String minPrice, String maxPrice);


}
