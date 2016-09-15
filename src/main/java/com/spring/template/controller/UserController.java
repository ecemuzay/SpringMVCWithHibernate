package com.spring.template.controller;

import com.spring.template.model.User;
import com.spring.template.model.UserProfile;
import com.spring.template.service.UserProfileService;
import com.spring.template.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/users")
@SessionAttributes("roles")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	UserProfileService userProfileService;
	
	@Autowired
	MessageSource messageSource;
/*
	@Autowired
	PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

*/
	
	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model) {

		List<User> users = userService.findAllUsers();
		model.addAttribute("users", users);

		return "userslist";
	}

	@RequestMapping(value = { "/delete-user-{ssoId}" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String ssoId) {
		userService.deleteUserBySSO(ssoId);
		return "redirect:/users/list";
	}

	@RequestMapping(value = { "/newuser" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);

		return "registration";
	}

	@ModelAttribute("roles")
	public List<UserProfile> initializeProfiles() {
		return userProfileService.findAll();
	}

	@RequestMapping(value = { "/usersAction" }, method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("user") @Valid User user, BindingResult result,
						   ModelMap model,final RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "registration";
		}

		if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "SSO ID exists");
			return "registration";
		}
		
		userService.saveOrUpdateUser(user);

		redirectAttributes.addFlashAttribute("css", "success");
		if(user.isNew()){
			redirectAttributes.addFlashAttribute("msg", "User added successfully!");
		}else{
			redirectAttributes.addFlashAttribute("msg", "User updated successfully!");
		}

		return "redirect:/users/list";
	}

	@RequestMapping(value = { "/edit-user-{ssoId}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String ssoId, ModelMap model) {
		User user = userService.findBySSO(ssoId);
		model.addAttribute("user", user);
		return "registration";
	}
	

	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		return "error";
	}

}