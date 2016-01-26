package com.viiup.web.flock.jdbc;

import com.viiup.web.flock.models.CustomerPhone;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by amoyeen on 3/1/15.
 */
public class CustomerPhoneRowMapper implements RowMapper<CustomerPhone> {
    @Override
    public CustomerPhone mapRow(ResultSet resultSet, int line) throws SQLException {
        CustomerPhoneExtractor customerPhoneExtractor = new CustomerPhoneExtractor();
        return customerPhoneExtractor.extractData(resultSet);
    }
}
