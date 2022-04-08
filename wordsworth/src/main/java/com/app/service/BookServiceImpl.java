package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dao.BookRepository;
import com.app.dao.FeedbackRepository;
import com.app.pojos.Book;
import com.app.pojos.Category;
import com.app.pojos.Feedback;

@Service
@Transactional
public class BookServiceImpl implements IBookService {

	@Autowired
	BookRepository bookRepo;
	
	@Autowired
	FeedbackRepository feedbackRepo;
	
	@Override
	public Book getBookById(Integer id) {
		return bookRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Book by given Id not found in database"));
	}

	@Override
	public List<Book> getBooksByCategory(String category) {
		return bookRepo.findByCategory(Category.valueOf(category.toUpperCase()));
	}

	@Override
	public String updateBookStock(Integer bookId, int stock) {
		Book temp=bookRepo.findById(bookId).orElseThrow(()->new ResourceNotFoundException("Book by given ID not found in database"));
		temp.setStock(stock);
		return "Stock updated to: "+stock+" for Book: "+temp.getBookTitle()+" with book id: "+bookId;
	}

	@Override
	public List<Book> getAllBooks() {
		return bookRepo.findAll();
	}

	@Override
	public String updateBookPrice(Integer bookId, double price) {
		Book book = bookRepo.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Could not find book by this ID!"));
		book.setPrice(price);
		return "Book price for "+book.getBookTitle()+" has been updated to "+price;
	}

	@Override
	public String updateBookCover(Integer bookId, byte[] bookCover) {
		Book book = bookRepo.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Could not find book by this ID!"));
		book.setBookCover(bookCover);
		return "Book Cover for "+book.getBookTitle()+" has been updated!";
	}

	@Override
	public List<Feedback> getFeedbackByBookId(Integer bookId) {
		return feedbackRepo.findByBookId(bookId);
	}
	
	@Override
	public String addBook(Book addBook) {
		bookRepo.save(addBook);
		return "book added successfully by Title : " + addBook.getBookTitle();
	}

}
