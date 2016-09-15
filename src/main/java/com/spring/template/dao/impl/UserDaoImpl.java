package com.spring.template.dao.impl;

import com.spring.template.dao.UserDao;
import com.spring.template.model.User;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;





@Repository("userDao")
public class UserDaoImpl extends BaseHibernateDAO<User> implements UserDao {

	static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	
	public User findById(int id) {
		User user = getById(id);
		if(user!=null){
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}

	public User findBySSO(String sso) {
		logger.info("SSO : {}", sso);

		User user = findByCriteria(Restrictions.eq("ssoId",sso));
		if(user!=null){
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}


	public List<User> findAllUsers() {

		List<User> users = getAll();

		return users;
	}


	public void deleteBySSO(String sso) {

		User user = findByCriteria(Restrictions.eq("ssoId",sso));
		delete(user);
	}

}
