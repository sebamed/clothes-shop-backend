package sebamed.clothesshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sebamed.clothesshop.domain.Services;

@Repository
public interface ServiceRepository extends JpaRepository<Services, Long>{

	List<Services> findAll(); 
	
	Services findOneById(Long id);
	
}
