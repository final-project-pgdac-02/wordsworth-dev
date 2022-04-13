package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

	@Column(name = "first_name", length = 50, nullable = false)
	@NotBlank
	private String firstName;

	@Column(name = "last_name", length = 50, nullable = false)
	@NotBlank
	private String lastName;

	@Email
	@Column(length = 50, unique = true, nullable = false)
	@NotBlank
	private String email;

	@Column(length = 50, nullable = false)
	@NotBlank
	private String password;

	@Column(length = 10)
	@NotBlank
	private String phone;

	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(nullable = false, /* length = 20 */ columnDefinition = "varchar(20) default 'CUSTOMER'")
	private Role role;

	// Unidirectional Many to One, User(*----->1)Membership
	@ManyToOne
	@JoinColumn(name = "membership_id")
	private Membership membership;

//	@ManyToMany(cascade = {
//		    CascadeType.PERSIST,
//		    CascadeType.MERGE
//		})
//	@JoinTable(name="user_cards" , joinColumns = @JoinColumn(name="user_id"), inverseJoinColumns = @JoinColumn(name="card_id"))
//	private Set<Card> cards=new HashSet<Card>();

//	@ElementCollection
//	@CollectionTable(name = "cart_items", joinColumns = @JoinColumn(name = "user_id"))
//	//@JsonIgnore //commented JsonIgnore as returning LoginResponse DTO by login user method from UserServiceImpl
//	private List<CartItem> cartItems = new ArrayList<CartItem>();

	// HELPER METHODS-------------------------

	public User(String firstName, String lastName, String email, String password, String phone, Role role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.role = role;
	}
	
	

//	public void addCartItem(CartItem c) {
//		cartItems.add(c);
//	}
//
//	public void removeCartItem(CartItem c) {
//		cartItems.remove(c);
//	}

//
//	public void addCard(Card c) {
//		cards.add(c);
//	}
//	
//	public void removeCard(Card c) {
//		cards.remove(c);
//	}

//	---------------------------------------

//	public List<CartItem> getCartItems() {
//		return cartItems;
//	}
//
//	public void setCartItems(List<CartItem> cartItems) {
//		this.cartItems = cartItems;
//	}

//	public Set<Card> getCards() {
//		return cards;
//	}
//
//	public void setCards(Set<Card> cards) {
//		this.cards = cards;
//	}

	public User() {
		super();
	}

	public Membership getMembership() {
		return membership;
	}

	public void setMembership(Membership membership) {
		this.membership = membership;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password=" + password
				+ ", phone=" + phone + ", role=" + role + ", membership=" + membership + "]";
	}

}
