package com.viiup.web.flock.jdbc;


import com.viiup.web.flock.models.GroupModel;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by AbdullahMoyeen on 1/25/2016.
 */
public class GroupExtractor implements ResultSetExtractor<GroupModel>{

    public GroupModel extractData(ResultSet resultSet) throws SQLException, DataAccessException{

        GroupModel group = new GroupModel();

        group.setGroupId(resultSet.getInt(1));
        group.setCreateUser(resultSet.getString(2));
        group.setCreateDate(resultSet.getDate(3));
        group.setUpdateUser(resultSet.getString(4));
        group.setUpdateDate(resultSet.getDate(5));
        group.setGroupName(resultSet.getString(6));
        group.setGroupDescription(resultSet.getString(7));
        group.setGroupCategory(resultSet.getString(8));
        group.setActiveMemberCount(resultSet.getInt(9));
        group.setPendingMemberCount(resultSet.getInt(10));
        group.setUpcomingEventCount(resultSet.getInt(11));

        return group;
    }
}