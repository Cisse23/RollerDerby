package cb.swd20.RollerDerby;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import cb.swd20.RollerDerby.webcontroller.GameController;
import cb.swd20.RollerDerby.webcontroller.TeamController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class RollerDerbyApplicationTests {

	@Autowired
	private TeamController teamController;
	@Autowired
	private GameController gameController;
	
	//Smoke tests
	@Test
	void contextLoads_teamController() {
		assertThat(teamController).isNotNull();
	}
	
	@Test
	void contextLoads_gameController() {
		assertThat(gameController).isNotNull();
	}

}
