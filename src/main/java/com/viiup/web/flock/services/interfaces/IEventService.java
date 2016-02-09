package com.viiup.web.flock.services.interfaces;

import com.viiup.web.flock.models.*;

import java.util.List;

/**
 * Created by MP on 1/27/2016.
 */
public interface IEventService {

    List<RefStateModel> getRefStateList();
    void insertEvent(EventModel event);
    void updateEvent(EventModel event);
    EventModel getEventByEventId(int eventId);
}
