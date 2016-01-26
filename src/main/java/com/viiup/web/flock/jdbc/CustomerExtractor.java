package com.viiup.web.flock.jdbc;


import com.viiup.web.flock.models.Customer;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

/**
 * Created by amoyeen on 2/20/2015.
 */
public class CustomerExtractor implements ResultSetExtractor<Customer>{

    public Customer extractData(ResultSet resultSet) throws SQLException, DataAccessException{

        Customer customer = new Customer();

        customer.setCustomerID(resultSet.getInt(1));
        customer.setFirstName(resultSet.getString(2));
        customer.setLastName(resultSet.getString(3));
        customer.setEmailAddress(resultSet.getString(4));
        customer.setPassword(resultSet.getString(5));
        customer.setRegistrationDate(resultSet.getDate(6));

        return customer;
    }
}
