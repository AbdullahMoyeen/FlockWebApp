package com.viiup.web.flock.jdbc;

import com.viiup.web.flock.models.AddressState;
import com.viiup.web.flock.models.RefState;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by amoyeen on 2/27/15.
 */
public class RefStateRowMapper implements RowMapper<RefState> {
    @Override
    public RefState mapRow(ResultSet resultSet, int line) throws SQLException {
        RefStateExtractor refStateExtractor = new RefStateExtractor();
        return refStateExtractor.extractData(resultSet);
    }
}
