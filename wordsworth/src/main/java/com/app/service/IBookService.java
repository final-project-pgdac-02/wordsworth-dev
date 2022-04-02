package com.app.service;

import java.util.List;

import com.app.pojos.Book;

public interface IBookService {
	
	List<Book> getAllBooks();
	
	Book getBookById(Integer id);
	
	List<Book> getBooksByBookTitleAndCategory(String title,String category); //need to implement
	
	String updateBookStock(Integer bookId,int stock);
	
	String updateBookCover(Integer bookId,byte[] bookCover);
	
	String updateBookPrice(Integer bookId,double price);
}
