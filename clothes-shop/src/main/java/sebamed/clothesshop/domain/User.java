package sebamed.clothesshop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "first_name", length = 255)
	private String firstName;
	
	@Column(name = "sur_name", length = 255)
	private String surName;
	
	@Column(name = "username", length = 255)
	private String username;
	
	@Column(name = "password", length = 255)
	private String password;
		
	@Column(name = "email", length = 255)
	private String email;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "role_id", nullable = true)
	private Role role;

	public User(Long id, String firstName, String surName, String username, String password, String email, Role role) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.surName = surName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
	
	
}
