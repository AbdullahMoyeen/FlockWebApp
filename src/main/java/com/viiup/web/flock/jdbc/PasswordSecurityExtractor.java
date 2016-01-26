package com.viiup.web.flock.jdbc;

import com.viiup.web.flock.models.PasswordSecurity;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by amoyeen on 2/22/15.
 */
public class PasswordSecurityExtractor implements ResultSetExtractor<PasswordSecurity> {

    public PasswordSecurity extractData(ResultSet resultSet) throws SQLException, DataAccessException {

        PasswordSecurity passwordSecurity = new PasswordSecurity();

        passwordSecurity.setCustomerID(resultSet.getInt(1));
        passwordSecurity.setEmailAddress(resultSet.getString(2));
        passwordSecurity.setSecurityQuestionID(resultSet.getInt(3));
        passwordSecurity.setSecurityQuestionText(resultSet.getString(4));
        passwordSecurity.setSecurityQuestionAnswer(resultSet.getString(5));

        return passwordSecurity;
    }
}
