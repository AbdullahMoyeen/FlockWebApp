package com.viiup.web.flock.jdbc;


import com.viiup.web.flock.models.GroupUserModel;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by amoyeen on 1/25/2016.
 */
public class GroupUserExtractor implements ResultSetExtractor<GroupUserModel>{

    public GroupUserModel extractData(ResultSet resultSet) throws SQLException, DataAccessException{

        GroupUserModel groupUser = new GroupUserModel();

        groupUser.setGroupUserId(resultSet.getInt(1));
        groupUser.setCreateUser(resultSet.getString(2));
        groupUser.setCreateDate(resultSet.getDate(3));
        groupUser.setUpdateUser(resultSet.getString(4));
        groupUser.setUpdateDate(resultSet.getDate(5));
        groupUser.setGroupId(resultSet.getInt(6));
        groupUser.setUserId(resultSet.getInt(7));
        groupUser.setGroupMembershipStatus(resultSet.getString(8));
        groupUser.setGroupAdmin(resultSet.getString(9) == "Y");
        groupUser.setFirstName(resultSet.getString(10));
        groupUser.setLastName(resultSet.getString(11));
        groupUser.setEmailAddress(resultSet.getString(12));

        return groupUser;
    }
}