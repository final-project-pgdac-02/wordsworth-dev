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
@RequestMapping("/books")
@CrossOrigin(origins = "http://localhost:3000/")
//@CrossOrigin(origins = "*")
public class BookController {
	
	@Autowired
	IBookService bookServ;

	@GetMapping("/{id}")
	public ResponseEntity<?> getBookById(@PathVariable int id){
		return new ResponseEntity<>(bookServ.getBookById(id),HttpStatus.OK);
	}
	
//	@GetMapping("/feedbacks/{id}")
//	public ResponseEntity<?> getSomething(@PathVariable Integer id) {
//		return new ResponseEntity<>(bookServ.getFeedbackByBookId(id),HttpStatus.OK); 
//	}
	
	
	
}
