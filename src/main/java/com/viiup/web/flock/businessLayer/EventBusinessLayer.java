package com.viiup.web.flock.businessLayer;

import com.viiup.web.flock.businessLayer.interfaces.IEventBusinessLayer;
import com.viiup.web.flock.models.EventModel;
import com.viiup.web.flock.models.RefStateModel;
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
