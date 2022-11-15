package cb.swd20.RollerDerby.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Game {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String date;
	private String venue;
	@ManyToOne
	@JsonIgnoreProperties("games")
	@JoinColumn(name = "homeTeam_id", referencedColumnName = "id")
	private Team homeTeam;
	
	@ManyToOne
	@JsonIgnoreProperties("games")
	@JoinColumn(name = "visitingTeam_id", referencedColumnName = "id")
	private Team visitingTeam;
	private int scoreHomeTeam;
	private int scoreVisitingTeam;
	
	public Game(String date, String venue, Team homeTeam, Team visitingTeam, int scoreHomeTeam,
			int scoreVisitingTeam) {
		super();
		this.date = date;
		this.venue = venue;
		this.homeTeam = homeTeam;
		this.visitingTeam = visitingTeam;
		this.scoreHomeTeam = scoreHomeTeam;
		this.scoreVisitingTeam = scoreVisitingTeam;
	}
	
	public Game() {
		super();
		this.date = null;
		this.venue = null;
		this.homeTeam = null;
		this.visitingTeam = null;
		this.scoreHomeTeam = 0;
		this.scoreVisitingTeam = 0;
	}

	public Long getId() {
		return id;
	}

	public String getDate() {
		return date;
	}

	public String getVenue() {
		return venue;
	}

	public Team getHomeTeam() {
		return homeTeam;
	}

	public Team getVisitingTeam() {
		return visitingTeam;
	}

	public int getScoreHomeTeam() {
		return scoreHomeTeam;
	}

	public int getScoreVisitingTeam() {
		return scoreVisitingTeam;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}

	public void setVisitingTeam(Team visitingTeam) {
		this.visitingTeam = visitingTeam;
	}

	public void setScoreHomeTeam(int scoreHomeTeam) {
		this.scoreHomeTeam = scoreHomeTeam;
	}

	public void setScoreVisitingTeam(int scoreVisitingTeam) {
		this.scoreVisitingTeam = scoreVisitingTeam;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", date=" + date + ", venue=" + venue + ", homeTeam=" + homeTeam + ", visitingTeam="
				+ visitingTeam + ", scoreHomeTeam=" + scoreHomeTeam + ", scoreVisitingTeam=" + scoreVisitingTeam + "]";
	}
	
	
}
