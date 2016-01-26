package com.viiup.web.flock.jdbc;

import com.viiup.web.flock.models.Customer;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by amoyeen on 2/20/2015.
 */
public class CustomerRowMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet resultSet, int line) throws SQLException {
        CustomerExtractor customerExtractor = new CustomerExtractor();
        return customerExtractor.extractData(resultSet);
    }
}