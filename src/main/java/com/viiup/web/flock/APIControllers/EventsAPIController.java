package com.viiup.web.flock.APIControllers;

import com.viiup.web.flock.models.EventModel;
import com.viiup.web.flock.services.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Date;

/**
 * Created by Niranjan on 2/4/2016.
 */

@RestController
public class EventsAPIController {

    @Autowired
    IEventService eventService;

    @RequestMapping(value = "/api/admin/event", method = RequestMethod.POST)
    public ResponseEntity<Void> createEvent(@RequestBody EventModel eventModel, UriComponentsBuilder ucBuilder) {

        // If event already exists then return a conflict
        if (eventService.getEventByEventID(eventModel.getEventID()) != null) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        // Event doesn't exist so create it.
        eventService.insertEvent(eventModel);

        // Send response headers back
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/admin/event/{id}").buildAndExpand(eventModel.getEventID()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/api/admin/event/{eventId}", method = RequestMethod.GET)
    public ResponseEntity<EventModel> getEventDetailsById(@PathVariable("eventId") int eventID){

        // Get event by event ID
        EventModel event =  eventService.getEventByEventID(eventID);

        // Return NO_CONTENT if the event is not found for this event id
        if(event == null) return new ResponseEntity<EventModel>(HttpStatus.NO_CONTENT);

        // Return the event found for this Id
        return new ResponseEntity<EventModel>(event,HttpStatus.OK);
    }

    @RequestMapping(value = "/api/admin/event", method = RequestMethod.PUT)
    public ResponseEntity<EventModel> updateEvent(@RequestBody EventModel event){

        // Check if event exists by this ID
        EventModel eventModel = eventService.getEventByEventID(event.getEventID());
        if(eventModel == null) return new ResponseEntity<EventModel>(HttpStatus.NO_CONTENT);

        // We found the event so update it
        eventService.updateEvent(eventModel);

        return new ResponseEntity<EventModel>(HttpStatus.OK);
    }
}
