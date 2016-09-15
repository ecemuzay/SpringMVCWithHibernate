package com.spring.template.service;

import com.spring.template.model.UserProfile;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserProfileService {

	UserProfile findById(int id);

	UserProfile findByType(String type);
	
	List<UserProfile> findAll();
	
}
