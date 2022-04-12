package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.Feedback;
import com.app.service.IBookService;
import com.app.service.IFeedbackService;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "http://localhost:3000/")
//@CrossOrigin(origins = "*")
public class BookController {
	
	@Autowired
	private IBookService bookServ;
	
	@Autowired
	private IFeedbackService feedbackService;

	@GetMapping("/{id}")
	public ResponseEntity<?> getBookById(@PathVariable int id){
		return new ResponseEntity<>(bookServ.getBookById(id),HttpStatus.OK);
	}
	
//	@GetMapping("/feedbacks/{id}")
//	public ResponseEntity<?> getSomething(@PathVariable Integer id) {
//		return new ResponseEntity<>(bookServ.getFeedbackByBookId(id),HttpStatus.OK); 
//	}
	@PostMapping("/addFeedback/{id}")
	public ResponseEntity<?> addNewFeedback(@PathVariable Integer id, @RequestBody Feedback feedback){
		return ResponseEntity.ok(feedbackService.addFeedback(id, feedback));
	}
	

	@GetMapping("/categories")
	public ResponseEntity<?> getAllCategories(){
		return ResponseEntity.ok(bookServ.getAllCategories());
	}
	
	@GetMapping("/advanced")
	public ResponseEntity<?> advancedSearchBooks(@RequestParam(name="category", required = false) String category,@RequestParam(name="rating", required = false) String rating,@RequestParam(name="min", required = false) String minPrice,@RequestParam(name="max", required = false) String maxPrice){
		return ResponseEntity.ok(bookServ.advancedFilterBooks(category, rating, minPrice, maxPrice));
	}


}
