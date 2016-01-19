package ro.esock.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.esock.model.converter.UserConverter;
import ro.esock.model.dto.AddressDTO;
import ro.esock.model.dto.OrderDTO;
import ro.esock.model.dto.ProductDTO;
import ro.esock.model.dto.PurchaseDTO;
import ro.esock.model.dto.UserDTO;
import ro.esock.model.entitiy.User;
import ro.esock.model.exception.IncorrectAddressException;
import ro.esock.model.exception.ProductOutOfStockException;
import ro.esock.model.repository.UserRepository;
import ro.esock.model.service.AddressService;
import ro.esock.model.service.ProductService;
import ro.esock.model.service.UserService;

@Service
@Transactional
public class UserServiceImpl extends GenericServiceImpl<UserDTO, User, Long> implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserConverter userConverter;
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private ProductService productService;

	@Override
	protected UserRepository getRepository() {
		return userRepository;
	}

	@Override
	protected UserConverter getEntityConverter() {
		return this.userConverter;
	}

	@Override
	public UserDTO findByUsername(String username) {
		User user = userRepository.findByUsername(username);
		return userConverter.toDto(user);
	}

	@Override
	public UserDTO findByUsernameAndPassword(String username, String password) {
		User user = userRepository.findByUsernameAndPassword(username, password);
		return userConverter.toDto(user);
	}

	@Override
	public OrderDTO addOrder(Long userId, OrderDTO orderDto) {
		UserDTO user = findById(userId);
		
		OrderDTO newOrder = new OrderDTO();
		AddressDTO address = addressService.findById(orderDto.getAddress().getAddressId());
		boolean correctAddr = false;
		for(AddressDTO addr : user.getUserProfile().getAddresses()){
			if(addr.equals(address)){
				correctAddr = true;
			}
		}
		if(!correctAddr){
			throw new IncorrectAddressException();
		}
		newOrder.setAddress(address);
		
		for(PurchaseDTO purchase : orderDto.getPurchases()){
			PurchaseDTO newPurchase = new PurchaseDTO();
			newPurchase.setProduct(productService.findById(purchase.getProduct().getProductId()));
			newPurchase.setQuantity(purchase.getQuantity());
			newPurchase.setUser(user);
			newOrder.getPurchases().add(newPurchase);
			if(newPurchase.getProduct().getStock() - purchase.getQuantity() < 0){
				throw new ProductOutOfStockException(newPurchase.getProduct());
			}
		}
		
		user.getOrders().add(newOrder);
		update(user);
		
		for(PurchaseDTO purchase : newOrder.getPurchases()){
			ProductDTO product = purchase.getProduct();
			product.setStock(product.getStock() - purchase.getQuantity());
			productService.update(product);
		}
		
		return newOrder;
	}

}
