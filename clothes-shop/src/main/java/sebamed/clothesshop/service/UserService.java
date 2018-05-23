package sebamed.clothesshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sebamed.clothesshop.domain.User;
import sebamed.clothesshop.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public User findOne(Long id) {
		return this.userRepository.findOne(id);
	}
	
	public List<User> findAll(){
		return this.userRepository.findAll();
	}
	
	public void remove(Long id) {
		User user = this.userRepository.findOne(id);
		if(user != null) {
			this.userRepository.delete(user);
		}
	}
	
	
	
}
