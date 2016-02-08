package com.viiup.web.flock.jdbc;


import com.viiup.web.flock.models.UserModel;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by amoyeen on 1/25/2016.
 */
public class UserExtractor implements ResultSetExtractor<UserModel>{

    public UserModel extractData(ResultSet resultSet) throws SQLException, DataAccessException{

        UserModel user = new UserModel();

        user.setUserId(resultSet.getInt(1));
        user.setCreateUser(resultSet.getString(2));
        user.setCreateDate(resultSet.getDate(3));
        user.setUpdateUser(resultSet.getString(4));
        user.setUpdateDate(resultSet.getDate(5));
        user.setFirstName(resultSet.getString(6));
        user.setLastName(resultSet.getString(7));
        user.setEmailAddress(resultSet.getString(8));
        user.setPassword(resultSet.getString(9));
        user.setSalt(resultSet.getString(10));
        user.setFailedLoginCount(resultSet.getInt(11));
        user.setAccountStatus(resultSet.getString(12));
        user.setPasswordExpired(resultSet.getString(13).equals("Y"));

        return user;
    }
}