package com.viiup.web.flock.APIControllers;

import com.viiup.web.flock.models.UserModel;
import com.viiup.web.flock.models.UserPasswordChangeModel;
import com.viiup.web.flock.services.interfaces.IBaseService;
import com.viiup.web.flock.services.interfaces.IUserService;
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
    public ResponseEntity<UserModel> userUpdateProfile(@RequestBody UserModel user) {
        // Update user profile
//        userService.updateCustomerProfile(customer);

        // Get the updated user and return to caller
        user = userService.getUserByUserId(user.getUserId());

        return new ResponseEntity<UserModel>(user, HttpStatus.OK);

    }

    @RequestMapping(value = "/api/admin/user/password", method = RequestMethod.PUT)
    public ResponseEntity<Void> userUpdatePassword(@RequestBody UserPasswordChangeModel userPassword) throws Exception {
        // Update user password
        userService.changeUserPassword(userPassword);

        // Return response OK
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/api/admin/account", method = RequestMethod.POST)
    public ResponseEntity<UserModel> createAccount(@ModelAttribute UserModel user) throws Exception {

            baseService.signUp(user);
            return new ResponseEntity<UserModel>(user,HttpStatus.OK);
    }
}
