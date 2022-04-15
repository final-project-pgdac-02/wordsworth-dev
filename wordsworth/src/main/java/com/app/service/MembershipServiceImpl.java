package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dao.MembershipRepository;
import com.app.dao.UserRepository;
import com.app.pojos.Membership;
import com.app.pojos.User;

@Service
@Transactional
public class MembershipServiceImpl implements IMembershipService {

	@Autowired
	private MembershipRepository membershipRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public List<Membership> getAllMemberships() {

		return membershipRepo.findAll();
	}

	@Override
	public Membership getMembershipByMembershipId(Integer membershipId) {

		return membershipRepo.findById(membershipId)
				.orElseThrow(() -> new ResourceNotFoundException("invalid membership id!!!"));
	}

	@Override
	public String updateMembershipDiscount(Integer membershipId, double discountPercent) {

		Membership updateMembershipDiscount = membershipRepo.findById(membershipId)
				.orElseThrow(() -> new ResourceNotFoundException("invalid membership id!!!"));

		updateMembershipDiscount.setDiscount(discountPercent);
		return "Discount % updated for membership type : " + updateMembershipDiscount.getMembershipType() + " to: "
				+ discountPercent;
	}

	@Override
	public String updateDiscountStatus(Integer membershipId, boolean status) {

		Membership updateMembershipDiscountStatus = membershipRepo.findById(membershipId)
				.orElseThrow(() -> new ResourceNotFoundException("invalid membership id!!!"));

		updateMembershipDiscountStatus.setDiscountIsActive(status);
		return "updated discount status for membership type : " + updateMembershipDiscountStatus.getMembershipType()
				+ " to : " + updateMembershipDiscountStatus.isDiscountIsActive();
	}

	@Override
	public String updateMembershipCost(Integer membershipId, double membershipCost) {
		Membership updateMembershipDiscount = membershipRepo.findById(membershipId)
				.orElseThrow(() -> new ResourceNotFoundException("invalid membership id!!!"));

		updateMembershipDiscount.setMembershipCost(membershipCost);
		return "Membership cost updated for membership type : " + updateMembershipDiscount.getMembershipType()
				+ " to:  " + membershipCost;
	}

	@Override
	public Membership getMemberhsipByUserId(Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("User with user id: " + userId + " not found in database!!"));
		return user.getMembership();
	}

}
