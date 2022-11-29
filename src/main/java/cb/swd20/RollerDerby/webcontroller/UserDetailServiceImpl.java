package cb.swd20.RollerDerby.webcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cb.swd20.RollerDerby.domain.TeamRepository;
import cb.swd20.RollerDerby.domain.User;
import cb.swd20.RollerDerby.domain.UserRepository;

@Service
@Controller
public class UserDetailServiceImpl implements UserDetailsService{
	private final UserRepository userRepo;
	
	@Autowired
	public UserDetailServiceImpl(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	@Autowired TeamRepository teamRepo;
	
	PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		User currentUser = userRepo.findByUsername(username);
		UserDetails user = new org.springframework.security.core.userdetails.User(username, currentUser.getPasswordHash(), 
				AuthorityUtils.createAuthorityList(currentUser.getRole()));
		return user;
	}
	
	@RequestMapping(value = "/users")
	@PreAuthorize("hasAuthority('ADMIN')") //Only Admin can see users
	public String getUsers(Model model){
		model.addAttribute("users", userRepo.findAll());
		return "users"; //return users.html
	}
	
	@RequestMapping(value = "/adduser")
	@PreAuthorize("hasAuthority('ADMIN')") //Only Admin can add users
	public String addUser(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("teams", teamRepo.findAll());
		
		return "adduser";
	}
	
	@RequestMapping(value = "/saveuser", method = RequestMethod.POST)
	public String saveUser(User user) {
		//user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
		user.setPasswordHash("$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6");
		userRepo.save(user);
		
		return "redirect:users";
	}
	
	@RequestMapping(value = "/edituser/{id}")
	@PreAuthorize("hasAuthority('ADMIN')") //Only Admin can edit users
	public String editUser(@PathVariable("id") Long id, Model model) {
		model.addAttribute("user", userRepo.findById(id));
		model.addAttribute("teams", teamRepo.findAll());
		
		return "edituser";
	}
	
	@RequestMapping(value = "/deleteuser/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')") //Only Admin can delete users
	public String deleteUser(@PathVariable("id") Long id, Model model) {
		userRepo.deleteById(id);
		
		return "redirect:../users";
	}
	
	
	//.antMatchers("/delete/**", "/addteam", "/addgame", "/users", "/adduser", "/edituser/**").hasAuthority("ADMIN")
	//userRepo.save(new User("Admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN"));
	//userRepo.save(new User("Pekka", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER", teamRepo.findByAcronym("HRDB").get(0)));
}
