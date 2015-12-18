package ro.esock.model.object;

import java.util.ArrayList;
import java.util.List;

import ro.esock.model.entitiy.OrderEntity;
import ro.esock.model.entitiy.PurchaseEntity;

public class Order {

	private User user;
	private Address address;
	private List<Purchase> purchases;

	public Order() {
		this.purchases = new ArrayList<>();
	}

	public Order(OrderEntity orderEntity) {
		this.purchases = new ArrayList<>();

		if (orderEntity != null) {
			this.user = new User(orderEntity.getUser());
			this.address = new Address(orderEntity.getAddress());
			List<PurchaseEntity> purchaseEntities = orderEntity.getPurchases();
			if (purchaseEntities != null && purchaseEntities.size() > 0) {
				for (PurchaseEntity purchaseEntity : purchaseEntities) {
					this.purchases.add(new Purchase(purchaseEntity));
				}
			}
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((purchases == null) ? 0 : purchases.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Order other = (Order) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (purchases == null) {
			if (other.purchases != null)
				return false;
		} else if (!purchases.equals(other.purchases))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

}
