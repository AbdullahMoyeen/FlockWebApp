package com.viiup.web.flock.controllers;

import com.viiup.web.flock.models.UserGroup;
import com.viiup.web.flock.services.IUserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by Niranjan on 1/28/2016.
 */
@RestController
public class UserGroupAPIController {

    @Autowired
    IUserGroupService userGroupService;   // this service will do all the data retrieval/manipulation work.

    /*
        Method for retrieving all the user groups
     */
    @RequestMapping(value = "/api/usergroup/", method = RequestMethod.GET)
    public ResponseEntity<List<UserGroup>> listAllUserGroups() {
        // Get all user groups from service
        List<UserGroup> userGroups = userGroupService.findAllUserGroups();

        // If are not found then send a NO_CONTENT response
        if (userGroups.isEmpty()) return new ResponseEntity<List<UserGroup>>(HttpStatus.NO_CONTENT);

        // Return all user groups to caller
        return new ResponseEntity<List<UserGroup>>(userGroups, HttpStatus.OK);
    }

    /*
        Method for finding user group for a given Id
     */
    @RequestMapping(value = "/api/usergroup/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserGroup> findUserGroupById(@PathVariable("id") long id) {
        // Get user by ID from service
        UserGroup userGroup = userGroupService.findById(id);

        // Return status NOT_FOUND if user is not found
        if (userGroup == null) return new ResponseEntity<UserGroup>(HttpStatus.NOT_FOUND);

        // Return user as JSON with status OK
        return new ResponseEntity<UserGroup>(userGroup, HttpStatus.OK);
    }

    /*
        Method for creating a new user group.
     */
    @RequestMapping(value = "/api/usergroup/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUserGroup(@RequestBody UserGroup userGroup, UriComponentsBuilder uriComponentsBuilder) {
        // Do we already have this user group
        if (userGroupService.isUserGroupExists(userGroup)) return new ResponseEntity<Void>(HttpStatus.CONFLICT);

        // If NOT, then save the user to DB
        userGroupService.saveUserGroup(userGroup);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponentsBuilder.path("/api/user/{id}").buildAndExpand(userGroup.getUserGroupId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    /*
        Method for updating user group corresponding to a given Id
     */
    @RequestMapping(value = "/api/usergroup/{id}", method = RequestMethod.PUT)
    public ResponseEntity<UserGroup> updateUserGroupById(@PathVariable("id") long id, @RequestBody UserGroup userGroup) {

        // Get the user for Id
        UserGroup currentUserGroup = userGroupService.findById(id);

        // If user is not found return NOT_FOUND
        if (currentUserGroup == null) {
            return new ResponseEntity<UserGroup>(HttpStatus.NOT_FOUND);
        }

        // Set User attributes
        currentUserGroup.setGroupName(userGroup.getGroupName());
        currentUserGroup.setGroupDescription(userGroup.getGroupDescription());
        currentUserGroup.setMemberCount(userGroup.getMemberCount());

        userGroupService.updateUserGroup(currentUserGroup);
        return new ResponseEntity<UserGroup>(currentUserGroup, HttpStatus.OK);
    }

    /*
        Delete a user group for given Id
     */
    @RequestMapping(value = "/api/usergroup/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<UserGroup> deleteUserGroupById(@PathVariable("id") long id) {
        // Get User for id
        UserGroup userGroup = userGroupService.findById(id);

        // If not found return NOT_FOUND
        if (userGroup == null) {
            return new ResponseEntity<UserGroup>(HttpStatus.NOT_FOUND);
        }

        // If User is found delete it from database and return NO_CONTENT
        userGroupService.deleteUserGroupById(id);
        return new ResponseEntity<UserGroup>(HttpStatus.NO_CONTENT);
    }

    /*
        Deletes all user groups from database
     */
    @RequestMapping(value = "/api/usergroup/", method = RequestMethod.DELETE)
    public ResponseEntity<UserGroup> deleteAllUserGroups() {
        // Delete all users
        userGroupService.deleteAllUserGroups();
        return new ResponseEntity<UserGroup>(HttpStatus.NO_CONTENT);
    }
}
