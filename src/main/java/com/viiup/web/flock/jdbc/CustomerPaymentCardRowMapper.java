package com.viiup.web.flock.jdbc;

import com.viiup.web.flock.models.CustomerPaymentCard;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by amoyeen on 2/28/15.
 */
public class CustomerPaymentCardRowMapper implements RowMapper<CustomerPaymentCard> {
    @Override
    public CustomerPaymentCard mapRow(ResultSet resultSet, int line) throws SQLException {
        CustomerPaymentCardExtractor customerPaymentCardExtractor = new CustomerPaymentCardExtractor();
        return customerPaymentCardExtractor.extractData(resultSet);
    }
}
