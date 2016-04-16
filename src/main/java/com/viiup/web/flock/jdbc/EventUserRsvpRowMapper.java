package com.viiup.web.flock.jdbc;

import com.viiup.web.flock.models.EventModel;
import com.viiup.web.flock.models.EventUserRsvpModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by MP on 1/25/2016.
 */
public class EventUserRsvpRowMapper implements RowMapper<EventUserRsvpModel> {

    @Override
    public EventUserRsvpModel mapRow(ResultSet resultSet, int line) throws SQLException {
        EventUserRsvpExtractor eventUserRsvpExtractor = new EventUserRsvpExtractor();
        return eventUserRsvpExtractor.extractData(resultSet);
    }
}
