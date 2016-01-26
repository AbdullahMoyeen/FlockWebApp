package com.viiup.web.flock.services;

import com.viiup.web.flock.models.AuthenticatedUser;
import com.viiup.web.flock.models.Customer;
import com.viiup.web.flock.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by amoyeen on 3/22/15.
 */
@Service
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Autowired
    CustomerService customerService;

    @Autowired
    OrderService orderService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication)
            throws IOException, ServletException {

        HttpSession httpSession = httpServletRequest.getSession();

        AuthenticatedUser authenticatedUser = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Customer customer = customerService.getCustomerByCustomerID(authenticatedUser.getUserId());
        httpSession.setAttribute("customer", customer);

        Order pendingOrder = orderService.getPendingOrder(customer.getCustomerID());
        Order sessionOrder = (Order) httpSession.getAttribute("order");

        if (sessionOrder == null){
            if (pendingOrder != null){
                httpSession.setAttribute("order", pendingOrder);
            }
        }
        else{
            if (pendingOrder != null){
                orderService.mergeOrder(sessionOrder, pendingOrder);
                sessionOrder = orderService.getOrderByOrderID(sessionOrder.getOrderID());
                httpSession.setAttribute("order", sessionOrder);
            }
            else{
                sessionOrder.setCustomerID(customer.getCustomerID());
                orderService.updateOrder(sessionOrder);
                httpSession.setAttribute("order", sessionOrder);
            }
        }

        httpServletResponse.sendRedirect((String) httpSession.getAttribute("urlPriorLogin"));
    }
}
