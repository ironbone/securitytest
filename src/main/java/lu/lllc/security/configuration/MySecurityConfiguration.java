package lu.lllc.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
 
@EnableWebSecurity
public class MySecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//We need here at least ONE mapping
		//Non mapped resources are accessible without authorization
		http.authorizeRequests()
		.antMatchers("/admin")
		.hasRole("ADMIN")
		.antMatchers("/restricted").hasAnyRole("USER", "ADMIN")
		.and()
		.formLogin().loginPage("/user/login").failureUrl("/user/login?error=true")
		.defaultSuccessUrl("/")
        .usernameParameter("name")
        .passwordParameter("password")
        .and().logout()
     // The following for automatic logout   
     //  .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
     //  .logoutSuccessUrl("/")
        .logoutUrl("/user/logout").logoutSuccessUrl("/")
        .and().exceptionHandling()
        .accessDeniedPage("/accessdenied");
	}
	
	@Bean
	public PasswordEncoder getPassword() {
		return new BCryptPasswordEncoder(); 
	}

}
