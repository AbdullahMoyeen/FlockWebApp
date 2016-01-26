package com.viiup.web.flock.jdbc;

import com.viiup.web.flock.models.OrderItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by HP on 2/24/2015.
 */
public class OrderItemRowMapper implements RowMapper<OrderItem> {

    @Override
    public OrderItem mapRow(ResultSet resultSet, int line) throws SQLException {
        OrderItemExtractor orderItemExtractor = new OrderItemExtractor();
        return orderItemExtractor.extractData(resultSet);
    }
}

