package com.app.service;

import com.app.pojos.Feedback;

public interface IFeedbackService {

	String addFeedback(Integer bookId,Feedback feedback); //fetch book by id and feedback.setBook(book)
}
