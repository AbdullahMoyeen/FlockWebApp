package com.viiup.web.flock.jdbc;


import com.viiup.web.flock.models.OrderItem;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by HP on 2/24/2015.
 */
public class OrderItemExtractor  implements ResultSetExtractor<OrderItem> {

    public OrderItem extractData(ResultSet resultSet) throws SQLException, DataAccessException {

        OrderItem orderItem = new OrderItem();
        orderItem.setOrderItemID(resultSet.getInt(1));
        orderItem.setOrderID(resultSet.getInt(2));
        orderItem.setProductID(resultSet.getInt(3));
        orderItem.setQuantity(resultSet.getInt(4));
        orderItem.setPricePaid(resultSet.getFloat(5));
        orderItem.setOrderItemSubTotal(resultSet.getFloat(6));

        return orderItem;
    }
}
