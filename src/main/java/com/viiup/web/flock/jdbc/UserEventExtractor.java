package com.viiup.web.flock.jdbc;

import com.viiup.web.flock.models.EventModel;
import com.viiup.web.flock.models.UserEventModel;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by MP on 1/25/2016.
 */
public class UserEventExtractor implements ResultSetExtractor<UserEventModel> {

    public UserEventModel extractData(ResultSet resultSet) throws SQLException, DataAccessException {

        UserEventModel userEvent = new UserEventModel();
        userEvent.event = new EventModel();

        userEvent.event.setEventId(resultSet.getInt(1));
        userEvent.event.setGroupId(resultSet.getInt(2));
        userEvent.event.setEventName(resultSet.getString(3));
        userEvent.event.setEventDescription(resultSet.getString(4));
        userEvent.event.setEventStartDatetime(resultSet.getTimestamp(5));
        userEvent.event.setEventEndDatetime(resultSet.getTimestamp(6));
        userEvent.event.setEventAddressLine1(resultSet.getString(7));
        userEvent.event.setEventAddressLine2(resultSet.getString(8));
        userEvent.event.setEventCity(resultSet.getString(9));
        userEvent.event.setEventStateCode(resultSet.getString(10));
        userEvent.event.setEventPostalCode(resultSet.getString(11));
        userEvent.event.setEventKeywords(resultSet.getString(12));
        userEvent.event.setEventLatitude(resultSet.getFloat(13));
        userEvent.event.setEventLongitude(resultSet.getFloat(14));
        userEvent.event.setPrivateEvent(resultSet.getString(15).equals("Y"));
        userEvent.event.setCreateUser(resultSet.getString(16));
        userEvent.event.setCreateDate(resultSet.getTimestamp(17));
        userEvent.event.setUpdateUser(resultSet.getString(18));
        userEvent.event.setUpdateDate(resultSet.getTimestamp(19));
        userEvent.event.setEventCategory(resultSet.getString(20));
        userEvent.event.setAttendeeCount(resultSet.getInt(21));
        userEvent.setUserId(resultSet.getInt(22));
        userEvent.setIsAttending(resultSet.getBoolean(23));

        return userEvent;
    }
}
