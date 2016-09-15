package com.spring.template.configuration;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by umutbuyukdurmus on 15.09.2016.
 */
@Component
public class LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler{

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        //Example
        //save logout time
        setDefaultTargetUrl("/login");
        super.onLogoutSuccess(request, response, authentication);
    }


}
