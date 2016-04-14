package com.viiup.web.flock.APIControllers;

import com.viiup.web.flock.models.EventModel;
import com.viiup.web.flock.models.GroupModel;
import com.viiup.web.flock.models.UserModel;
import com.viiup.web.flock.models.UserPasswordChangeModel;
import com.viiup.web.flock.services.IBaseService;
import com.viiup.web.flock.services.IUserService;
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

        // Hard code a list of groups to be returned for this user
        for (int i = 0; i < 5; ++i) {
            GroupModel group = new GroupModel();
            group.setGroupId(i);
            group.setGroupName("Group - " + i);
            group.setGroupDescription("Group description for group - " + i);

            // Add to the list
            groupsForUser.add(group);
        }

        // If not found then return no content
        if (groupsForUser == null) return new ResponseEntity<List<GroupModel>>(HttpStatus.NO_CONTENT);

        // Return the list to the caller
        return new ResponseEntity<List<GroupModel>>(groupsForUser, HttpStatus.OK);
    }

    @RequestMapping(value = "api/user/events", method = RequestMethod.GET)
    public ResponseEntity<List<EventModel>> getUserEventsByUserId(@RequestParam int userId) {

        // List for holding the events for this user and public events from other groups
        List<EventModel> eventsForUser = new ArrayList<EventModel>();

        // Hard coded list of groups for testing
        for (int i = 0; i < 5; ++i) {
            EventModel eventModel = new EventModel();
            eventModel.setEventId(i);
            eventModel.setGroupId(i + 1);
            eventModel.setEventAddressLine1("Address line 1 - " + i);
            eventModel.setEventCity("Dallas");
            eventModel.setEventKeywords("test,event,UTD");
            eventModel.setEventName("Event - " + i);
            eventModel.setEventDescription("Event " + i + " description");

            // Add the event to list
            eventsForUser.add(eventModel);
        }

        // If not found then return no content
        if (eventsForUser == null) return new ResponseEntity<List<EventModel>>(HttpStatus.NO_CONTENT);

        // Send back the response
        return new ResponseEntity<List<EventModel>>(eventsForUser, HttpStatus.OK);
    }
}
