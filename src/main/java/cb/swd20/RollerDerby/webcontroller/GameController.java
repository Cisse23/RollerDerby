package cb.swd20.RollerDerby.webcontroller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cb.swd20.RollerDerby.domain.Game;
import cb.swd20.RollerDerby.domain.GameRepository;
import cb.swd20.RollerDerby.domain.Team;
import cb.swd20.RollerDerby.domain.TeamRepository;
import cb.swd20.RollerDerby.domain.User;
import cb.swd20.RollerDerby.domain.UserRepository;

@Controller
public class GameController {
	
	@Autowired
	private GameRepository gameRepo;
	
	@Autowired
	private TeamRepository teamRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@RequestMapping("/")
	public String homePage(Model model){
			
		model.addAttribute("games", gameRepo.findAll());
		return "home"; //return home.html
	}
	
	@RequestMapping(value = "/addgame")
	public String addGame(Model model) {
		model.addAttribute("game", new Game());
		model.addAttribute("teams", teamRepo.findAll());
		return "addgame"; //return addgame.html
	}
	
	@RequestMapping(value = "/savegame", method = RequestMethod.POST)
	public String saveGame(Game game) {
		gameRepo.save(game);
		return "redirect:/";
	}
	
	@RequestMapping(value="/username", method = RequestMethod.GET)
	public String getCurrentUserTeam(Principal principal) {
		return principal.getName();
	}
	
	
	@RequestMapping(value="/editgame/{id}")
	public String editGame(@PathVariable("id") Long id, Model model, Principal principal) {
		User currentUser = userRepo.findByUsername(principal.getName());
		Optional<Game> game = gameRepo.findById(id);
		//Admin can edit all games
		if(currentUser.getRole().equals("ADMIN")) {
			model.addAttribute("game", gameRepo.findById(id));
			model.addAttribute("teams", teamRepo.findAll());
			
			return "editgame";
		}
		else {
			//User can edit a game if their team is the homeTeam
			Team userTeam = currentUser.getTeam();
			if(game.get().getHomeTeam().getId() == userTeam.getId()) {
				model.addAttribute("game", gameRepo.findById(id));
				model.addAttribute("teams", teamRepo.findAll());
				return "editgame";
			}
			else {
				//tried to display an error message, but it's not working
				String message = "Access denied";
				model.addAttribute("message", message);
				return "redirect:/";
			}
		}

	}
	
	@RequestMapping(value = "/deletegame/{id}", method = RequestMethod.GET)
	public String deleteGame(@PathVariable("id") Long gameId, Model model) {
		gameRepo.deleteById(gameId);
		return "redirect:../";
	}

}
