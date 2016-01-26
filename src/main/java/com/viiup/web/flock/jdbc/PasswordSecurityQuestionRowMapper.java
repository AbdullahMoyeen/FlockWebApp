package com.viiup.web.flock.jdbc;

import com.viiup.web.flock.models.PasswordSecurityQuestion;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by amoyeen on 2/23/15.
 */
public class PasswordSecurityQuestionRowMapper implements RowMapper<PasswordSecurityQuestion> {
    @Override
    public PasswordSecurityQuestion mapRow(ResultSet resultSet, int line) throws SQLException {
        PasswordSecurityQuestionExtractor passwordSecurityQuestionExtractor = new PasswordSecurityQuestionExtractor();
        return passwordSecurityQuestionExtractor.extractData(resultSet);
    }
}
