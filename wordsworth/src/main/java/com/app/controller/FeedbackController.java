package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.IBookService;

@RestController
@RequestMapping("/feedbacks")
@CrossOrigin(origins = "http://localhost:3000/")

public class FeedbackController {
	
	@Autowired
	IBookService bookServ;
	
//	@GetMapping("/{id}/feedbacks")
	@GetMapping("/{id}")
	public ResponseEntity<?> getSomething(@PathVariable Integer id) {
		return new ResponseEntity<>(bookServ.getFeedbackByBookId(id),HttpStatus.OK); 
	}
	

}
