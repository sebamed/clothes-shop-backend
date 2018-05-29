package sebamed.clothesshop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

	@PostMapping("/update-role")
	public ResponseEntity<UserDTO> handleUpdateRole(@RequestBody UserDTO user) {
		User u = this.userService.findOne(user.getId());
		if (u != null) {
			u.setRole(user.getRole());
			this.userService.save(u);
			return new ResponseEntity<UserDTO>(new UserDTO(u.getId(), u.getUsername(), u.getFirstName(),
					u.getLastName(), u.getPassword(), u.getEmail(), u.getRole()), HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/")
	public ResponseEntity<List<UserDTO>> handleGetAllUsers() {
		List<UserDTO> usersDto = new ArrayList<UserDTO>();
		for (User u : this.userService.findAll()) {
			usersDto.add(new UserDTO(u.getId(), u.getUsername(), u.getFirstName(), u.getLastName(), u.getPassword(), u.getEmail(), u.getRole()));
		}
		return new ResponseEntity<List<UserDTO>>(usersDto, HttpStatus.OK);
	}

	@PostMapping("/delete")
	public ResponseEntity<List<UserDTO>> handleDelete(@RequestBody List<UserDTO> users) {
		for (UserDTO userDto : users) {
			this.userService.remove(userDto.getId());
		}
		return new ResponseEntity<List<UserDTO>>(users, HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<UserDTO> handleLogin(@RequestBody UserLoginDTO loginDto) {
		User user = this.userService.login(loginDto);
		if (user != null) {
			return new ResponseEntity<UserDTO>(new UserDTO(user.getId(), user.getUsername(), user.getFirstName(),
					user.getLastName(), user.getPassword(), user.getEmail(), user.getRole()), HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/register")
	public ResponseEntity<UserDTO> handleRegistration(@RequestBody UserRegisterDTO registerDto) {
		User user = this.userService.register(registerDto);
		if (user != null) {
			return new ResponseEntity<>(new UserDTO(user.getId(), user.getUsername(), user.getFirstName(),
					user.getLastName(), user.getPassword(), user.getEmail(), user.getRole()), HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
