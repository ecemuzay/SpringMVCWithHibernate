package com.spring.template.service.impl;


import com.spring.template.dao.UserProfileDao;
import com.spring.template.exception.CustomException;
import com.spring.template.model.UserProfile;
import com.spring.template.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("userProfileService")
@Transactional(rollbackFor = {Exception.class, CustomException.class})
public class UserProfileServiceImpl implements UserProfileService{
	
	@Autowired
	UserProfileDao dao;
	
	public UserProfile findById(int id) {
		return dao.findById(id);
	}

	public UserProfile findByType(String type){
		return dao.findByType(type);
	}

	public List<UserProfile> findAll() {
		return dao.findAll();
	}
}
