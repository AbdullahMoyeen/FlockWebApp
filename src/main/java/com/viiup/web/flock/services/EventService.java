package com.viiup.web.flock.services;

import com.viiup.web.flock.models.*;
import com.viiup.web.flock.providers.interfaces.IEventProvider;
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
    IEventProvider eventProvider;

    @Override
    public EventModel getEventByEventId(int eventId) {
        return eventProvider.getEventByEventId(eventId);
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
}
