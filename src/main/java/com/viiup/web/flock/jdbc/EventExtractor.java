package com.viiup.web.flock.jdbc;

import com.viiup.web.flock.models.EventModel;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by HP on 2/25/2015.
 */
public class EventExtractor implements ResultSetExtractor<EventModel> {

    public EventModel extractData(ResultSet resultSet) throws SQLException, DataAccessException {

        EventModel event = new EventModel();

        event.setEventId(resultSet.getInt(1));
        event.setGroupId(resultSet.getInt(2));
        event.setEventName(resultSet.getString(3));
        event.setEventDescription(resultSet.getString(4));
        event.setEventStartDatetime(resultSet.getTimestamp(5));
        event.setEventEndDatetime(resultSet.getTimestamp(6));
        event.setEventAddressLine1(resultSet.getString(7));
        event.setEventAddressLine2(resultSet.getString(8));
        event.setEventCity(resultSet.getString(9));
        event.setEventStateCode(resultSet.getString(10));
        event.setEventPostalCode(resultSet.getString(11));
        event.setEventKeywords(resultSet.getString(12));
        event.setEventLatitude(resultSet.getFloat(13));
        event.setEventLongitude(resultSet.getFloat(14));
        event.setPrivateEvent(resultSet.getString(15).equals("Y"));
        event.setCreateUser(resultSet.getString(16));
        event.setCreateDate(resultSet.getTimestamp(17));
        event.setUpdateUser(resultSet.getString(18));
        event.setUpdateDate(resultSet.getTimestamp(19));

        return event;
    }
}
