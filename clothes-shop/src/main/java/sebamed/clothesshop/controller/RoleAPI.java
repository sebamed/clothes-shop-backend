package sebamed.clothesshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sebamed.clothesshop.domain.Role;
import sebamed.clothesshop.dto.RoleDTO;
import sebamed.clothesshop.service.RoleService;

@RestController
@RequestMapping("/api/roles")
public class RoleAPI {

	@Autowired
	RoleService roleService;
	
	@GetMapping("/generate")
	public ResponseEntity<RoleDTO> handleGenerateRoles(){
		Role r1 = new Role();
		r1.setName("admin");
		
		Role r2 = new Role();
		r2.setName("user");
		
		this.roleService.save(r1);
		this.roleService.save(r2);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping("/{id}")
	public ResponseEntity<RoleDTO> handleGetOneRole(@PathVariable("id") Long id){
		Role role = this.roleService.findOne(id);
		if(role == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new RoleDTO(role.getId(), role.getName()), HttpStatus.OK);	
	}
	
	@PostMapping("/add")
	public RoleDTO handlePostRole(@RequestBody RoleDTO roleDto) {
		Role role = new Role();
		role.setName(roleDto.getName());
		Role newRole = this.roleService.save(role);
		return new RoleDTO(newRole.getId(), newRole.getName());
	}
	
}
