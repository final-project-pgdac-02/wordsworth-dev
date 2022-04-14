package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.pojos.Membership;
import com.app.pojos.MembershipType;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Integer> {
	Optional<Membership> findByMembershipType(MembershipType type);
}
