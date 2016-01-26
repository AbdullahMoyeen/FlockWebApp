package com.viiup.web.flock.jdbc;

import com.viiup.web.flock.models.PhoneType;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by amoyeen on 3/1/15.
 */
public class PhoneTypeExtractor implements ResultSetExtractor<PhoneType> {

    public PhoneType extractData(ResultSet resultSet) throws SQLException, DataAccessException {

        PhoneType phoneType = new PhoneType();

        phoneType.setPhoneTypeCode(resultSet.getString(1));
        phoneType.setPhoneTypeName(resultSet.getString(2));
        phoneType.setPhoneTypeDescription(resultSet.getString(3));

        return phoneType;
    }
}
