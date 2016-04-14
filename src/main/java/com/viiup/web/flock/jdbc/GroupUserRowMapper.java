package com.viiup.web.flock.jdbc;

import com.viiup.web.flock.models.GroupUserModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by AbdullahMoyeen on 1/25/2016.
 */
public class GroupUserRowMapper implements RowMapper<GroupUserModel> {
    @Override
    public GroupUserModel mapRow(ResultSet resultSet, int line) throws SQLException {
        GroupUserExtractor groupUserExtractor = new GroupUserExtractor();
        return groupUserExtractor.extractData(resultSet);
    }
}