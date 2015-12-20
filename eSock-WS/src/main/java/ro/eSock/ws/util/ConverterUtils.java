package ro.esock.ws.util;

import java.util.ArrayList;

import ro.esock.model.entitiy.Address;
import ro.esock.model.entitiy.User;
import ro.esock.model.entitiy.UserProfile;
import ro.esock.ws.soap.user.AddressXml;
import ro.esock.ws.soap.user.UserProfileXml;
import ro.esock.ws.soap.user.UserXml;

public class ConverterUtils {

	// XML to Entity
	public static User convertUserXmlToUser(UserXml userXml) {
		User user = new User();
		user.setUsername(userXml.getUsername());
		user.setPassword(userXml.getPassword());
		UserProfileXml userProfileXml = userXml.getUserProfile();
		UserProfile userProfile = convertUserProfileXmlToUserProfile(userProfileXml);
		user.setUserProfile(userProfile);

		return user;
	}

	public static UserProfile convertUserProfileXmlToUserProfile(UserProfileXml userProfileXml) {
		UserProfile userProfile = new UserProfile();
		userProfile.setName(userProfileXml.getName());
		userProfile.setSurname(userProfileXml.getSurname());
		userProfile.setPhoneNumber(userProfileXml.getPhoneNumber());
		userProfile.setEmailAddress(userProfileXml.getEmailAddress());
		userProfile.setAddresses(new ArrayList<>());
		for (AddressXml addressXml : userProfileXml.getAddresses()) {
			Address address = convertAddressXmlToAddress(addressXml);
			address.setUserProfile(userProfile);
			userProfile.getAddresses().add(address);
		}

		return userProfile;
	}

	public static Address convertAddressXmlToAddress(AddressXml addressXml) {
		Address address = new Address();
		address.setAddressName(addressXml.getAddressName());
		address.setCity(addressXml.getCity());
		address.setPostcode(addressXml.getPostcode());
		address.setAddressLine1(addressXml.getAddressLine1());
		address.setAddressLine2(addressXml.getAddressLine2());

		return address;
	}

	// Entity to XML
	public static UserXml convertUserToUserXml(User user) {
		UserXml userXml = new UserXml();
		userXml.setUsername(user.getUsername());
		userXml.setPassword(user.getPassword());
		UserProfile userProfile = user.getUserProfile();
		UserProfileXml userProfileXml = convertUserProfileToUserProfileXml(userProfile);
		userXml.setUserProfile(userProfileXml);

		return userXml;
	}

	public static UserProfileXml convertUserProfileToUserProfileXml(UserProfile userProfile) {
		UserProfileXml userProfileXml = new UserProfileXml();
		userProfileXml.setName(userProfile.getName());
		userProfileXml.setSurname(userProfile.getSurname());
		userProfileXml.setPhoneNumber(userProfile.getPhoneNumber());
		userProfileXml.setEmailAddress(userProfile.getEmailAddress());
		for (Address address : userProfile.getAddresses()) {
			AddressXml addressXml = convertAddressToAddressXml(address);
			userProfileXml.getAddresses().add(addressXml);
		}
		
		return userProfileXml;
	}

	public static AddressXml convertAddressToAddressXml(Address address) {
		AddressXml addressXml = new AddressXml();
		addressXml.setAddressName(address.getAddressName());
		addressXml.setCity(address.getCity());
		addressXml.setPostcode(address.getPostcode());
		addressXml.setAddressLine1(address.getAddressLine1());
		addressXml.setAddressLine2(address.getAddressLine2());

		return addressXml;
	}
}
