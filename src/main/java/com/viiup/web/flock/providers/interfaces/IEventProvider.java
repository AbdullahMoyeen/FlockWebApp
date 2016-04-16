package com.viiup.web.flock.providers.interfaces;

import com.viiup.web.flock.models.*;

import java.util.List;

/**
 * Created by HP on 1/27/2016.
 */
public interface IEventProvider {

    List<RefStateModel> getRefStateList();

    List<UserEventModel> getEventsByUserId(int userId);

    void insertEventUserRsvp(EventUserRsvpModel eventUserRsvp);

    void deleteEventUserRsvp(int userId, int eventId);

    Boolean isEventUserRsvpExist(int userId, int eventId);

    void insertEvent(EventModel event);
    void updateEvent(EventModel event);

    List<RefEventCategoryModel> getRefEventCategoryList();

    EventModel getEventByEventId(int eventID);
}
