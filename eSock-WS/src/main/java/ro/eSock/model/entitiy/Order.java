package ro.esock.model.entitiy;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "`order`")
public class Order {

	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	@Column(name = "order_id")
	private Long orderId;

	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
	private List<Purchase> purchases = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Long getOrderId() {
		return orderId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
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
		return true;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", address=" + address + ", purchases=" + purchases
				+ "]";
	}

}
