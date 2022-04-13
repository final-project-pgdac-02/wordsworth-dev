package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.app.pojos.Membership;
import com.app.service.IMembershipService;

@RestController
@RequestMapping("/memberships")
@CrossOrigin(origins = "http://localhost:3000/")
public class MembershipController {
	
	@Autowired
	IMembershipService membershipService;

	@GetMapping("/")
	public ResponseEntity<?> getAllMembersips() {
		return new ResponseEntity<>(membershipService.getAllMemberships(),HttpStatus.OK); 
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getMembershipById(@PathVariable int id) {
		return new ResponseEntity<>(membershipService.getMembershipByMembershipId(id),HttpStatus.OK); 
	}
	
	@PutMapping("/status")
	public ResponseEntity<?> setDiscountStatus(@RequestBody Membership membership) {
		return new ResponseEntity<>(membershipService.updateDiscountStatus(membership.getId(), membership.isDiscountIsActive()),HttpStatus.OK); 
	}
	
	@PutMapping("/percent")
	public ResponseEntity<?> setDiscountPercent(@RequestBody Membership membership) {
		return new ResponseEntity<>(membershipService.updateMembershipDiscount(membership.getId(), membership.getDiscount()),HttpStatus.OK); 
	}
	
	@PutMapping("/cost")
	public ResponseEntity<?> setMembershipCost(@RequestBody Membership membership) {
		return new ResponseEntity<>(membershipService.updateMembershipCost(membership.getId(), membership.getMembershipCost()),HttpStatus.OK); 
	}

}
