package com.viiup.web.flock.controllers;

import com.viiup.web.flock.models.*;
import com.viiup.web.flock.services.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by amoyeen on 3/1/15.
 */
@Controller
public class GroupController {

    @Autowired
    IGroupService groupService;

    @RequestMapping("/admin/groups")
    public ModelAndView adminGroups(@RequestParam int userId){

        List<GroupModel> adminGroups = groupService.getAdminGroupsByUserId(userId);

        ModelAndView modelAndView = new ModelAndView("adminGroups");
        modelAndView.addObject("adminGroups", adminGroups);

        return modelAndView;
    }

    @RequestMapping("/admin/group/details")
    public ModelAndView adminGroupDetails(@RequestParam int groupId){

        GroupModel adminGroupDetails = groupService.getGroupByGroupId(groupId);
        List<EventModel> adminGroupEvents = groupService.getGroupEventsByGroupId(groupId);
        List<GroupUserModel> adminGroupUsers = groupService.getGroupUsersByGroupId(groupId);

        ModelAndView modelAndView = new ModelAndView("adminGroupDetails");

        modelAndView.addObject("adminGroupDetails", adminGroupDetails);
        modelAndView.addObject("adminGroupEvents", adminGroupEvents);
        modelAndView.addObject("adminGroupUsers", adminGroupUsers);

        return modelAndView;
    }

    @RequestMapping("/admin/group/update")
    public String adminGroupUpdate(@ModelAttribute GroupModel adminGroupDetails){

        groupService.updateGroup(adminGroupDetails);

        return "redirect:/admin/group/details?groupId=" + adminGroupDetails.getGroupId();
    }

    @RequestMapping("/admin/group/membership/approve")
    public String adminGroupMembershipApprove(@RequestParam int groupId, int userId){

        groupService.approveGroupMembership(groupId, userId);

        return "redirect:/admin/group/details?groupId=" + groupId;
    }

    @RequestMapping("/admin/group/membership/deny")
    public String adminGroupMembershipDeny(@RequestParam int groupId, int userId){

        groupService.denyGroupMembership(groupId, userId);

        return "redirect:/admin/group/details?groupId=" + groupId;
    }
}
