package com.viiup.web.flock.jdbc;

import com.viiup.web.flock.models.Shipper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by HP on 2/28/2015.
 */
public class ShipperRowMapper implements RowMapper<Shipper> {

    @Override
    public Shipper mapRow(ResultSet resultSet, int line) throws SQLException {
        ShipperExtractor shipperExtractor = new ShipperExtractor();
        return shipperExtractor.extractData(resultSet);
    }

}

