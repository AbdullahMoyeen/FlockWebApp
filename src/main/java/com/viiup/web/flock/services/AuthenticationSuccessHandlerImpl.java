package com.viiup.web.flock.services;

import com.viiup.web.flock.models.AuthenticatedUser;
import com.viiup.web.flock.models.UserModel;
import com.viiup.web.flock.services.interfaces.IEventService;
import com.viiup.web.flock.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by AbdullahMoyeen on 1/22/2016.
 */
@Service
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    private SavedRequestAwareAuthenticationSuccessHandler target = new SavedRequestAwareAuthenticationSuccessHandler();

    @Autowired
    IUserService userService;

    @Autowired
    IEventService eventService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication)
            throws IOException, ServletException {

        HttpSession httpSession = httpServletRequest.getSession();

        AuthenticatedUser authenticatedUser = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserModel user = userService.getUserByUserId(authenticatedUser.getUserId());
        httpSession.setAttribute("userId", user.getUserId());
        httpSession.setAttribute("userName", user.getEmailAddress());
        httpSession.setAttribute("userFirstName", user.getFirstName());
        httpSession.setAttribute("userLastName", user.getLastName());

        target.setDefaultTargetUrl("/admin/groups?userId=" + user.getUserId());
        target.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);
    }
}