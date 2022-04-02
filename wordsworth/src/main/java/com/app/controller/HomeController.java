package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.IBookService;

@RestController
@RequestMapping("/books")
public class HomeController {

	@Autowired
	IBookService bookServ;
	
	@GetMapping(value = {"/", "/{id}"})
	public ResponseEntity<?> getAllBooks(@PathVariable(required = false) Integer id) {
//		return bookServ.getAllBooks().toString();
//		return new ResponseEntity<>(bookServ.getAllBooks(),HttpStatus.OK);
		if(id!=null) {
			return new ResponseEntity<>(bookServ.getBookById(id),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(bookServ.getAllBooks(),HttpStatus.OK);
		}
	}
	
	
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
