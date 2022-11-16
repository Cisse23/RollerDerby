package cb.swd20.RollerDerby;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import cb.swd20.RollerDerby.domain.Team;
import cb.swd20.RollerDerby.domain.TeamRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TeamRepoTests {
	
	@Autowired
	private TeamRepository teamRepo;
	
	//Test create, delete and search functionalities
	@Test
	public void testCreateNewTeam() {
		Team team = new Team("Turku Roller Derby", "TRD", "Turku");
		teamRepo.save(team);
		assertThat(team.getId()).isNotNull();
	}
	
	@Test
	public void testDeleteTeamk() {
		teamRepo.deleteById((long) 1);
		assertThat(teamRepo.findById((long)1)).isEmpty();
	}
	
	@Test
	public void testFindAllTeams() {
		List<Team> teams = (List<Team>) teamRepo.findAll();
		assertThat(teams).hasSizeGreaterThanOrEqualTo(1);
	}
	
	
	// Test List<Team>findByAcronym(String acronym);
	@Test
	public void testFindTeamByAcronym() {
		List<Team> teams = (List<Team>) teamRepo.findByAcronym("HRDB");
		assertThat(teams.get(0).getName()).isEqualTo("Helsinki Roller Derby");
	}
	
	//Test List<Team>findByCity(String city);
	@Test
	public void testFindTeamByCity() {
		List<Team> teams = (List<Team>) teamRepo.findByCity("Tampere");
		assertThat(teams.get(0).getName()).isEqualTo("Tampere Roller Derby");
	}
}
