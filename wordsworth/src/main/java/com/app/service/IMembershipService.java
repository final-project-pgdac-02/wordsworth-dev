package com.app.service;

import java.util.List;

import com.app.pojos.Membership;

public interface IMembershipService {

	List<Membership> getAllMemberships();

	Membership getMembershipByMembershipId(Integer membershipId);

	String updateMembershipDiscount(Integer membershipId, double discountPercent);

	String updateDiscountStatus(Integer membershipId, boolean status);

	String updateMembershipCost(Integer membershipId, double membershipCost);

	Membership getMemberhsipByUserId(Integer userId);
}
