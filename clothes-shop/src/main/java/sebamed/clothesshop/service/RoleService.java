package sebamed.clothesshop.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sebamed.clothesshop.domain.Role;
import sebamed.clothesshop.domain.User;
import sebamed.clothesshop.repository.RoleRepository;
import sebamed.clothesshop.repository.UserRepository;

@Service
public class RoleService {
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public Role findOne(Long id) {
		return this.roleRepository.findOneById(id);
	}
	
	@Transactional
	public Role save(Role role) {
		return this.roleRepository.save(role);
	}
	
	public void remove(Long id) {
		Role role = this.roleRepository.findOneById(id);
		if(role != null) {
			for(User user : this.userRepository.findAll()) {
				if(user.getRole().equals(role)) {
					user.setRole(null);
				}
			}
			this.roleRepository.delete(role);
		}
	}

}
