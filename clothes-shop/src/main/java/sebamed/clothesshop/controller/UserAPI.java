package sebamed.clothesshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sebamed.clothesshop.domain.User;
import sebamed.clothesshop.dto.UserDTO;
import sebamed.clothesshop.dto.UserLoginDTO;
import sebamed.clothesshop.dto.UserRegisterDTO;
import sebamed.clothesshop.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("http://localhost:4200")
public class UserAPI {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<UserDTO> handleLogin(@RequestBody UserLoginDTO loginDto) {
		User user = this.userService.login(loginDto);
		if(user != null) {
			return new ResponseEntity<>(new UserDTO(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getPassword(), user.getEmail(), user.getRole()), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserDTO> handleRegistration(@RequestBody UserRegisterDTO registerDto){
		User user = this.userService.register(registerDto);
		if(user != null) {
			return new ResponseEntity<>(new UserDTO(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getPassword(), user.getEmail(), user.getRole()), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
