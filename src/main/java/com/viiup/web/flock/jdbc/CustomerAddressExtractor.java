package com.viiup.web.flock.jdbc;

import com.viiup.web.flock.models.CustomerAddress;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

/**
 * Created by amoyeen on 2/26/2015.
 */
public class CustomerAddressExtractor implements ResultSetExtractor<CustomerAddress> {

    public CustomerAddress extractData(ResultSet resultSet) throws SQLException, DataAccessException{

        CustomerAddress customerAddress = new CustomerAddress();

        customerAddress.setAddressID(resultSet.getInt(1));
        customerAddress.setCustomerID(resultSet.getInt(2));
        customerAddress.setAddressLine1(resultSet.getString(3));
        customerAddress.setAddressLine2(resultSet.getString(4));
        customerAddress.setCity(resultSet.getString(5));
        customerAddress.setStateCode(resultSet.getString(6));
        customerAddress.setPostalCode(resultSet.getString(7));
        customerAddress.setFullAddress(resultSet.getString(8));

        return customerAddress;
    }
}
