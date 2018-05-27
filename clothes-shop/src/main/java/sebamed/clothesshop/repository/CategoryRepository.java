package sebamed.clothesshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sebamed.clothesshop.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	Category findOneById(Long id);
	
	List<Category> findAll();
	
}
