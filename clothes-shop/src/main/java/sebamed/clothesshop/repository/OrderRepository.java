package sebamed.clothesshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sebamed.clothesshop.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	
	Order findOneById(Long id);
	
	List<Order> findAll();

}
