package com.spring.template.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by umutbuyukdurmus on 14.09.2016.
 */
@Controller
@SessionAttributes("loggedinuser")
public class BaseController {

    private final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;

//    @ModelAttribute("loggedinuser")
//    public String initializeProfiles() {
//        return getPrincipal();
//    }

    @RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
    public String index(Model model) {

        logger.debug("index()");
        model.addAttribute("loggedinuser",getPrincipal());
        return "welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        if (isCurrentAuthenticationAnonymous()) {
            return "login";
        } else {
            return "redirect:/home";
        }
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/home";
    }

    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }

    private String getPrincipal(){
        String userName = "";
       if(SecurityContextHolder.getContext().getAuthentication()!=null) {
           Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
           if (principal != null) {
               if (principal instanceof UserDetails) {
                   userName = ((UserDetails) principal).getUsername();
               } else {
                   userName = principal.toString();
               }
           }
       }
        return userName;
    }



}
