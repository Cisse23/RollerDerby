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
		Game game3 = new Game((long) 3, "17.12.2022.", "Tampere", team5, team2, 0, 0);
		Game game4 = new Game((long) 4, "17.12.2022.", "Tampere", team3, team1, 0, 0);
		Game game5 = new Game((long) 5, "21.01.2023.", "Oulu", team4, team3, 0, 0);
		Game game6 = new Game((long) 6, "21.01.2023.", "Oulu", team1, team5, 0, 0);
		Game game7 = new Game((long) 7, "21.01.2023.", "Oulu", team3, team2, 0, 0);
		Game game8 = new Game((long) 8, "04.03.2023.", "Pasilan urheiluhalli", team2, team1, 0, 0);
		Game game9 = new Game((long) 9, "04.03.2023.", "Pasilan urheiluhalli", team5, team4, 0, 0);
		
		
		List<Game> games = new ArrayList<>();
		games.add(game1);
		games.add(game2);
		games.add(game3);
		games.add(game4);
		games.add(game5);
		games.add(game6);
		games.add(game7);
		games.add(game8);
		games.add(game9);
		
		model.addAttribute("games", games);
		return "home"; //return home.html
	}

}
