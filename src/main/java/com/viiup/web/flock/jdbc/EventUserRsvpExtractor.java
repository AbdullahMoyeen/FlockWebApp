package com.viiup.web.flock.jdbc;

import com.viiup.web.flock.models.EventModel;
import com.viiup.web.flock.models.EventUserRsvpModel;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by MP on 1/25/2016.
 */
public class EventUserRsvpExtractor implements ResultSetExtractor<EventUserRsvpModel> {

    public EventUserRsvpModel extractData(ResultSet resultSet) throws SQLException, DataAccessException {

        EventUserRsvpModel eventUserRsvp = new EventUserRsvpModel();

        eventUserRsvp.setEventUserRsvpId(resultSet.getInt(1));
        eventUserRsvp.setCreateUser(resultSet.getString(2));
        eventUserRsvp.setCreateDate(resultSet.getTimestamp(3));
        eventUserRsvp.setUpdateUser(resultSet.getString(4));
        eventUserRsvp.setUpdateDate(resultSet.getTimestamp(5));
        eventUserRsvp.setUserId(resultSet.getInt(6));
        eventUserRsvp.setEventId(resultSet.getInt(7));
        eventUserRsvp.setRsvpTypeCode(resultSet.getString(8));

        return eventUserRsvp;
    }
}
