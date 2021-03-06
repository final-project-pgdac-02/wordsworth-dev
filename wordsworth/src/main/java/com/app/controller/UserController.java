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

import com.app.dto.CartItemDto;
import com.app.dto.LoginRequest;
import com.app.dto.UpdatedUserDto;
import com.app.dto.UpgradeMemberShipDto;
import com.app.pojos.Address;
import com.app.pojos.Card;
import com.app.pojos.User;
import com.app.service.IAddressService;
import com.app.service.ICardService;
import com.app.service.ICartItemService;
import com.app.service.IMembershipService;
import com.app.service.IOrderService;
import com.app.service.IUserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000/")
public class UserController {

	@Autowired
	private IUserService userService;

	@Autowired
	private ICartItemService cartItemServ;

	@Autowired
	private IAddressService addressService;

	@Autowired
	private ICardService cardService;

	@Autowired
	private IOrderService orderService;

	@Autowired
	private IMembershipService membershipService;

	@PostMapping("/login")
	public ResponseEntity<?> processLoginForm(@RequestBody LoginRequest request) {
		System.out.println(request.getEmail() + " " + request.getPassword());
		return ResponseEntity.ok(userService.loginUser(request.getEmail(), request.getPassword()));
	}

	@PostMapping("/registration")
	public ResponseEntity<?> processRegistrationForm(@RequestBody User user) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(user));

	}

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

	@PutMapping("/upgrademembership")
	public String upgradeMembership(@RequestBody UpgradeMemberShipDto upgradeDto) {
		return userService.updateMembership(upgradeDto.getUserId(), upgradeDto.getMembershipId());
	}

	@PostMapping("/addtocart")
	public String saveItemToCart(@RequestBody CartItemDto cartItem) {
		return cartItemServ.saveToCart(cartItem.getUserId(), cartItem.getBookId());
	}

	@PostMapping("/addanaddress/{userId}")
	public String addAnAddress(@PathVariable Integer userId, @RequestBody Address addedAddress) {
		return addressService.addAddress(userId, addedAddress);
	}

	@PostMapping("/addacard/{userId}")
	public String addACard(@PathVariable Integer userId, @RequestBody Card card) {
		return cardService.addCard(userId, card);
	}

	@GetMapping("/getuserdiscount/{userId}")
	public double getDiscount(@PathVariable Integer userId) {
		return userService.getUserDiscount(userId);
	}

	@GetMapping("/getcards/{userId}")
	public ResponseEntity<?> getUserCards(@PathVariable Integer userId) {
		return new ResponseEntity<>(cardService.getCardsByUserId(userId), HttpStatus.OK);
	}

	@GetMapping("/getaddresses/{userId}")
	public ResponseEntity<?> getUserAddresses(@PathVariable Integer userId) {
		return new ResponseEntity<>(addressService.getAddressListByUserId(userId), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getAllOrdersByUserId(@PathVariable Integer id) {
		return ResponseEntity.ok(orderService.getOrdersByUserId(id));
	}

	@GetMapping("/membership/{userId}")
	public ResponseEntity<?> getMembershipByUserId(@PathVariable Integer userId) {
		return ResponseEntity.ok(membershipService.getMemberhsipByUserId(userId));
	}
}
