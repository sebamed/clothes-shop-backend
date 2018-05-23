package sebamed.clothesshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sebamed.clothesshop.domain.User;
import sebamed.clothesshop.dto.UserLoginDTO;
import sebamed.clothesshop.dto.UserRegisterDTO;
import sebamed.clothesshop.repository.RoleRepository;
import sebamed.clothesshop.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	public User findOne(Long id) {
		return this.userRepository.findOneById(id);
	}
	
	public List<User> findAll(){
		return this.userRepository.findAll();
	}
	
	public void remove(Long id) {
		User user = this.userRepository.findOneById(id);
		if(user != null) {
			this.userRepository.delete(user);
		}
	}
	
	public User save(User user) {
		return this.userRepository.save(user);
	}
	
	public boolean exists(UserLoginDTO loginDto) {
		User u = this.userRepository.findOneByUsername(loginDto.getUsername());
		if(u != null) {
			return true;
		}
		return false;
	}
	
	public boolean exists(UserRegisterDTO registerDTO) {
		// username check
		User u = this.userRepository.findOneByUsername(registerDTO.getUsername());
		if(u != null) {
			return true;
		}
		
		// email check
		User u2 = this.userRepository.findOneByEmail(registerDTO.getEmail());
		if(u2 != null) {
			return true;
		}
		
		return false;
		
	}
	
	public User login(UserLoginDTO loginDto) {
		if(this.exists(loginDto)) {
			User u = this.userRepository.findOneByUsername(loginDto.getUsername());
			if(u.getPassword().equals(loginDto.getPassword())) {
				return u;
			}
		}
		return null;
	}
	
	public User register(UserRegisterDTO registerDTO) {
		if(this.exists(registerDTO)) {
			return null;
		} else {
			User user = new User();
			user.setEmail(registerDTO.getEmail());
			user.setUsername(registerDTO.getUsername());
			user.setFirstName(registerDTO.getFirstName());
			user.setLastName(registerDTO.getLastName());
			user.setPassword(registerDTO.getPassword());
			user.setRole(this.roleRepository.findOneByName("user"));
			return this.userRepository.save(user);
		}
	}
	
	
	
}
