package com.spring.template.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="USER_PROFILE")
public class UserProfile extends BaseEntity implements Serializable{


	private String type = UserProfileType.USER.getUserProfileType();

	@Column(name="TYPE", length=15, unique=true, nullable=false)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}





}
