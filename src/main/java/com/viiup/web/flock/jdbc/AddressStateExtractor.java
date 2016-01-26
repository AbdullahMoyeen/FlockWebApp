package com.viiup.web.flock.jdbc;

import com.viiup.web.flock.models.AddressState;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by amoyeen on 2/27/15.
 */
public class AddressStateExtractor implements ResultSetExtractor<AddressState> {

    public AddressState extractData(ResultSet resultSet) throws SQLException, DataAccessException {

        AddressState addressState = new AddressState();

        addressState.setStateCode(resultSet.getString(1));
        addressState.setStateName(resultSet.getString(2));
        addressState.setShippingAllowedInd(resultSet.getString(3));
        addressState.setStateCodeAndName(resultSet.getString(4));

        return addressState;
    }
}
