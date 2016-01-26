package com.viiup.web.flock.jdbc;

import com.viiup.web.flock.models.ShippingSpeed;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by HP on 2/26/2015.
 */
public class ShippingExtractor implements ResultSetExtractor<ShippingSpeed> {

    public ShippingSpeed extractData(ResultSet resultSet) throws SQLException, DataAccessException {

        ShippingSpeed shippingSpeed = new ShippingSpeed();
        shippingSpeed.setShippingSpeedCode(resultSet.getString(1));
        shippingSpeed.setShippingSpeedName(resultSet.getString(2));
        shippingSpeed.setShippingSpeedDescription(resultSet.getString(3));

        return shippingSpeed;
    }
}
