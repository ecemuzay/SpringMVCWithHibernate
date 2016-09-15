package com.spring.template.dao.impl;

import com.spring.template.dao.UserProfileDao;
import com.spring.template.model.UserProfile;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository("userProfileDao")
public class UserProfileDaoImpl extends BaseHibernateDAO<UserProfile>implements UserProfileDao{

	public UserProfile findById(int id) {
		return getById(id);
	}

	public UserProfile findByType(String type) {

		return findByCriteria(Restrictions.eq("type",type));
	}
	
	public List<UserProfile> findAll(){

		return getAll();
	}
	
}
