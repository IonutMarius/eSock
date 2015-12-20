package ro.esock.ws.util;

import ro.esock.model.entitiy.Address;
import ro.esock.model.entitiy.User;
import ro.esock.model.entitiy.UserProfile;
import ro.esock.ws.soap.user.AddressXml;
import ro.esock.ws.soap.user.UserProfileXml;
import ro.esock.ws.soap.user.UserXml;

public class ConverterUtils {

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
		for (AddressXml addressXml : userProfileXml.getAddresses()) {
			Address address = convertAddressXmlToAddress(addressXml);
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
}
