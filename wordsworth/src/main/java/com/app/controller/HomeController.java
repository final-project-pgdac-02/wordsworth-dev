package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.IBookService;
import com.app.service.IOrderService;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:3000/")
public class HomeController {

	@Autowired
	IBookService bookServ;
	
	
	@Autowired
	IOrderService orderServ;
	
	@GetMapping("/")
	public ResponseEntity<?> getAllBooks() {
//		return bookServ.getAllBooks().toString();
//		return new ResponseEntity<>(bookServ.getAllBooks(),HttpStatus.OK);
			return new ResponseEntity<>(bookServ.getAllBooks(),HttpStatus.OK);
	}
	
	
//	@GetMapping("/test/{id}")
//	public ResponseEntity<?> getSomething(@PathVariable Integer id) {
//		return new ResponseEntity<>(bookServ.getFeedbackByBookId(id),HttpStatus.OK); 
//	}
	
//	@GetMapping("/{id}")
//	public ResponseEntity<?> getBookById(@PathVariable int id){
//		return new ResponseEntity<>(bookServ.getBookById(id),HttpStatus.OK);
//	}
//	
//	@GetMapping("/name/{name}/{category}")
//	public ResponseEntity<?> getBookByName(@PathVariable String name,@PathVariable String category){
//		return new ResponseEntity<>(name+category,HttpStatus.OK);
//	}
}
