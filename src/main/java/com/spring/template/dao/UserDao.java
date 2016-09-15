package com.spring.template.dao;

import com.spring.template.model.User;

import java.util.List;


public interface UserDao extends BaseDAO<User>{

	User findById(int id);
	
	User findBySSO(String sso);


	void deleteBySSO(String sso);
	
	List<User> findAllUsers();

}

