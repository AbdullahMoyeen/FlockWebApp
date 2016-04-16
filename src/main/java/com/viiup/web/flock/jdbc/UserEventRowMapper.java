package com.viiup.web.flock.jdbc;

import com.viiup.web.flock.models.EventModel;
import com.viiup.web.flock.models.UserEventModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by MP on 1/25/2016.
 */
public class UserEventRowMapper implements RowMapper<UserEventModel> {

    @Override
    public UserEventModel mapRow(ResultSet resultSet, int line) throws SQLException {
        UserEventExtractor userEventExtractor = new UserEventExtractor();
        return userEventExtractor.extractData(resultSet);
    }
}
