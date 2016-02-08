package com.viiup.web.flock.jdbc;

import com.viiup.web.flock.models.EventModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by MP on 1/25/2016.
 */
public class EventRowMapper implements RowMapper<EventModel> {

    @Override
    public EventModel mapRow(ResultSet resultSet, int line) throws SQLException {
        EventExtractor eventExtractor = new EventExtractor();
        return eventExtractor.extractData(resultSet);
    }
}
