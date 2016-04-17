package com.viiup.web.flock.jdbc;

import com.viiup.web.flock.models.UserEventModel;
import com.viiup.web.flock.models.UserGroupModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by MP on 1/25/2016.
 */
public class UserGroupRowMapper implements RowMapper<UserGroupModel> {

    @Override
    public UserGroupModel mapRow(ResultSet resultSet, int line) throws SQLException {
        UserGroupExtractor userGroupExtractor = new UserGroupExtractor();
        return userGroupExtractor.extractData(resultSet);
    }
}
