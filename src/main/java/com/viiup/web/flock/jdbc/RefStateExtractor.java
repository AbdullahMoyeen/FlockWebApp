package com.viiup.web.flock.jdbc;

import com.viiup.web.flock.models.AddressState;
import com.viiup.web.flock.models.RefState;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by amoyeen on 2/27/15.
 */
public class RefStateExtractor implements ResultSetExtractor<RefState> {

    public RefState extractData(ResultSet resultSet) throws SQLException, DataAccessException {

        RefState refState = new RefState();

        refState.setStateCode(resultSet.getString(1));
        refState.setStateName(resultSet.getString(2));
        refState.setStateCodeAndName(resultSet.getString(3));

        return refState;
    }
}
