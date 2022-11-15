package cb.swd20.RollerDerby.webcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cb.swd20.RollerDerby.domain.Game;
import cb.swd20.RollerDerby.domain.GameRepository;
import cb.swd20.RollerDerby.domain.TeamRepository;

@Controller
public class GameController {
	
	@Autowired
	private GameRepository gameRepo;
	
	@Autowired
	private TeamRepository teamRepo;
	
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
	
	@RequestMapping(value = "/deletegame/{id}", method = RequestMethod.GET)
	public String deleteGame(@PathVariable("id") Long gameId, Model model) {
		gameRepo.deleteById(gameId);
		return "redirect:../";
	}

}
