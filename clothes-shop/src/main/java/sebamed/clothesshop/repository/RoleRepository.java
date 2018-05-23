package sebamed.clothesshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sebamed.clothesshop.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findOne(Long id);

}
