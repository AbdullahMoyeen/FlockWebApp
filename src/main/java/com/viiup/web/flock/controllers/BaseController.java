package com.viiup.web.flock.controllers;

import com.viiup.web.flock.models.*;
import com.viiup.web.flock.services.interfaces.IBaseService;
import com.viiup.web.flock.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by AbdullahMoyeen on 1/27/2016.
 */
@Controller
public class BaseController {

    @Autowired
    HttpSession httpSession;

    @Autowired
    IBaseService baseService;

    @Autowired
    IUserService userService;

    @RequestMapping("/")
    public String adminSignIn() {
        return "adminSignIn";
    }

    @RequestMapping("/signUp")
    public ModelAndView adminSignup() {

        UserModel user = new UserModel();

        ModelAndView modelAndView = new ModelAndView("adminSignUp");

        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @RequestMapping("/signUp/submit")
    public String signUpSubmit(@ModelAttribute UserModel user) {

        try {
            baseService.signUp(user);
            return "redirect:/displayMessage?messageCode=tempPasswordSent";
        } catch (Exception e) {
            if (e.getMessage().equals("UserAlreadyExists"))
                return "redirect:/signUp?userAlreadyExists=true";
            else
                return "redirect:/signUp?unknownError=true";
        }
    }

    @RequestMapping("/signIn/success")
    public String adminSignInSuccess() {

        return "redirect:/admin/groups?userId=" + httpSession.getAttribute("userId");
    }

    @RequestMapping("/passwordReset")
    public String adminPasswordReset() {

        return "adminPasswordReset";
    }

    @RequestMapping("/resetPassword")
    public String adminTempPassword(@RequestParam String emailAddress) {

        try {
            baseService.resetPassword(emailAddress);
            return "redirect:/displayMessage?messageCode=tempPasswordSent";
        } catch (Exception e) {
            if (e.getMessage().equals("UserNotFound"))
                return "redirect:/passwordReset?userNotFound=true";
            else
                return "redirect:/passwordReset?unknownError=true";
        }
    }

    @RequestMapping("/tempPasswordChange")
    public ModelAndView adminUserPasswordChange() {

        UserPasswordChangeModel userPassword = new UserPasswordChangeModel();

        ModelAndView modelAndView = new ModelAndView("adminTempPasswordChange");
        modelAndView.addObject("userPassword", userPassword);

        return modelAndView;
    }

    @RequestMapping("/changeTempPassword")
    public String adminUserChangePassword(@ModelAttribute UserPasswordChangeModel userPassword) {

        try {
            userService.changeUserPassword(userPassword);
            return "redirect:/displayMessage?messageCode=tempChangeSuccess";
        } catch (Exception e) {
            if (e.getMessage().equals("CurrentPasswordInvalid"))
                return "redirect:/tempPasswordChange?currentPasswordInvalid=true";
            else
                return "redirect:/tempPasswordChange?unknownError=true";
        }
    }

    @RequestMapping("/displayMessage")
    public String adminDisplayMessage() {

        return "adminDisplayMessage";
    }

    @RequestMapping("/aboutUs")
    public String aboutUs() {
        return ("aboutUs");
    }

    @RequestMapping("/contactUs")
    public String contactUs() {
        return ("contactUs");
    }
}