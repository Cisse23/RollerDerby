package cb.swd20.RollerDerby.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	//private String club;

	private String name;
	private String acronym;
	private String city;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "homeTeam")
	@JsonIgnoreProperties("homeTeam")
	private List<Game> games;
	
	@OneToOne(mappedBy = "team")
	private User user;
	
	
	public Team(String name, String acronym, String city) {
		super();
		this.name = name;
		this.acronym = acronym;
		this.city = city;
	}
	
	public Team(Long id, String name, String acronym, String city) {
		super();
		this.id = id;
		this.name = name;
		this.acronym = acronym;
		this.city = city;
	}
	
	public Team() {
		super();
		this.name = null;
		this.acronym = null;
		this.city = null;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAcronym() {
		return acronym;
	}

	public String getCity() {
		return city;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		//return "Team [id=" + id + ", name=" + name + ", acronym=" + acronym + ", city=" + city + "]";
		return "Team [name=" + name + ", acronym=" + acronym + ", city=" + city + "]";
	}
	
	
	
}
