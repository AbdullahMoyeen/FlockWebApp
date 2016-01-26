package com.viiup.web.flock.jdbc;

import com.viiup.web.flock.models.ShippingSpeed;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by HP on 2/26/2015.
 */
public class ShippingRowMapper implements RowMapper<ShippingSpeed> {

    @Override
    public ShippingSpeed mapRow(ResultSet resultSet, int line) throws SQLException {
        ShippingExtractor shippingExtractor = new ShippingExtractor();
        return shippingExtractor.extractData(resultSet);
    }

}
