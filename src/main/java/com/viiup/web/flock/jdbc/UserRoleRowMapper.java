package com.viiup.web.flock.jdbc;

import com.viiup.web.flock.models.UserRoleModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by amoyeen on 2/20/2015.
 */
public class UserRoleRowMapper implements RowMapper<UserRoleModel> {
    @Override
    public UserRoleModel mapRow(ResultSet resultSet, int line) throws SQLException {
        UserRoleExtractor userRoleExtractor = new UserRoleExtractor();
        return userRoleExtractor.extractData(resultSet);
    }
}