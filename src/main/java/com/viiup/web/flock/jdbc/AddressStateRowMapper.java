package com.viiup.web.flock.jdbc;

import com.viiup.web.flock.models.AddressState;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by amoyeen on 2/27/15.
 */
public class AddressStateRowMapper implements RowMapper<AddressState> {
    @Override
    public AddressState mapRow(ResultSet resultSet, int line) throws SQLException {
        AddressStateExtractor addressStateExtractor = new AddressStateExtractor();
        return addressStateExtractor.extractData(resultSet);
    }
}
