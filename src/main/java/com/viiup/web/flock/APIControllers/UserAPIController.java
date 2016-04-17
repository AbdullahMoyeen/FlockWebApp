package com.viiup.web.flock.APIControllers;

import com.viiup.web.flock.models.*;
import com.viiup.web.flock.services.interfaces.IBaseService;
import com.viiup.web.flock.services.interfaces.IEventService;
import com.viiup.web.flock.services.interfaces.IGroupService;
import com.viiup.web.flock.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niranjan on 2/5/2016.
 */

@RestController
public class UserAPIController {

    @Autowired
    IUserService userService;

    @Autowired
    IBaseService baseService;

    @Autowired
    IEventService eventService;

    @Autowired
    IGroupService groupService;

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
        return new ResponseEntity<UserModel>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/user/groups", method = RequestMethod.GET)
    public ResponseEntity<List<GroupModel>> getUserGroupsByUserId(@RequestParam int userId) {

        // List for holding groups
        List<GroupModel> groupsForUser = new ArrayList<GroupModel>();

        // Get the user groups for this user Id
        try {
            groupsForUser = groupService.getGroupsByUserId(userId);
        }catch (Exception e) {
            // Return error response
            return new ResponseEntity<List<GroupModel>>(groupsForUser,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // If not found then return no content
        if (groupsForUser.size() <= 0) return new ResponseEntity<List<GroupModel>>(HttpStatus.NO_CONTENT);

        // Return the list to the caller
        return new ResponseEntity<List<GroupModel>>(groupsForUser, HttpStatus.OK);
    }

    @RequestMapping(value = "api/user/events", method = RequestMethod.GET)
    public ResponseEntity<List<UserEventModel>> getUserEventsByUserId(@RequestParam int userId) {

        // List for holding the events for this user and public events from other groups
        List<UserEventModel> eventsForUser = new ArrayList<UserEventModel>();

        // Get the list of events for this user
        try {
            eventsForUser = eventService.getUserEventsByUserId(userId);
        } catch (Exception e) {
            // Return http error
            return new ResponseEntity<List<UserEventModel>>(eventsForUser, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // If not found then return no content
        if (eventsForUser.size() <= 0) return new ResponseEntity<List<UserEventModel>>(HttpStatus.NO_CONTENT);

        // Send back the response
        return new ResponseEntity<List<UserEventModel>>(eventsForUser, HttpStatus.OK);
    }
}
