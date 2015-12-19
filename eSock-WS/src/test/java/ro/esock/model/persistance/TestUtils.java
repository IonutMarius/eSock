package ro.esock.model.persistance;

import java.util.ArrayList;

import ro.esock.model.entitiy.Address;
import ro.esock.model.entitiy.Order;
import ro.esock.model.entitiy.Product;
import ro.esock.model.entitiy.Purchase;
import ro.esock.model.entitiy.User;
import ro.esock.model.entitiy.UserProfile;

public class TestUtils {

	// User
	private static final String DEFAULT_USERNAME = "username";
	private static final String DEFAULT_PASSWORD = "password";
	// UserProfile
	private static final String DEFAULT_NAME = "name";
	private static final String DEFAULT_SURNAME = "surname";
	private static final String DEFAULT_PHONE_NUMBER = "0123456789";
	private static final String DEFAULT_EMAIL_ADDRESS = "ceva@email.com";
	// Address
	private static final String DEFAULT_ADDRESS_NAME = "addr name";
	private static final String DEFAULT_CITY = "city";
	private static final String DEFAULT_POSTCODE = "postcode";
	private static final String DEFAULT_ADDRESS_LINE_1 = "addr line 1";
	private static final String DEFAULT_ADDRESS_LINE_2 = "addr line 2";
	// Purchase
	private static final Integer DEFAULT_QUANTITY = new Integer(23);
	// Product
	private static final String DEFAULT_PRODUCT_NAME = "prod name";
	private static final String DEFAULT_DESCRIPTION = "desc";
	private static final Double DEFAULT_PRICE = new Double(21.542);
	private static final Integer DEFAULT_STOCK = new Integer(543);

	public static User createUser(String sufix) {
		User user = new User();
		user.setUsername(DEFAULT_USERNAME + sufix);
		user.setPassword(DEFAULT_PASSWORD + sufix);
		user.setUserProfile(createUserProfile(sufix));

		return user;
	}

	public static UserProfile createUserProfile(String sufix) {
		UserProfile userProfile = new UserProfile();
		userProfile.setName(DEFAULT_NAME + sufix);
		userProfile.setSurname(DEFAULT_SURNAME + sufix);
		userProfile.setPhoneNumber(DEFAULT_PHONE_NUMBER + sufix);
		userProfile.setEmailAddress(DEFAULT_EMAIL_ADDRESS + sufix);

		Address address = createAddress(sufix);
		address.setUserProfile(userProfile);
		Address address2 = createAddress(sufix + "_2");
		address2.setUserProfile(userProfile);

		userProfile.setAddresses(new ArrayList<>());
		userProfile.getAddresses().add(address);
		userProfile.getAddresses().add(address2);

		return userProfile;
	}

	/**
	 * Must attach a UserProfile before persisting
	 */
	public static Address createAddress(String sufix) {
		Address address = new Address();
		address.setAddressName(DEFAULT_ADDRESS_NAME + sufix);
		address.setCity(DEFAULT_CITY + sufix);
		address.setPostcode(DEFAULT_POSTCODE + sufix);
		address.setAddressLine1(DEFAULT_ADDRESS_LINE_1 + sufix);
		address.setAddressLine2(DEFAULT_ADDRESS_LINE_2 + sufix);

		return address;
	}

	/**
	 * Must attach an Address, an User and one Product to each Purchase (2)
	 * before persisting
	 */
	public static Order createOrder() {
		Order order = new Order();
		Purchase purchase1 = createPurchase();
		purchase1.setOrder(order);
		Purchase purchase2 = createPurchase();
		purchase2.setOrder(order);

		order.setPurchases(new ArrayList<>());
		order.getPurchases().add(purchase1);
		order.getPurchases().add(purchase2);

		return order;
	}

	/**
	 * Must attach an User, a Product and an Order before persisting
	 */
	public static Purchase createPurchase() {
		Purchase purchase = new Purchase();
		purchase.setQuantity(DEFAULT_QUANTITY);

		return purchase;
	}

	public static Product createProduct(String sufix) {
		Product product = new Product();
		product.setName(DEFAULT_PRODUCT_NAME + sufix);
		product.setDescription(DEFAULT_DESCRIPTION + sufix);
		product.setPrice(DEFAULT_PRICE);
		product.setStock(DEFAULT_STOCK);

		return product;
	}
}
