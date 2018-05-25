package sebamed.clothesshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sebamed.clothesshop.domain.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

	Image findOneById(Long id);
	
}
