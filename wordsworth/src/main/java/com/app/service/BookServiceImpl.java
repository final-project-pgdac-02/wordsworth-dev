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
		return bookRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Book by given Id not found in database"));
	}

	@Override
	public List<Book> getBooksByCategory(String category) {
		return bookRepo.findByCategory(Category.valueOf(category.toUpperCase()));
	}

	@Override
	public String updateBookStock(Integer bookId, int stock) {
		Book temp = bookRepo.findById(bookId)
				.orElseThrow(() -> new ResourceNotFoundException("Book by given ID not found in database"));
		temp.setStock(temp.getStock() + stock);

		return "Stock updated to: " + temp.getStock() + " for Book: " + temp.getBookTitle() + " with book id: "
				+ bookId;

	}

	@Override
	public List<Book> getAllBooks() {
		return bookRepo.findAll();
	}

	@Override
	public String updateBookPrice(Integer bookId, double price) {
		Book book = bookRepo.findById(bookId)
				.orElseThrow(() -> new ResourceNotFoundException("Could not find book by this ID!"));
		book.setPrice(price);
		return "Book price for " + book.getBookTitle() + " has been updated to " + price;
	}

	@Override
	public String updateBookCover(Integer bookId, String bookCover) {
		Book book = bookRepo.findById(bookId)
				.orElseThrow(() -> new ResourceNotFoundException("Could not find book by this ID!"));
		book.setBookCover(bookCover);
		return "Book Cover for " + book.getBookTitle() + " has been updated!";
	}

	@Override
	public List<Feedback> getFeedbackByBookId(Integer bookId) {
		return feedbackRepo.findByBookId(bookId);
	}

	@Override
	public Book addBook(Book addBook) {

//		"book added successfully by Title : " + addBook.getId();
		return bookRepo.save(addBook);
	}

	@Override
	public double updateRatingByBookId(Integer bookId) {
		Book temp = bookRepo.findById(bookId)
				.orElseThrow(() -> new ResourceNotFoundException("Book by given ID not found!"));
		double calculatedRating = feedbackRepo.findByBookId(bookId).stream().mapToDouble(f -> f.getRating()).average()
				.orElse(1);
		calculatedRating = Math.round(calculatedRating * 100.0) / 100.0;
		temp.setAverageRating(calculatedRating);
		return calculatedRating;
	}

	@Override
	public List<Book> getBookByTitle(String title) {
		return bookRepo.getBookByTitle(title);
	}

	@Override
	public List<String> getAllCategories() {
		return bookRepo.getCategories();
	}

	@Override
	public String updateBookDetails(Integer bookid, double price, String publication, String isbn, String cover) {
		Book temp = bookRepo.findById(bookid)
				.orElseThrow(() -> new ResourceNotFoundException("Book by Given ID not found!"));

		temp.setPrice(price);
		temp.setPublication(publication);
		temp.setIsbn(isbn);
		temp.setBookCover(cover);

		return "Book Details Updated Successfully!!";
	}

	@Override
	public List<Book> advancedFilterBooks(String category, String rating, String minPrice, String maxPrice) {
		Category c = null;
		Double rat = null;
		Double min = null;
		Double max = null;
		if (!category.equals("") && (category != null) && !(category.toUpperCase().equals("NULL"))) {
			c = Category.valueOf(category.toUpperCase());
		}
		if (!rating.equals("") && (rating != null) && !(rating.toUpperCase().equals("NULL"))) {
			rat = Double.parseDouble(rating);
		}
		if (!minPrice.equals("") && (minPrice != null) && !(minPrice.toUpperCase().equals("NULL"))) {
			min = Double.parseDouble(minPrice);
		}
		if (!maxPrice.equals("") && (maxPrice != null) && !(maxPrice.toUpperCase().equals("NULL"))) {
			max = Double.parseDouble(maxPrice);
		}
		return bookRepo.filterBooks(c, rat, min, max);
	}

}
