package ro.esock.ws.util;

import java.util.ArrayList;

import ro.esock.model.dto.AddressDTO;
import ro.esock.model.dto.UserDTO;
import ro.esock.model.dto.UserProfileDTO;
import ro.esock.ws.soap.user.AddressXml;
import ro.esock.ws.soap.user.UserProfileXml;
import ro.esock.ws.soap.user.UserXml;

public class ConverterUtils {

	// XML to Entity
	public static UserDTO convertUserDTOXmlToUser(UserXml userXml) {
		UserDTO user = new UserDTO();
		user.setUsername(userXml.getUsername());
		user.setPassword(userXml.getPassword());
		UserProfileXml userProfileXml = userXml.getUserProfile();
		UserProfileDTO userProfile = convertUserProfileXmlToUserProfileDTO(userProfileXml);
		user.setUserProfile(userProfile);

		return user;
	}

	public static UserProfileDTO convertUserProfileXmlToUserProfileDTO(UserProfileXml userProfileXml) {
		UserProfileDTO userProfile = new UserProfileDTO();
		userProfile.setName(userProfileXml.getName());
		userProfile.setSurname(userProfileXml.getSurname());
		userProfile.setPhoneNumber(userProfileXml.getPhoneNumber());
		userProfile.setEmailAddress(userProfileXml.getEmailAddress());
		userProfile.setAddresses(new ArrayList<>());
		for (AddressXml addressXml : userProfileXml.getAddresses()) {
			AddressDTO address = convertAddressXmlToAddressDTO(addressXml);
			address.setUserProfile(userProfile);
			userProfile.getAddresses().add(address);
		}

		return userProfile;
	}

	public static AddressDTO convertAddressXmlToAddressDTO(AddressXml addressXml) {
		AddressDTO address = new AddressDTO();
		address.setAddressName(addressXml.getAddressName());
		address.setCity(addressXml.getCity());
		address.setPostcode(addressXml.getPostcode());
		address.setAddressLine1(addressXml.getAddressLine1());
		address.setAddressLine2(addressXml.getAddressLine2());

		return address;
	}

	// Entity to XML
	public static UserXml convertUserDTOToUserXml(UserDTO user) {
		UserXml userXml = new UserXml();
		userXml.setUsername(user.getUsername());
		userXml.setPassword(user.getPassword());
		UserProfileDTO userProfile = user.getUserProfile();
		UserProfileXml userProfileXml = convertUserProfileDTOToUserProfileXml(userProfile);
		userXml.setUserProfile(userProfileXml);

		return userXml;
	}

	public static UserProfileXml convertUserProfileDTOToUserProfileXml(UserProfileDTO userProfile) {
		UserProfileXml userProfileXml = new UserProfileXml();
		userProfileXml.setName(userProfile.getName());
		userProfileXml.setSurname(userProfile.getSurname());
		userProfileXml.setPhoneNumber(userProfile.getPhoneNumber());
		userProfileXml.setEmailAddress(userProfile.getEmailAddress());
		for (AddressDTO address : userProfile.getAddresses()) {
			AddressXml addressXml = convertAddressDTOToAddressXml(address);
			userProfileXml.getAddresses().add(addressXml);
		}
		
		return userProfileXml;
	}

	public static AddressXml convertAddressDTOToAddressXml(AddressDTO address) {
		AddressXml addressXml = new AddressXml();
		addressXml.setAddressName(address.getAddressName());
		addressXml.setCity(address.getCity());
		addressXml.setPostcode(address.getPostcode());
		addressXml.setAddressLine1(address.getAddressLine1());
		addressXml.setAddressLine2(address.getAddressLine2());

		return addressXml;
	}
}
