package com.viiup.web.flock.jdbc;

import com.viiup.web.flock.models.CustomerAddress;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by amoyeen on 2/26/2015.
 */
public class CustomerAddressRowMapper implements RowMapper<CustomerAddress> {
    @Override
    public CustomerAddress mapRow(ResultSet resultSet, int line) throws SQLException {
        CustomerAddressExtractor customerAddressExtractor = new CustomerAddressExtractor();
        return customerAddressExtractor.extractData(resultSet);
    }
}
