package com.spring.template.dao;

import com.spring.template.model.UserProfile;

import java.util.List;


public interface UserProfileDao extends BaseDAO<UserProfile>{

	List<UserProfile> findAll();
	
	UserProfile findByType(String type);
	
	UserProfile findById(int id);
}
