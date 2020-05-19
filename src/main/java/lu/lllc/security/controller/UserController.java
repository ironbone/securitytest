package lu.lllc.security.controller;

import java.util.Collections;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lu.lllc.security.dto.UserDTO;
import lu.lllc.security.entity.Role;
import lu.lllc.security.entity.User;
import lu.lllc.security.repository.RoleRepository;
import lu.lllc.security.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@GetMapping("/register")
	public String register(Model model) {
		UserDTO userDto = new UserDTO();
		model.addAttribute("user", userDto);
		return "user/register";
	}
	
	@PostMapping("/register")
	public String createNewUser(@Validated @ModelAttribute("user") UserDTO userDTO, 
			BindingResult bindingResult ) {
		
		if(bindingResult.hasErrors())
		{
			return "user/register";
		}else {
			//This class comes from the library org.modelmapper 
			ModelMapper mapper = new ModelMapper();
			User user = mapper.map(userDTO, User.class);
			
			//Encode password
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			//This will be never set automatically
			user.setActive(true);
			
			Role role = roleRepository.findByRole("ROLE_USER");
			if(role == null) {
				role = new Role("ROLE_USER");
				role = roleRepository.save(role);
			}
			
			user.setRoles(Collections.singletonList(role));
			
			userRepository.save(user);
			
			return "redirect:/";
		}
		
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		UserDTO userDto = new UserDTO();
		model.addAttribute("user", userDto);
		return "user/login";
	}
	
	@GetMapping("/logout")
	public String logout(Model model) {;
		return "user/logout";
	}
	

}
