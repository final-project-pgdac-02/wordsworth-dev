package com.app.service;

import java.util.List;

import com.app.pojos.Book;
import com.app.pojos.Feedback;

public interface IBookService {

	List<Book> getAllBooks();

	Book getBookById(Integer id);

	List<Book> getBooksByCategory(String category);

	String updateBookStock(Integer bookId, int stock);

	String updateBookCover(Integer bookId, String bookCover);

	String updateBookDetails(Integer bookid, double price, String publication, String isbn, String cover);

	String updateBookPrice(Integer bookId, double price);

	List<Feedback> getFeedbackByBookId(Integer bookId);

	double updateRatingByBookId(Integer bookId);

	Book addBook(Book book);

	List<String> getAllCategories();

	List<Book> getBookByTitle(String title);

	List<Book> advancedFilterBooks(String category, String rating, String minPrice, String maxPrice);

}
