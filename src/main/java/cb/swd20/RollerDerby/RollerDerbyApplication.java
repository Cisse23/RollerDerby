package cb.swd20.RollerDerby;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import cb.swd20.RollerDerby.domain.Game;
import cb.swd20.RollerDerby.domain.GameRepository;
import cb.swd20.RollerDerby.domain.Team;
import cb.swd20.RollerDerby.domain.TeamRepository;
import cb.swd20.RollerDerby.domain.User;
import cb.swd20.RollerDerby.domain.UserRepository;

@SpringBootApplication
public class RollerDerbyApplication {
	
	private static final Logger log = LoggerFactory.getLogger(RollerDerbyApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(RollerDerbyApplication.class, args);
	}
	
	//localhost:8080/h2-console
	//INSERT INTO Team (id, name, acronym, city ) VALUES (1, 'Helsinki Roller Derby', 'HRD', 'Helsinki');
	//SLECT * FROM Team;
	
	//Luodaan testidataa H2-tietokantaan
	@Bean
	public CommandLineRunner testData(TeamRepository teamRepo, GameRepository gameRepo, UserRepository userRepo) {
		return(args) -> {
			log.info("Save some teams to the DB");
			teamRepo.save(new Team("Helsinki Roller Derby", "HRDB", "Helsinki"));
			teamRepo.save(new Team("Kallio Rolling Rainbow", "KRR", "Helsinki"));
			teamRepo.save(new Team("Oulu Roller Derby", "ORD", "Oulu"));
			teamRepo.save(new Team("Riverdale Rollers", "RDR", "Ylivieska"));
			teamRepo.save(new Team("Tampere Roller Derby", "TRDA", "Tampere"));
			
			log.info("Save some games to the DB");
			gameRepo.save(new Game("22.10.2022.", "Pasilan urheiluhalli", teamRepo.findByAcronym("ORD").get(0) , teamRepo.findByAcronym("TRDA").get(0), 56, 151));
			gameRepo.save(new Game("22.10.2022.", "Pasilan urheiluhalli", teamRepo.findByAcronym("HRDB").get(0) , teamRepo.findByAcronym("RDR").get(0), 406, 40));
			gameRepo.save(new Game("17.12.2022.", "Tampere", teamRepo.findByAcronym("TRDA").get(0), teamRepo.findByAcronym("KRR").get(0), 0, 0));
			gameRepo.save(new Game("17.12.2022.", "Tampere", teamRepo.findByAcronym("ORD").get(0), teamRepo.findByAcronym("HRDB").get(0), 0, 0));
			gameRepo.save(new Game("21.01.2023.", "Oulu", teamRepo.findByAcronym("RDR").get(0), teamRepo.findByAcronym("ORD").get(0), 0, 0));
			gameRepo.save(new Game("21.01.2023.", "Oulu", teamRepo.findByAcronym("HRDB").get(0), teamRepo.findByAcronym("TRDA").get(0), 0, 0));
			gameRepo.save(new Game("21.01.2023.", "Oulu", teamRepo.findByAcronym("ORD").get(0), teamRepo.findByAcronym("KRR").get(0), 0, 0));
			gameRepo.save(new Game("04.03.2023.", "Pasilan urheiluhalli", teamRepo.findByAcronym("KRR").get(0), teamRepo.findByAcronym("HRDB").get(0), 0, 0));
			gameRepo.save(new Game("04.03.2023.", "Pasilan urheiluhalli", teamRepo.findByAcronym("TRDA").get(0), teamRepo.findByAcronym("RDR").get(0), 0, 0));
			
			log.info("Save some users to  the DB");
			userRepo.save(new User("Admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN"));
			userRepo.save(new User("Pekka", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER", teamRepo.findByAcronym("HRDB").get(0)));
		};
	}
}
