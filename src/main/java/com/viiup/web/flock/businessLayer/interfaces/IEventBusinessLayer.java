package com.viiup.web.flock.businessLayer.interfaces;

import com.viiup.web.flock.models.EventModel;
import com.viiup.web.flock.models.RefEventCategoryModel;
import com.viiup.web.flock.models.RefStateModel;
import com.viiup.web.flock.models.UserEventModel;

import java.util.List;

/**
 * Created by AbdullahMoyeen on 2/7/16.
 */
public interface IEventBusinessLayer {

    List<RefStateModel> getRefStateList();

    List<UserEventModel> getEventsByUserId(int userId);

    void setEventRsvpStatus(int userId, int eventId, boolean isAttending);

    void insertEvent(EventModel event);
    void updateEvent(EventModel event);
    EventModel getEventByEventId(int eventId);

    List<RefEventCategoryModel> getRefEventCategoryList();
}
