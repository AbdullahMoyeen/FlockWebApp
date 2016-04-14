package com.viiup.web.flock.providers.interfaces;

import com.viiup.web.flock.models.*;

import java.util.List;

/**
 * Created by HP on 1/27/2016.
 */
public interface IEventProvider {

    List<RefStateModel> getRefStateList();
    void insertEvent(EventModel event);
    void updateEvent(EventModel event);
    EventModel getEventByEventId(int eventID);
}
