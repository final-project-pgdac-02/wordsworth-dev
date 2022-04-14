package com.app.dto;

public class UpgradeMemberShipDto {
	
	private Integer userId;
	private Integer membershipId;
	
	public UpgradeMemberShipDto() {
		super();
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getMembershipId() {
		return membershipId;
	}
	public void setMembershipId(Integer membershipId) {
		this.membershipId = membershipId;
	}
	
	
}
