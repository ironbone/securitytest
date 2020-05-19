package lu.lllc.security.dto;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class UserDTO {

	private int id;
	
	@Length(min = 5, message = "User name must have at least 5 characters")
	@NotEmpty(message = "The user name must be not empty")
	private String name;
	

	@Email(message = "Valid Email expected")
	@NotEmpty(message = "Your email must be not empty")
	private String email;
	
	@Column(name = "password")
	@Length(min = 5, message = "Your password must have at least 5 characters")
	@NotEmpty(message = "Your password must be not empty")
	private String password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
