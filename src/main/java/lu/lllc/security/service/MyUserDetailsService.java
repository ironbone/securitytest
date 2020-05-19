package lu.lllc.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lu.lllc.security.entity.Role;
import lu.lllc.security.entity.User;
import lu.lllc.security.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByName(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("Unknown user");
		}
		

		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		
		for (Role role : user.getRoles()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
		}
		

		
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(
				user.getName(), user.getPassword(), user.getActive(), true, true, true,grantedAuthorities);
		
		return userDetails;
	}

}
