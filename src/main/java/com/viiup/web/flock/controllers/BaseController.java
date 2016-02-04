package com.viiup.web.flock.controllers;

import com.viiup.web.flock.models.Customer;
import com.viiup.web.flock.services.IBaseService;
import com.viiup.web.flock.services.IUserService;
import com.viiup.web.flock.services.IEventService;
import com.viiup.web.flock.models.PasswordSecurity;
import com.viiup.web.flock.models.PasswordSecurityQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by amoyeen on 2/20/2015.
 */
@Controller
public class BaseController {

    @Autowired
    HttpSession httpSession;

    @Autowired
    IBaseService baseService;

    @Autowired
    IUserService userService;

    @Autowired
    IEventService eventService;

    @RequestMapping("/")
    public String home(){
        return ("adminSignIn");
    }



    @RequestMapping("/aboutUs")
    public String aboutUs(){
        return ("aboutUs");
    }

    @RequestMapping("/contactUs")
    public String contactUs(){
        return ("contactUs");
    }

    @RequestMapping("/signUp")
    public ModelAndView signUp(HttpServletRequest httpServletRequest){

        Customer customer = new Customer();
        List<PasswordSecurityQuestion> passwordSecurityQuestionList = baseService.getPasswordSecurityQuestionList();

        ModelAndView modelAndView = new ModelAndView("signUp");

        modelAndView.addObject("customer", customer);
        modelAndView.addObject("passwordSecurityQuestionList", passwordSecurityQuestionList);

        if(httpSession.getAttribute("urlPriorLogin") == null) {

            String referrer = httpServletRequest.getHeader("Referer");

            if (referrer != null) {
                httpSession.setAttribute("urlPriorLogin", referrer);
            }
        }

        return modelAndView;
    }

    @RequestMapping("/signUp/submit")
    public String signUpSubmit(@ModelAttribute Customer customer) {

        if(baseService.accountExistsForEmail(customer.getEmailAddress())) {
            return "redirect:/signUp?accountExistsForEmail=true";
        }
        else {
            baseService.signUpCustomer(customer);
            return "redirect:/signIn";
        }
    }

    @RequestMapping("/signIn")
    public String signIn(HttpServletRequest httpServletRequest){

        if(httpSession.getAttribute("urlPriorLogin") == null) {

            String referrer = httpServletRequest.getHeader("Referer");

            if (referrer != null) {
                httpSession.setAttribute("urlPriorLogin", referrer);
            }
        }

        return ("signIn");
    }

    @RequestMapping("/signIn/success")
    public String signInSuccess(){

//        return "redirect:" + httpSession.getAttribute("urlPriorLogin");

        return "redirect:/admin/groups";
    }

    @RequestMapping("/signIn/retrieveSecurity")
    public String signInRetrieveSecurity(){

        return "signInRetrieveSecurity";
    }

    @RequestMapping("/signIn/answerSecurity")
    public ModelAndView signInAnswerSecurity(@RequestParam String emailAddress){

        PasswordSecurity passwordSecurity = baseService.getPasswordSecurity(emailAddress);

        ModelAndView modelAndView = new ModelAndView("signInAnswerSecurity");

        modelAndView.addObject("passwordSecurity", passwordSecurity);

        return modelAndView;
    }

    @RequestMapping("/signIn/resetPassword")
    public ModelAndView signInResetPassword(@ModelAttribute PasswordSecurity passwordSecurity){

        if(baseService.validatePasswordSecurity(passwordSecurity)){

            Customer customer = userService.getCustomerByCustomerID(passwordSecurity.getCustomerID());
            customer.setPassword(null);

            ModelAndView modelAndView = new ModelAndView("signInResetPassword");

            modelAndView.addObject("customer", customer);

            return modelAndView;
        }
        else{

            ModelAndView modelAndView = new ModelAndView(new RedirectView ("/signIn/answerSecurity"));

            modelAndView.addObject("emailAddress", passwordSecurity.getEmailAddress());
            modelAndView.addObject("invalidAnswer", true);

            return modelAndView;
        }
    }

    @RequestMapping("/signIn/resetPassword/submit")
    public String signInResetPasswordSubmit(@ModelAttribute Customer customer){

        userService.updateCustomerPassword(customer);

        return "redirect:/signIn?passwordReset=true";
    }
}