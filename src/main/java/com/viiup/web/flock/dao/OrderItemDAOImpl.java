package com.viiup.web.flock.dao;

import com.viiup.web.flock.jdbc.OrderItemRowMapper;
import com.viiup.web.flock.jdbc.ShoppingCartRowMapper;
import com.viiup.web.flock.models.OrderItem;
import com.viiup.web.flock.models.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by HP on 2/24/2015.
 */
@Service
public class OrderItemDAOImpl implements OrderItemDAO {

    @Autowired
    DataSource dataSource;


    @Override
    public OrderItem getOrderItem(int orderItemID){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql ="SELECT order_item_id, order_id, product_id, quantity, price_paid, order_item_sub_total  FROM order_item WHERE order_item_id = ?";

        OrderItem orderItem = jdbcTemplate.queryForObject(sql, new Object[] { orderItemID}, new OrderItemRowMapper());

        return orderItem;
    }

    @Override
    public void insertOrderItem (OrderItem orderItem) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = new String();

        sql = "SELECT COUNT(*) FROM order_item WHERE order_id = ? AND product_id = ?";

        int x = jdbcTemplate.queryForObject(sql, new Object[] { orderItem.getOrderID(), orderItem.getProductID() }, Integer.class);

        if (x > 0){
            sql = "UPDATE order_item " +
                  "   SET quantity = quantity + ?, " +
                  "       price_paid = ?, " +
                  "       order_item_sub_total = ? * (quantity + ?) " +
                  " WHERE order_id = ? " +
                  "   AND product_id = ?";
            jdbcTemplate.update(sql, new Object[]{orderItem.getQuantity(), orderItem.getPricePaid(), orderItem.getPricePaid(), orderItem.getQuantity(), orderItem.getOrderID(), orderItem.getProductID()});
        }
        else {
            sql = "INSERT INTO order_item (order_id, product_id, quantity, price_paid, order_item_sub_total) " +
                  "VALUES(?,?,?,?,?)";
            jdbcTemplate.update(sql, new Object[]{orderItem.getOrderID(), orderItem.getProductID(), orderItem.getQuantity(), orderItem.getPricePaid(), orderItem.getOrderItemSubTotal()});
        }
    }

    @Override
    public void updateOrderItem (OrderItem orderItem){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "UPDATE order_item " +
                     "   SET order_id = ?, product_id =?, quantity = ?, price_paid = ?, order_item_sub_total = ? " +
                     " WHERE order_item_id= ?";

        jdbcTemplate.update(sql, new Object[]{orderItem.getOrderID(), orderItem.getProductID(), orderItem.getQuantity(), orderItem.getPricePaid(), orderItem.getOrderItemSubTotal(), orderItem.getOrderItemID()});
    }

    @Override
    public List<OrderItem> getOrderItemList(int orderID){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "SELECT order_item_id, order_id, product_id, quantity, price_paid, order_item_sub_total  FROM order_item WHERE order_id = ?";

        List orderItemList = jdbcTemplate.query(sql, new Integer[] {orderID}, new OrderItemRowMapper());

        return orderItemList;
    }


    @Override
    public List<ShoppingCart> getShoppingCartList(int orderID){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "SELECT order_item_id, " +
                     "       order_id, " +
                     "       product_id, " +
                     "       quantity, " +
                     "       price_paid, " +
                     "       order_item_sub_total, " +
                     "       product_name, " +
                     "       product_description, " +
                     "       price, " +
                     "       image_file_location, " +
                     "       image_file_name, " +
                     "       manufacturer_id, " +
                     "       manufacturer_name " +
                     "  FROM shopping_cart_view " +
                     " WHERE order_id = ?";

        List shoppingCartList = jdbcTemplate.query(sql, new Integer[] {orderID}, new ShoppingCartRowMapper());

        return shoppingCartList;
    }

    @Override
    public void deleteOrderItem (int orderItemID){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "DELETE FROM order_item WHERE order_item_id= ?";

        jdbcTemplate.update(sql,new Integer[] {orderItemID});
    }
}
