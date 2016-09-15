package com.spring.template.service;

import com.spring.template.model.User;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;


public interface UserService {
	
	User findById(int id);
	
	User findBySSO(String sso);
	
	void saveUser(User user);

	@PreAuthorize("hasRole('ADMIN')")
	void updateUser(User user);

	@PreAuthorize("hasRole('ADMIN')")
	void deleteUserBySSO(String sso);

	List<User> findAllUsers(); 
	
	boolean isUserSSOUnique(Integer id, String sso);

	@Secured("ROLE_ADMIN")
	void saveOrUpdateUser(User user);
}