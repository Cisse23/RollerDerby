package cb.swd20.RollerDerby;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import cb.swd20.RollerDerby.webcontroller.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity
public class WebSecurityConfig {

	@Autowired
	private UserDetailServiceImpl userDetailService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder autManBldr)throws Exception{
		autManBldr.userDetailsService(userDetailService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception{
		http
		.authorizeRequests()
		.antMatchers("/", "/home", "/teams").permitAll()
		.antMatchers("/delete/**", "/addteam", "/addgame").hasAuthority("ADMIN")
		.antMatchers("/editteam/**", "/editgame/**").hasAnyAuthority("ADMIN", "USER")
		.anyRequest().authenticated()
		.and()
	.formLogin()
		.defaultSuccessUrl("/", true)
		.permitAll()
		.and()
	.logout()
		//.logoutSuccessUrl("/")
		.permitAll()
		.and()
	.httpBasic();
		
		return http.build();
	}
	
}
