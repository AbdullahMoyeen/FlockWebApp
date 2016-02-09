package com.viiup.web.flock.businessLayer.interfaces;

import com.viiup.web.flock.models.EventModel;
import com.viiup.web.flock.models.RefStateModel;

import java.util.List;

/**
 * Created by AbdullahMoyeen on 2/7/16.
 */
public interface IEventBusinessLayer {

    List<RefStateModel> getRefStateList();
    void insertEvent(EventModel event);
    void updateEvent(EventModel event);
    EventModel getEventByEventId(int eventId);
}
