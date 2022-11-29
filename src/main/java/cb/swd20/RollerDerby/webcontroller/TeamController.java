package cb.swd20.RollerDerby.webcontroller;

import java.security.Principal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cb.swd20.RollerDerby.domain.Team;
import cb.swd20.RollerDerby.domain.TeamRepository;
import cb.swd20.RollerDerby.domain.User;
import cb.swd20.RollerDerby.domain.UserRepository;

@Controller
public class TeamController {
	@Autowired
	private TeamRepository teamRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@RequestMapping(value = "/teams")
	public String listTeams(Model model) {	
		model.addAttribute("teams", teamRepo.findAll());
		return "teams"; //return http://localhost:8080/teams
	}
	
	//Endpoint for team page, not implemented
	@RequestMapping(value="/team/{id}")
	public String getTeam(@PathVariable("id") Long teamId, Model model) {
		model.addAttribute("team", teamRepo.findById(teamId));
		return "team"; //return team.html
	}
	
	@RequestMapping(value = "/addteam")
	public String addTeam(Model model) {
		model.addAttribute("team", new Team());
		return "addteam"; //return addteam.html
	}
	
	@RequestMapping(value = "/saveteam", method = RequestMethod.POST)
	public String saveTeam(@NotBlank String name, @Size(min=3) String acronym, Team team) {
		teamRepo.save(team);
		return "redirect:teams";
	}
	

	@RequestMapping(value="/editteam/{id}")
	public String editTeam(@PathVariable("id") Long id, Model model, Principal principal) {
		User currentUser = userRepo.findByUsername(principal.getName());
		
		//Admin can edit any team
		if(currentUser.getRole().equals("ADMIN")) {
			model.addAttribute("team", teamRepo.findById(id));
			
			return "editteam";
		}
		else {
			//User can edit their own team
			Team userTeam = currentUser.getTeam();
			if(id == userTeam.getId()) {
				model.addAttribute("team", teamRepo.findById(id));
				
				return "editteam";
			}
			else {
				return "redirect:/teams";
			}
		}		
	}
	
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteTeam(@PathVariable("id") Long id, Model model) {
		teamRepo.deleteById(id);
		return "redirect:../teams";
	}
	
	@Controller
	public class Error implements ErrorController {
		@RequestMapping("/error")
		public String handleError() {
			return "error";
		}
	}

	
}
