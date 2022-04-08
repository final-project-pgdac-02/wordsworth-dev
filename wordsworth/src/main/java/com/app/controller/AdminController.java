package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.Book;
import com.app.service.IBookService;
import com.app.service.IFeedbackService;

@RequestMapping("/admin")
@RestController
@CrossOrigin(origins = "http://localhost:3000/")
public class AdminController {
	
	@Autowired
	IBookService bookService;
	
	@Autowired
	IFeedbackService feedbackService;
	
	@PostMapping("/addNewBook")
	public ResponseEntity<?> addNewBook(@RequestBody Book book){
		return ResponseEntity.ok(bookService.addBook(book));
	}
	
	

}
