package com.viiup.web.flock.jdbc;

import com.viiup.web.flock.models.CustomerPhone;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by amoyeen on 3/1/15.
 */
public class CustomerPhoneExtractor implements ResultSetExtractor<CustomerPhone> {

    public CustomerPhone extractData(ResultSet resultSet) throws SQLException, DataAccessException {

        CustomerPhone customerPhone = new CustomerPhone();

        customerPhone.setPhoneID(resultSet.getInt(1));
        customerPhone.setCustomerID(resultSet.getInt(2));
        customerPhone.setPhoneTypeCode(resultSet.getString(3));
        customerPhone.setPhoneTypeName(resultSet.getString(4));
        customerPhone.setPhoneNumber(resultSet.getString(5));

        return customerPhone;
    }
}