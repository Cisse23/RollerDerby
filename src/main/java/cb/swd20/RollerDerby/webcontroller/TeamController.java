package cb.swd20.RollerDerby.webcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cb.swd20.RollerDerby.domain.Team;
import cb.swd20.RollerDerby.domain.TeamRepository;

@Controller
public class TeamController {
	@Autowired
	private TeamRepository teamRepo;
	
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
	public String saveTeam(Team team) {
		teamRepo.save(team);
		return "redirect:teams";
	}
	

	@RequestMapping(value="/editteam/{id}")
	public String editTeam(@PathVariable("id") Long id, Model model) {
		model.addAttribute("team", teamRepo.findById(id));
		return "editteam";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteTeam(@PathVariable("id") Long id, Model model) {
		teamRepo.deleteById(id);
		return "redirect:../teams";
	}
	
	

	
}
