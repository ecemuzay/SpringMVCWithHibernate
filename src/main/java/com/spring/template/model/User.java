package com.spring.template.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="APP_USER")
public class User extends BaseEntity implements Serializable{



	private String ssoId;
	

	private String password;
		

	private String firstName;


	private String lastName;


	private String email;


	private Set<UserProfile> userProfiles = new HashSet<>();

	@Transient
	public boolean isNew() {
		return (this.getId() == null);
	}

	@NotEmpty
	@Column(name="SSO_ID", unique=true, nullable=false)
	public String getSsoId() {
		return ssoId;
	}

	public void setSsoId(String ssoId) {
		this.ssoId = ssoId;
	}

	@NotEmpty
	@Column(name="PASSWORD", nullable=false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@NotEmpty
	@Column(name="FIRST_NAME", nullable=false)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@NotEmpty
	@Column(name="LAST_NAME", nullable=false)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@NotEmpty
	@Column(name="EMAIL", nullable=false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@NotEmpty
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "APP_USER_USER_PROFILE",
			joinColumns = { @JoinColumn(name = "USER_ID",referencedColumnName="id") },
			inverseJoinColumns = { @JoinColumn(name = "USER_PROFILE_ID" ,referencedColumnName="id") })
	public Set<UserProfile> getUserProfiles() {
		return userProfiles;
	}

	public void setUserProfiles(Set<UserProfile> userProfiles) {
		this.userProfiles = userProfiles;
	}


	
}
