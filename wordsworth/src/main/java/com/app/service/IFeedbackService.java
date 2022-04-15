package com.app.service;

import com.app.pojos.Feedback;

public interface IFeedbackService {

	String addFeedback(Integer bookId, Feedback feedback);

	String updateAllBookRatings();
}
