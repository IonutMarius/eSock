package ro.esock.model.domain.object;

import java.util.ArrayList;
import java.util.List;

import ro.esock.model.persistance.entitiy.OrderEntity;
import ro.esock.model.persistance.entitiy.UserEntity;

public class User {

	private UserProfile userProfile;
	private List<Order> orders;
	private String username;
	private String password;

	public User() {
		this.orders = new ArrayList<>();
	}

	public User(UserEntity userEntity) {
		this.orders = new ArrayList<>();
		if (userEntity != null) {
			this.userProfile = new UserProfile(userEntity.getUserProfile());
			List<OrderEntity> orderEntities = userEntity.getOrders();
			if (orderEntities != null && orderEntities.size() > 0) {
				for (OrderEntity orderEntity : orderEntities) {
					this.orders.add(new Order(orderEntity));
				}
			}
		}
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orders == null) ? 0 : orders.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((userProfile == null) ? 0 : userProfile.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (orders == null) {
			if (other.orders != null)
				return false;
		} else if (!orders.equals(other.orders))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userProfile == null) {
			if (other.userProfile != null)
				return false;
		} else if (!userProfile.equals(other.userProfile))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}
