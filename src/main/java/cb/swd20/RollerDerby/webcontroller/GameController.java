package cb.swd20.RollerDerby.webcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cb.swd20.RollerDerby.domain.GameRepository;

@Controller
public class GameController {
	
	@Autowired
	private GameRepository gameRepo;
	
	@RequestMapping("/")
	public String homePage(Model model){
			
		model.addAttribute("games", gameRepo.findAll());
		return "home"; //return home.html
	}

}
