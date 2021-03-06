package com.app.service;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dao.AddressRepository;
import com.app.dao.BookRepository;
import com.app.dao.CardRepository;
import com.app.dao.CartItemRepository;
import com.app.dao.MembershipRepository;
import com.app.dao.OrderRepository;
import com.app.dao.OrderdetailsRepository;
import com.app.dao.UserRepository;
import com.app.dto.LoginResponse;
import com.app.dto.UserCartDto;
import com.app.pojos.Book;
import com.app.pojos.CartItem;
import com.app.pojos.Membership;
import com.app.pojos.MembershipType;
import com.app.pojos.User;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private MembershipRepository membershipRepo;

	@Autowired
	private BookRepository bookRepo;

	@Autowired
	private CartItemRepository cartRepo;

	@Autowired
	private CardRepository cardRepo;

	@Autowired
	private AddressRepository addressRepo;

	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private OrderdetailsRepository orderDetailsRepo;

	@Override
	public LoginResponse loginUser(String email, String password) {
		User user = userRepo.findByEmailAndPassword(email, password)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Credentials!!!"));
		return new LoginResponse(user.getEmail(), user.getFirstName(), user.getLastName(), user.getRole(),
				user.getId());
	}

	@Override
	public String registerUser(User user) {
		Membership membership = membershipRepo.findByMembershipType(MembershipType.valueOf("REGULAR"))
				.orElseThrow(() -> new ResourceNotFoundException("This membership does not exist!"));
		user.setMembership(membership);
		userRepo.save(user);

		return "user registered successfully : " + user.getEmail();

	}

	@Override
	public String changePassword(Integer userId, String oldPassword, String newPassword) {
		User changedUserPassword = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user does not exists with this id!!!"));

		if (changedUserPassword.getPassword().equals(oldPassword)) {
			changedUserPassword.setPassword(newPassword);
			return "password changed successfully!!!";
		}
		return "old password does not matches!!!";

	}

	@Override
	public String updateMembership(Integer userId, Integer membershipId) {

		User updateUserMembership = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user does not exists with this id!!!"));

		Membership updatedMembership = membershipRepo.findById(membershipId)
				.orElseThrow(() -> new ResourceNotFoundException("invalid membership id!!!"));

		updateUserMembership.setMembership(updatedMembership);

		return "membership updated successfully for user : " + updateUserMembership.getEmail();

	}

	@Override
	public List<UserCartDto> getUserCart(Integer userId) {
		List<UserCartDto> userCart = new ArrayList<>();
		List<CartItem> cartItems = cartRepo.findByUserId(userId);
		for (CartItem c : cartItems) {
			Book book = bookRepo.findById(c.getBook().getId())
					.orElseThrow(() -> new ResourceNotFoundException("Couldn't find book by ID!"));
			userCart.add(new UserCartDto(book.getId(), userId, c.getId(), c.getQuantity(), book.getBookCover(),
					book.getPrice(), book.getBookTitle()));
		}
		return userCart;
	}

	@Override
	public double getUserDiscount(Integer userId) {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User by given userId not found in database"));
		return user.getMembership().getDiscount();
	}

	@Override
	public User getUserByUserId(Integer userId) {
		return userRepo.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("User with userId: " + userId + " not found in database"));
	}

	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public String deleteAUser(Integer userId) {
		orderDetailsRepo.deleteOrderDetailByUserId(userId);
		orderRepo.deleteOrderByUserId(userId);
		addressRepo.deleteAddressByUserId(userId);
		cardRepo.deleteCardByUserId(userId);
		cartRepo.deleteCartItemsByUserId(userId);
		userRepo.deleteById(userId);
		return "user deleted with id : " + userId;
	}

	@Override
	public String updateUserDetails(Integer userId, String fName, String lName, String phgoneNumber) {
		User temp = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User ID is Invalid!!"));
		temp.setFirstName(fName);
		temp.setLastName(lName);
		temp.setPhone(phgoneNumber);
		return "User Profile Updated Succefully!!!";
	}

}
