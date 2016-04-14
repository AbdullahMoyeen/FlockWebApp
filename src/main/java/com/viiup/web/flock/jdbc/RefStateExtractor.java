package com.viiup.web.flock.jdbc;

import com.viiup.web.flock.models.RefStateModel;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by AbdullahMoyeen on 1/27/2016.
 */
public class RefStateExtractor implements ResultSetExtractor<RefStateModel> {

    public RefStateModel extractData(ResultSet resultSet) throws SQLException, DataAccessException {

        RefStateModel refState = new RefStateModel();

        refState.setStateCode(resultSet.getString(1));
        refState.setStateName(resultSet.getString(2));
        refState.setStateCodeAndName(resultSet.getString(3));

        return refState;
    }
}
