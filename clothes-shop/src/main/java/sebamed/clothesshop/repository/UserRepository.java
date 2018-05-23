package sebamed.clothesshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sebamed.clothesshop.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findOne(Long id);
	
	List<User> findAll();
	
}
