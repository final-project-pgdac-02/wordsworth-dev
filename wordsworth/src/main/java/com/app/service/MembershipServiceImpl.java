package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dao.MembershipRepository;
import com.app.pojos.Membership;

@Service
@Transactional
public class MembershipServiceImpl implements IMembershipService {

	@Autowired
	private MembershipRepository membershipRepo;

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
		return "discount percentage updated for membership type : " + updateMembershipDiscount.getMembershipType();
	}

	@Override
	public String updateDiscountStatus(Integer membershipId) {

		Membership updateMembershipDiscountStatus = membershipRepo.findById(membershipId)
				.orElseThrow(() -> new ResourceNotFoundException("invalid membership id!!!"));

		if (updateMembershipDiscountStatus.isDiscountIsActive()) {
			updateMembershipDiscountStatus.setDiscountIsActive(false);
		} else {
			updateMembershipDiscountStatus.setDiscountIsActive(true);
		}
		return "updated discount status for membership type : " + updateMembershipDiscountStatus.getMembershipType()
				+ " to : " + updateMembershipDiscountStatus.isDiscountIsActive();
	}

}
