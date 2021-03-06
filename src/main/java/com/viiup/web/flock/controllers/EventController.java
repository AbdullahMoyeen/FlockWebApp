package com.viiup.web.flock.controllers;

import com.viiup.web.flock.models.*;
import com.viiup.web.flock.services.interfaces.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by AbdullahMoyeen on 2/1/2016.
 */
@Controller
public class EventController {

    @Autowired
    IEventService eventService;

    @RequestMapping("/admin/group/event/details")
    public ModelAndView groupEventsCallEvent(@RequestParam int eventId, int groupId){
        EventModel event = new EventModel();
        if (eventId ==0) {
            event.setGroupId(groupId);
        }else {
            event= eventService.getEventByEventId(eventId);
        }
        ModelAndView modelAndView = new ModelAndView("adminGroupEventDetails");
        List<RefEventCategoryModel> refEventCategoryList = eventService.getRefEventCategoryList();
        List<RefStateModel> refStateList = eventService.getRefStateList();
        modelAndView.addObject("event", event);
        modelAndView.addObject("refEventCategoryList", refEventCategoryList);
        modelAndView.addObject("refStateList", refStateList);

        return modelAndView;
    }

    @RequestMapping("/admin/group/event/details/submit")
    public String eventSubmit(@ModelAttribute EventModel event){
        if (event.getEventId() == 0) {
            eventService.insertEvent(event);
        }else {
            eventService.updateEvent(event);
        }

        return "redirect:/admin/group/details?groupId=" + event.getGroupId();
    }
}
