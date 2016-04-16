package com.viiup.web.flock.services;

import com.viiup.web.flock.businessLayer.interfaces.IEventBusinessLayer;
import com.viiup.web.flock.models.EventModel;
import com.viiup.web.flock.models.RefEventCategoryModel;
import com.viiup.web.flock.models.RefStateModel;
import com.viiup.web.flock.models.UserEventModel;
import com.viiup.web.flock.services.interfaces.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by AbdullahMoyeen on 1/25/2016.
 */
@Service
public class EventService implements IEventService {

    @Autowired
    IEventBusinessLayer eventBusinessLayer;

    @Override
    public EventModel getEventByEventId(int eventId) {
        return eventBusinessLayer.getEventByEventId(eventId);
    }

    @Override
    public List<UserEventModel> getUserEventsByUserId(int userId) {
        return eventBusinessLayer.getEventsByUserId(userId);
    }

    @Override
    public void insertEvent(EventModel event){
        eventBusinessLayer.insertEvent(event);
    }

    @Override
    public void updateEvent(EventModel event) {
        eventBusinessLayer.updateEvent(event);
    }

    @Override
    public List<RefStateModel> getRefStateList(){

        return eventBusinessLayer.getRefStateList();
    }

    @Override
    public List<RefEventCategoryModel> getRefEventCategoryList(){

        return eventBusinessLayer.getRefEventCategoryList();
    }

    @Override
    public void setEventRsvpStatus(int userId, int eventId, boolean isAttending){
         eventBusinessLayer.setEventRsvpStatus(userId, eventId, isAttending);
    }
}
