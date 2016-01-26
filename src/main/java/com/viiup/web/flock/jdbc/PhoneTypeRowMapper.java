package com.viiup.web.flock.jdbc;

import com.viiup.web.flock.models.PhoneType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by amoyeen on 3/1/15.
 */
public class PhoneTypeRowMapper implements RowMapper<PhoneType> {
    @Override
    public PhoneType mapRow(ResultSet resultSet, int line) throws SQLException {
        PhoneTypeExtractor phoneTypeExtractor = new PhoneTypeExtractor();
        return phoneTypeExtractor.extractData(resultSet);
    }
}
