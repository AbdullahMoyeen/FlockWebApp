package com.viiup.web.flock.jdbc;


import com.viiup.web.flock.models.UserRoleModel;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by amoyeen on 1/25/2016.
 */
public class UserRoleExtractor implements ResultSetExtractor<UserRoleModel>{

    public UserRoleModel extractData(ResultSet resultSet) throws SQLException, DataAccessException{

        UserRoleModel userRole = new UserRoleModel();

        userRole.setUserId(resultSet.getInt(1));
        userRole.setCreateUser(resultSet.getString(2));
        userRole.setCreateDate(resultSet.getDate(3));
        userRole.setUpdateUser(resultSet.getString(4));
        userRole.setUpdateDate(resultSet.getDate(5));
        userRole.setUserId(resultSet.getInt(6));
        userRole.setRoleName(resultSet.getString(7));

        return userRole;
    }
}