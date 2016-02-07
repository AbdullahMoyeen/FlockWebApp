package com.viiup.web.flock.APIControllers;

import com.viiup.web.flock.models.Customer;
import com.viiup.web.flock.models.UserModel;
import com.viiup.web.flock.services.IBaseService;
import com.viiup.web.flock.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Niranjan on 2/5/2016.
 */

@RestController
public class UserAPIController {

    @Autowired
    IUserService userService;

    @Autowired
    IBaseService baseService;

    @RequestMapping(value = "/api/admin/user/profile", method = RequestMethod.PUT)
    public ResponseEntity<Customer> userUpdateProfile(@RequestBody Customer customer) {
        // Update user profile
        userService.updateCustomerProfile(customer);

        // Get the updated user and return to caller
        customer = userService.getCustomerByCustomerID(customer.getCustomerID());

        return new ResponseEntity<Customer>(customer, HttpStatus.OK);

    }

    @RequestMapping(value = "/api/admin/user/password", method = RequestMethod.PUT)
    public ResponseEntity<Void> userUpdatePassword(@RequestBody Customer customer) {
        // Update user password
        userService.updateCustomerPassword(customer);

        // Return response OK
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/api/admin/account", method = RequestMethod.POST)
    public ResponseEntity<Customer> createAccount(@ModelAttribute Customer customer) {

        // Check if an account already exists for this email
        if(baseService.accountExistsForEmail(customer.getEmailAddress())) {
            return new ResponseEntity<Customer>(HttpStatus.CONFLICT);
        }
        else {
            baseService.signUpCustomer(customer);
            return new ResponseEntity<Customer>(customer,HttpStatus.OK);
        }
    }
}
