package com.viiup.web.flock.jdbc;

import com.viiup.web.flock.models.GroupModel;
import com.viiup.web.flock.models.UserModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by amoyeen on 2/20/2015.
 */
public class GroupRowMapper implements RowMapper<GroupModel> {
    @Override
    public GroupModel mapRow(ResultSet resultSet, int line) throws SQLException {
        GroupExtractor groupExtractor = new GroupExtractor();
        return groupExtractor.extractData(resultSet);
    }
}