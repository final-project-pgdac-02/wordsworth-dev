package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dao.UserRepository;
import com.app.dto.LoginRequest;
import com.app.dto.UpdatedUserDto;
//import com.app.dto.UserDto;
import com.app.pojos.User;
import com.app.service.IUserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000/")
public class UserController {

	@Autowired
	private IUserService userService;

//	@PostMapping("/login")
//	public ResponseEntity<?> processLoginForm(@RequestBody LoginRequest request) {
//		User loggedInUser = userService.loginUser(request.getEmail(), request.getPassword());
//
//		if (loggedInUser.getRole() == Role.ADMIN) {
//			return new ResponseEntity<>(loggedInUser, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(loggedInUser, HttpStatus.OK);
//		}
//	}

//	@PostMapping("/login")
//	public ResponseEntity<?> processLoginForm(@RequestBody LoginRequest request) {
//		System.out.println(request.getEmail() + " " + request.getPassword());
//		return ResponseEntity.ok(userService.loginUser(request.getEmail(), request.getPassword()));
//	}
	
	@PostMapping("/login")
	public ResponseEntity<?> processLoginForm(@RequestBody LoginRequest request) {
		System.out.println(request.getEmail() + " " + request.getPassword());
		return ResponseEntity.ok(userService.loginUser(request.getEmail(), request.getPassword()));
	}

	@PostMapping("/registration")
	public ResponseEntity<?> processRegistrationForm(@RequestBody User user) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(user));

	}

//	@GetMapping("/test/{id}")
//	public ResponseEntity<UserDto> testLogin(@PathVariable Integer id) {
//		User userObject = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found!!!"));
//		UserDto userDtoObject = new UserDto(userObject.getFirstName(), userObject.getLastName(), userObject.getEmail(),
//				userObject.getPassword());
//
//		return ResponseEntity.ok(userDtoObject);
//	}

	@PutMapping("/changepassword")
	public String changedUserPassword(@RequestBody UpdatedUserDto updatedUser) {
		System.out.println("updated password : " + updatedUser.getNewPassword());
		return userService.changePassword(updatedUser.getId(), updatedUser.getOldPassword(),
				updatedUser.getNewPassword());

	}

	@PutMapping("/updatemembership/{membershipId}")
	public String updateUserMembership(@RequestBody User updatedMembershipOfUser, @PathVariable Integer membershipId) {
		System.out.println("updated membership : " + updatedMembershipOfUser.getEmail() + " | "
				+ updatedMembershipOfUser.getMembership());

		return userService.updateMembership(updatedMembershipOfUser.getId(), membershipId);
	}

}
