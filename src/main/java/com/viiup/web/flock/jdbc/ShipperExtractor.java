package com.viiup.web.flock.jdbc;

import com.viiup.web.flock.models.Shipper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by HP on 2/28/2015.
 */
public class ShipperExtractor implements ResultSetExtractor<Shipper> {

    public Shipper extractData(ResultSet resultSet) throws SQLException, DataAccessException {

        Shipper shipper = new Shipper();
        shipper.setShipperID(resultSet.getInt(1));
        shipper.setShipperName(resultSet.getString(2));

        return shipper;
    }
}