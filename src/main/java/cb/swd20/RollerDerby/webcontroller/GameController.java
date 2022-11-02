package cb.swd20.RollerDerby.webcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cb.swd20.RollerDerby.domain.Game;
import cb.swd20.RollerDerby.domain.Team;

@Controller
public class GameController {
	
	@RequestMapping("/")
	public String homePage(Model model){
		Team team1 = new Team("Helsinki Roller Derby", "HRD", "Helsinki");
		Team team2 = new Team("Kallio Rolling Rainbow", "KRR", "Helsinki");
		Team team3 = new Team("Oulu Roller Derby", "ORD", "Oulu");
		Team team4 = new Team("Riverdale Rollers", "RDR", "Ylivieska");
		Team team5 = new Team("Tampere Roller Derby", "TRD", "Tampere");
		
		Game game1 = new Game((long) 1, "22.10.2022.", "Pasilan urheiluhalli", team3, team5, 56, 151);
		Game game2 = new Game((long) 2, "22.10.2022.", "Pasilan urheiluhalli", team1, team4, 406, 40);
		
		List<Game> games = new ArrayList<>();
		games.add(game1);
		games.add(game2);
		
		model.addAttribute("games", games);
		return "home"; //return home.html
	}

}
