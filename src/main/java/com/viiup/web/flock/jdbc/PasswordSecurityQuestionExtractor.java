package com.viiup.web.flock.jdbc;

import com.viiup.web.flock.models.PasswordSecurityQuestion;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by amoyeen on 2/23/15.
 */
public class PasswordSecurityQuestionExtractor implements ResultSetExtractor<PasswordSecurityQuestion> {

    public PasswordSecurityQuestion extractData(ResultSet resultSet) throws SQLException, DataAccessException {

        PasswordSecurityQuestion passwordSecurityQuestion = new PasswordSecurityQuestion();

        passwordSecurityQuestion.setSecurityQuestionID(resultSet.getInt(1));
        passwordSecurityQuestion.setSecurityQuestionText(resultSet.getString(2));

        return passwordSecurityQuestion;
    }
}
