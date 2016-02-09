package com.viiup.web.flock.jdbc;

import com.viiup.web.flock.models.UserRoleModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by AbdullahMoyeen on 1/25/2016.
 */
public class UserRoleRowMapper implements RowMapper<UserRoleModel> {
    @Override
    public UserRoleModel mapRow(ResultSet resultSet, int line) throws SQLException {
        UserRoleExtractor userRoleExtractor = new UserRoleExtractor();
        return userRoleExtractor.extractData(resultSet);
    }
}