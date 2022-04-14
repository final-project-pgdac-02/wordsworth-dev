package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "memberships")
public class Membership extends BaseEntity {

	@Column(name = "membership_type", unique = true, nullable = false, length = 35)
	@Enumerated(EnumType.STRING)
	@NotNull
	private MembershipType membershipType;

	@Column(precision = 2)
	private double discount;

	@Column(name = "discount_is_active", columnDefinition = "boolean default false")
	private boolean discountIsActive;

	@Column(precision = 2, name = "membership_cost")
	private double membershipCost;

	public Membership() {
		super();
	}

	public double getMembershipCost() {
		return membershipCost;
	}

	public void setMembershipCost(double membershipCost) {
		this.membershipCost = membershipCost;
	}

	public MembershipType getMembershipType() {
		return membershipType;
	}

	public void setMembershipType(MembershipType membershipType) {
		this.membershipType = membershipType;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public boolean isDiscountIsActive() {
		return discountIsActive;
	}

	public void setDiscountIsActive(boolean discountIsActive) {
		this.discountIsActive = discountIsActive;
	}

	@Override
	public String toString() {
		return "Membership [membershipType=" + membershipType + ", discount=" + discount + ", discountIsActive="
				+ discountIsActive + "]";
	}

}
