package com.viiup.web.flock.APIControllers;

import com.viiup.web.flock.models.GroupModel;
import com.viiup.web.flock.models.GroupUserModel;
import com.viiup.web.flock.services.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Niranjan on 2/4/2016.
 */

@RestController
public class GroupAPIController {

    @Autowired
    IGroupService groupService;

    @RequestMapping(value = "/api/admin/group/user", method = RequestMethod.GET)
    public ResponseEntity<List<GroupModel>> adminGroupsForUser(@RequestParam int userId) {

        /* List of admin groups to return to client */
        List<GroupModel> adminGroups = groupService.getAdminGroupsByUserId(userId);

        /* If are not found then send a NO_CONTENT response */
        if (adminGroups.isEmpty()) return new ResponseEntity<List<GroupModel>>(HttpStatus.NO_CONTENT);

        /* Return all admin groups to caller */
        return new ResponseEntity<List<GroupModel>>(adminGroups, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/admin/group", method = RequestMethod.GET)
    public ResponseEntity<GroupModel> adminGroupDetails(@RequestParam int groupId) {
        // Get group model
        GroupModel adminGroupDetails = groupService.getGroupByGroupId(groupId);

        // If not found then return not found
        if (adminGroupDetails == null) return new ResponseEntity<GroupModel>(HttpStatus.NO_CONTENT);

        // If found return group details to user
        return new ResponseEntity<GroupModel>(adminGroupDetails, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/admin/user/group", method = RequestMethod.GET)
    public ResponseEntity<List<GroupUserModel>> usersByGroupID(@RequestParam int groupId) {
        // List of user for an admin group
        List<GroupUserModel> adminGroupUsers = groupService.getGroupUsersByGroupId(groupId);

        // If not found then return no content
        if (adminGroupUsers == null) return new ResponseEntity<List<GroupUserModel>>(HttpStatus.NO_CONTENT);

        // if found return the list of users for this group
        return new ResponseEntity<List<GroupUserModel>>(adminGroupUsers, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/admin/group", method = RequestMethod.PUT)
    public ResponseEntity<GroupModel> adminGroupUpdate(@RequestBody GroupModel adminGroupDetails) {
        // Get the group using group Id
        GroupModel adminGroup = groupService.getGroupByGroupId(adminGroupDetails.getGroupId());

        // If group not found then return NO_CONTENT response back
        if (adminGroup == null) return new ResponseEntity<GroupModel>(HttpStatus.NO_CONTENT);

        // If found then update the admin group
        adminGroup = groupService.updateGroup(adminGroupDetails);

        // Return the updated group model
        return new ResponseEntity<GroupModel>(adminGroup, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/admin/group/membership", method = RequestMethod.PUT)
    public ResponseEntity<Void> adminGroupMembershipApprove(@RequestParam int groupId, @RequestParam int userId, @RequestParam String action) {

        if (action.equalsIgnoreCase("approve"))
            groupService.approveGroupMembership(groupId, userId);
        else if (action.equalsIgnoreCase("deny"))
            groupService.denyGroupMembership(groupId, userId);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
