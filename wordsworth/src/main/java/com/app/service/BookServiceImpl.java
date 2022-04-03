package com.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dao.BookRepository;
import com.app.pojos.Book;
import com.app.pojos.Category;

@Service
@Transactional
public class BookServiceImpl implements IBookService {

	BookRepository bookRepo;
	
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

}
