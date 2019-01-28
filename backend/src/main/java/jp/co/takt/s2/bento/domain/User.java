package jp.co.takt.s2.bento.domain;

import java.time.LocalDateTime;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "users")
public class User {

	// PrimaryKey
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;
	private String role;
	private String status;
	private String notes;

	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private LocalDateTime registered;

	protected User() {
	}

	/**
	 * constructor.
	 * 
	 * @param name
	 * @param role
	 * @param status
	 * @param registered
	 */
	public User(String name, String role, String status, LocalDateTime registered) {
		this.name = name;
		this.role = role;
		this.status = status;
		this.registered = registered;
	}

	@Override
	public String toString() {
		return String.format("User[id=%d, name='%s']", id, name);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getRegistered() {
		return registered;
	}

	public void setRegistered(LocalDateTime registered) {
		this.registered = registered;
	}
}
