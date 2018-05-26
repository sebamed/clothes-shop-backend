package sebamed.clothesshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sebamed.clothesshop.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	Product findOneById(Long id);
	
	List<Product> findAll();
	
}
