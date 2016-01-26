package com.viiup.web.flock.jdbc;

import com.viiup.web.flock.models.PasswordSecurity;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by amoyeen on 2/22/15.
 */
public class PasswordSecurityRowMapper implements RowMapper<PasswordSecurity> {
    @Override
    public PasswordSecurity mapRow(ResultSet resultSet, int line) throws SQLException {
        PasswordSecurityExtractor passwordSecurityExtractor = new PasswordSecurityExtractor();
        return passwordSecurityExtractor.extractData(resultSet);
    }
}
