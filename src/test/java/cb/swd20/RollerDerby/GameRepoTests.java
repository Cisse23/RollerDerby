package cb.swd20.RollerDerby;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import cb.swd20.RollerDerby.domain.Game;
import cb.swd20.RollerDerby.domain.GameRepository;
import cb.swd20.RollerDerby.domain.TeamRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class GameRepoTests {
	
	@Autowired
	private GameRepository gameRepo;
	@Autowired
	private TeamRepository teamRepo;
	
	//Test create, delete and search functionalities
	@Test
	public void testCreateNewGame() {
		Game game = new Game("14.2.2023", "Kallion Urheilutalo", null, null, 0, 0);
		gameRepo.save(game);
		assertThat(game.getId()).isNotNull();
	}
	
	@Test
	public void testDeleteGamek() {
		gameRepo.deleteById((long) 6);
		assertThat(teamRepo.findById((long)6)).isEmpty();
	}
	
	@Test
	public void testFindAllGames() {
		List<Game> games = (List<Game>) gameRepo.findAll();
		assertThat(games).hasSizeGreaterThanOrEqualTo(1);
	}
}
