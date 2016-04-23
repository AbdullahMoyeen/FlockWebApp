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
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;
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
    public ResponseEntity<List<UserGroupModel>> getUserGroupsByUserId(@RequestParam int userId) {

        // List for holding groups
        List<UserGroupModel> groupsForUser = new ArrayList<UserGroupModel>();

        // Get the user groups for this user Id
        try {
            groupsForUser = groupService.getGroupsByUserId(userId);
        } catch (Exception e) {
            // Return error response
            return new ResponseEntity<List<UserGroupModel>>(groupsForUser, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // If not found then return no content
        if (groupsForUser.size() <= 0) return new ResponseEntity<List<UserGroupModel>>(HttpStatus.NO_CONTENT);

        // Return the list to the caller
        return new ResponseEntity<List<UserGroupModel>>(groupsForUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/user/events", method = RequestMethod.GET)
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

    @RequestMapping(value = "/api/user/events/rsvp", method = RequestMethod.PUT)
    public ResponseEntity<Void> setEventRSVPStatusByUserId(@RequestParam int userId, @RequestParam int eventId,
                                                           @RequestParam boolean isAttending) {

        // call the event service method to update RSVP status
        try {
            eventService.setEventRsvpStatus(userId, eventId, isAttending);
        } catch (Exception e) {
            // return internal server error
            new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // If the API call is successful return the HTTP OK status
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/api/user/groups/membership", method = RequestMethod.PUT)
    public ResponseEntity<Void> setGroupMembershipByUserId(@RequestParam int userId, @RequestParam int groupId,
                                                           @RequestParam boolean isMember) {
        // Call the API to set the group membership
        try {
            groupService.setGroupMembership(groupId, userId, isMember);
        } catch (Exception e) {
            // return internal server error
            new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // If we are here, the API call is successful
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/api/user/signup", method = RequestMethod.POST)
    public ResponseEntity<UserModel> signUpUser(@RequestBody UserModel user) {
        // sign up this user
        try {
            baseService.signUp(user);
        } catch (Exception e) {
            e.printStackTrace();
            new ResponseEntity<UserModel>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Return the user model after sign up
        return new ResponseEntity<UserModel>(user, HttpStatus.OK);

    }
}
