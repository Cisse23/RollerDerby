package cb.swd20.RollerDerby.webcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cb.swd20.RollerDerby.domain.Team;

@Controller
public class TeamController {
	
	@RequestMapping(value = "/teams")
	public String listTeams(Model model) {
		Team team1 = new Team("Helsinki Roller Derby", "HRD", "Helsinki");
		Team team2 = new Team("Kallio Rolling Rainbow", "KRR", "Helsinki");
		Team team3 = new Team("Oulu Roller Derby", "ORD", "Oulu");
		Team team4 = new Team("Riverdale Rollers", "RDR", "Ylivieska");
		Team team5 = new Team("Tampere Roller Derby", "TRD", "Tampere");
		
		List<Team> teams = new ArrayList<>();
		teams.add(team1);
		teams.add(team2);
		teams.add(team3);
		teams.add(team4);
		teams.add(team5);
		
		model.addAttribute("teams", teams);
		return "teams"; //return teams.html
	}
	
	@RequestMapping(value = "/addteam")
	public String addTeam() {
		return "addteam"; //return addteam.html
	}

	
}
