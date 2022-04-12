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
	
	String updateBookStock(Integer bookId,int stock);
	
	String updateBookCover(Integer bookId, String bookCover );
	
	String updateBookDetails(Integer bookid,double price,String publication,String isbn,String cover);
	
	String updateBookPrice(Integer bookId,double price);
	
	List<Feedback> getFeedbackByBookId(Integer bookId);
	
	double updateRatingByBookId(Integer bookId); 
	
<<<<<<< HEAD
	Book addBook(Book book);
	
	List<String> getAllCategories();
	
=======
	String addBook(Book book);
	
	List<Book> advancedFilterBooks(String category, String rating, String minPrice, String maxPrice);
>>>>>>> c0c6cdca93b68087775665b742955f9b7fe9b42f

}
