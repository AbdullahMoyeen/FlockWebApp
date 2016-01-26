package com.viiup.web.flock.jdbc;

import com.viiup.web.flock.models.ShoppingCart;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by HP on 2/25/2015.
 */
public class ShoppingCartRowMapper implements RowMapper<ShoppingCart> {

    @Override
    public ShoppingCart mapRow(ResultSet resultSet, int line) throws SQLException {
        ShoppingCartExtractor shoppingCartExtractor = new ShoppingCartExtractor();
        return shoppingCartExtractor.extractData(resultSet);
    }

}
