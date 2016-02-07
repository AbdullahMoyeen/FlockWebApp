package com.viiup.web.flock.services;

import com.viiup.web.flock.models.*;

import java.util.List;

/**
 * Created by HP on 2/23/2015.
 */
public interface IEventService {

    List<RefStateModel> getRefStateList();
    void insertEvent(EventModel event);
    void updateEvent(EventModel event);
    EventModel getEventByEventId(int eventId);
}
