package com.viiup.web.flock.jdbc;

import com.viiup.web.flock.models.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by HP on 2/25/2015.
 */
public class OrderRowMapper implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet resultSet, int line) throws SQLException {
        OrderExtractor orderExtractor = new OrderExtractor();
        return orderExtractor.extractData(resultSet);
    }
}
