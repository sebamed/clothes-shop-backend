package sebamed.clothesshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sebamed.clothesshop.domain.Order;
import sebamed.clothesshop.domain.User;
import sebamed.clothesshop.repository.OrderRepository;
import sebamed.clothesshop.repository.UserRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public Order findOneById(Long id) {
		return this.orderRepository.findOneById(id);
	}
	
	public Order save(Order order) {
		return this.orderRepository.save(order);
	}
	
	public List<Order> findAll(){
		return this.orderRepository.findAll();
	}
	
	public void remove(Long id) {
		Order o = this.orderRepository.findOneById(id);
		if(o != null) {
			for(User u : this.userRepository.findAll()) {
//				if(u.getOrder().equals(o)) {
//					u.setOrder(null);
//				}
			}
			this.orderRepository.delete(o);
		}
	}
	
}
