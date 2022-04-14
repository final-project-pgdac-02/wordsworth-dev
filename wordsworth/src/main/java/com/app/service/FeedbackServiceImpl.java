package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dao.BookRepository;
import com.app.dao.FeedbackRepository;
import com.app.pojos.Book;
import com.app.pojos.Feedback;

@Service
@Transactional
public class FeedbackServiceImpl implements IFeedbackService {

	@Autowired
	private BookRepository bookRepo;

	@Autowired
	private FeedbackRepository feedbackRepo;

	@Autowired
	private IBookService bookServ;

	@Override
	public String addFeedback(Integer bookId, Feedback feedback) {

		Book book = bookRepo.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("invalid book id!!!"));

		feedback.setBook(book);

		Feedback addedFeedback = feedbackRepo.save(feedback);

		return "feedback added for book title : " + book.getBookTitle() + " | feedback is : "
				+ addedFeedback.getReview();
	}

	@Override
	public String updateAllBookRatings() {
		bookRepo.findAll().forEach(book -> bookServ.updateRatingByBookId(book.getId()));
		return "All Book Ratings Updated!";
	}

}
