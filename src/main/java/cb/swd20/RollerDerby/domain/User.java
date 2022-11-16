package cb.swd20.RollerDerby.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity (name="users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;
	
	//unique username
	@Column(name = "username", nullable = false, unique = true)
	private String username;
	
	@Column(name = "password", nullable = false)
	private String passwordHash;
	
	@Column(name = "role", nullable = false)
	private String role;
	
	//@OneToOne(cascade = CascadeType.ALL)
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "team")
	private Team team;

	//constructor
	public User(String username, String passwordHash, String role, Team team) {
		super();
		this.username = username;
		this.passwordHash = passwordHash;
		this.role = role;
		this.team = team;
	}
	
	public User(String username, String passwordHash, String role) {
		super();
		this.username = username;
		this.passwordHash = passwordHash;
		this.role = role;
	}
	
	public User() {

	}

	
	//Getters
	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public String getRole() {
		return role;
	}

	public Team getTeam() {
		return team;
	}

	
	//Setters
	public void setId(Long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	
	//toString
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", passwordHash=" + passwordHash + ", role=" + role
				+ ", team=" + team + "]";
	}
	
	
	
	
	
	
}

