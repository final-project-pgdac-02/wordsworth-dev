package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.UpdateBookDetailsDto;
import com.app.dto.UpdateUserDetailsDto;
import com.app.pojos.Book;
import com.app.service.IBookService;
import com.app.service.IFeedbackService;
import com.app.service.IUserService;

@RequestMapping("/admin")
@RestController
@CrossOrigin(origins = "http://localhost:3000/")
public class AdminController {

	@Autowired
	IBookService bookService;

	@Autowired
	IUserService userService;

	@Autowired
	IFeedbackService feedbackService;

	@PostMapping("/addNewBook")
	public ResponseEntity<?> addNewBook(@RequestBody Book book) {
		return ResponseEntity.ok(bookService.addBook(book));
	}

	@GetMapping("/allUsers")
	public ResponseEntity<?> getListOfAllUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}

	@DeleteMapping("/deleteUser/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable Integer userId) {
		return ResponseEntity.ok(userService.deleteAUser(userId));
	}

	@PutMapping("/updateUserDetails/{id}")
	public String updateUserProfile(@PathVariable Integer id, @RequestBody UpdateUserDetailsDto userDto) {
		return userService.updateUserDetails(id, userDto.getFirstName(), userDto.getLastName(), userDto.getPhone());
	}

	@PutMapping("/{id}/updateBookStock/{stock}")
	public ResponseEntity<?> updateStock(@PathVariable Integer id, @PathVariable int stock) {
		return ResponseEntity.ok(bookService.updateBookStock(id, stock));
	}

	@GetMapping("/getAllBooks")
	public ResponseEntity<?> getAllBooks() {
		return ResponseEntity.ok(bookService.getAllBooks());
	}

	@PutMapping("/updateBookDetails/{id}")
	public String updateBookDetails(@PathVariable Integer id, @RequestBody UpdateBookDetailsDto bookDto) {
		return bookService.updateBookDetails(id, bookDto.getPrice(), bookDto.getPublication(), bookDto.getIsbn(),
				bookDto.getBookCover());
	}
}
