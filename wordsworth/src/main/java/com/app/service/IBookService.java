package com.app.service;

import java.util.List;

import com.app.pojos.Book;
import com.app.pojos.Feedback;

public interface IBookService {
	
	List<Book> getAllBooks();
	
	Book getBookById(Integer id);
	
	List<Book> getBooksByCategory(String category); //need to implement
	
	String updateBookStock(Integer bookId,int stock); 
	
	String updateBookDetails(Integer bookid,double price,String publication,String isbn,String cover);
	
	String updateBookCover(Integer bookId, String bookCover );
	
	String updateBookPrice(Integer bookId,double price);
	
	List<Feedback> getFeedbackByBookId(Integer bookId);
	
	double updateRatingByBookId(Integer bookId); 
	
	String addBook(Book book);

}
