package com.viiup.web.flock.businessLayer;

import com.viiup.web.flock.businessLayer.interfaces.IEventBusinessLayer;
import com.viiup.web.flock.models.*;
import com.viiup.web.flock.providers.interfaces.IEventProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by AbdullahMoyeen on 2/7/16.
 */
@Service
public class EventBusinessLayer implements IEventBusinessLayer {

    @Autowired
    IEventProvider eventProvider;

    @Override
    public EventModel getEventByEventId(int eventId) {
        return eventProvider.getEventByEventId(eventId);
    }

    @Override
    public List<UserEventModel> getEventsByUserId(int userId) {
        return eventProvider.getEventsByUserId(userId);
    }

    @Override
    public void setEventRsvpStatus(int userId, int eventId, boolean isAttending){
        if (isAttending == true){
         EventUserRsvpModel eventUserRsvp = new EventUserRsvpModel();

            eventUserRsvp.setEventId(eventId);
            eventUserRsvp.setUserId(userId);
            eventUserRsvp.setRsvpTypeCode("Y");
            eventProvider.insertEventUserRsvp(eventUserRsvp);

        } else
           // if (eventProvider.isEventUserRsvpExist(userId,eventId ))
            eventProvider.deleteEventUserRsvp(userId, eventId);
    }

    @Override
    public void insertEvent(EventModel event){
        eventProvider.insertEvent(event);
    }

    @Override
    public void updateEvent(EventModel event) {
        eventProvider.updateEvent(event);
    }

    @Override
    public List<RefStateModel> getRefStateList(){

        return eventProvider.getRefStateList();
    }

    @Override
    public List<RefEventCategoryModel> getRefEventCategoryList(){

        return eventProvider.getRefEventCategoryList();
    }
}
