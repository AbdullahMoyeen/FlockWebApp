package com.viiup.web.flock.controllers;

import com.viiup.web.flock.models.*;
import com.viiup.web.flock.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by AbdullahMoyeen on 1/24/2016.
 */
@Controller
public class UserController {

    @Autowired
    IUserService userService;

    @RequestMapping("/admin/user/viewProfile")
    public ModelAndView adminUserViewProfile(@RequestParam int userId) {

        UserModel user = userService.getUserByUserId(userId);

        ModelAndView modelAndView = new ModelAndView("adminUserProfile");

        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @RequestMapping("/admin/user/updateProfile")
    public String adminUserUpdateProfile(@ModelAttribute UserModel user) {

//        userService.updateCustomerProfile(customer);
//        customer = userService.getCustomerByCustomerID(customer.getCustomerID());
//
//        httpSession.setAttribute("customer", customer);

        return "redirect:/admin/user/viewProfile?userId=" + user.getUserId();

    }

    @RequestMapping("/admin/user/passwordChange")
    public ModelAndView adminUserPasswordChange() {

        UserPasswordChangeModel userPassword = new UserPasswordChangeModel();

        ModelAndView modelAndView = new ModelAndView("adminPasswordChange");
        modelAndView.addObject("userPassword", userPassword);

        return modelAndView;
    }

    @RequestMapping("/admin/user/changePassword")
    public String adminUserChangePassword(@ModelAttribute UserPasswordChangeModel userPassword) {

        try {
            userService.changeUserPassword(userPassword);
            return "redirect:/displayMessage?messageCode=passwordChangeSuccess";
        } catch (Exception e) {
            if (e.getMessage().equals("CurrentPasswordInvalid"))
                return "redirect:/admin/user/passwordChange?currentPasswordInvalid=true";
            else
                return "redirect:/admin/user/passwordChange?unknownError=true";
        }
    }
}
