package com.spring.template.service.impl;

import com.spring.template.dao.UserDao;
import com.spring.template.exception.CustomException;
import com.spring.template.model.User;
import com.spring.template.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("userService")
@Transactional(rollbackFor = {Exception.class, CustomException.class})
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public User findById(int id) {
		return userDao.findById(id);
	}

	public User findBySSO(String sso) {
		User user = userDao.findBySSO(sso);
		return user;
	}

	public void saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userDao.save(user);
	}

	public void updateUser(User user) {
		User entity = userDao.findById(user.getId());
		if(entity!=null){
			entity.setSsoId(user.getSsoId());
			if(!user.getPassword().equals(entity.getPassword())){
				entity.setPassword(passwordEncoder.encode(user.getPassword()));
			}
			entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
			entity.setEmail(user.getEmail());
			entity.setUserProfiles(user.getUserProfiles());
		}
	}

	
	public void deleteUserBySSO(String sso) {
		userDao.deleteBySSO(sso);
	}

	public List<User> findAllUsers() {
		return userDao.findAllUsers();
	}

	public boolean isUserSSOUnique(Integer id, String sso) {
		User user = findBySSO(sso);
		return ( user == null || ((id != null) && (user.getId() == id)));
	}

	@Override
	public void saveOrUpdateUser(User user){
		if(user.getId()==null)
			saveUser(user);
		else
			updateUser(user);

	}
}
