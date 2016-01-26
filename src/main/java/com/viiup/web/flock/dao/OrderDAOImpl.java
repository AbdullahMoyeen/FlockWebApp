package com.viiup.web.flock.dao;

import com.viiup.web.flock.jdbc.OrderRowMapper;
import com.viiup.web.flock.jdbc.ShippingRowMapper;
import com.viiup.web.flock.models.Order;
import com.viiup.web.flock.models.ShippingSpeed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

/**
 * Created by HP on 2/23/2015.
 */
@Service
public class OrderDAOImpl implements OrderDAO{

    @Autowired
    DataSource dataSource;

    @Override
    public int insertOrder(Order order){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "INSERT INTO order_header(customer_id, order_status_code) " +
                     "VALUES (?, UPPER(?))";

        final String finalSql = sql;
        final Order finalOrder = order;
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(finalSql, new String[]{"order_id"});
                        if (finalOrder.getCustomerID() > 0){
                            ps.setInt(1, finalOrder.getCustomerID());
                        }
                        else {
                            ps.setNull(1, Types.INTEGER);
                        };
                        ps.setString(2, finalOrder.getOrderStatusCode());
                        return ps;
                    }
                },
                keyHolder
        );

        int orderID = keyHolder.getKey().intValue();

        return orderID;
    }

    @Override
    public List<ShippingSpeed> getShippingSpeedList(){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "SELECT * FROM shipping_speed";
        List shippingSpeedList = jdbcTemplate.query(sql, new ShippingRowMapper());

        return shippingSpeedList;
    }

    @Override
    public void updateOrder (final Order order){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        boolean b;
        String sql = "UPDATE order_header " +
                     "   SET customer_id = ?, " +
                     "       order_status_code = UPPER(?), " +
                     "       order_created_date = ?, " +
                     "       order_placed_date = ?, " +
                     "       payment_card_id = ?, " +
                     "       order_sub_total = ?," +
                     "       state_tax_rate_id = ?, " +
                     "       tax_total = ?, " +
                     "       shipper_id = ?, " +
                     "       shipping_speed_code = UPPER(?), " +
                     "       shipping_total = ?, " +
                     "       order_shipped_date = ?, " +
                     "       tracking_number = ?," +
                     "       shipping_address_line1 = UPPER(?), " +
                     "       shipping_address_line2 = UPPER(?), " +
                     "       shipping_city = UPPER(?), " +
                     "       shipping_state_code = UPPER(?), " +
                     "       shipping_postal_code = UPPER(?), " +
                     "       shipping_to_name = UPPER(?), " +
                     "       merged_to_order_id = ? " +
                     " WHERE order_id = ?";

        b = jdbcTemplate.execute(sql,new PreparedStatementCallback<Boolean>(){
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                if (order.getCustomerID() > 0){ps.setInt(1, order.getCustomerID());} else {ps.setNull(1, Types.INTEGER);}
                ps.setString(2, order.getOrderStatusCode());
                ps.setTimestamp(3,new java.sql.Timestamp(order.getOrderCreated().getTime()));
                if (order.getOrderPlaced() == null){ps.setNull(4, Types.TIMESTAMP);} else {ps.setTimestamp(4,new java.sql.Timestamp(order.getOrderPlaced().getTime()));}
                if (order.getPaymentCardID() > 0){ps.setInt(5, order.getPaymentCardID());} else {ps.setNull(5, Types.INTEGER);}
                if (order.getOrderSubTotal() > 0){ ps.setFloat(6, order.getOrderSubTotal());} else {ps.setNull(6, Types.FLOAT);}
                if (order.getStateTaxRateID() > 0){ps.setInt(7, order.getStateTaxRateID());} else {ps.setNull(7, Types.INTEGER);}
                if (order.getTaxTotal() > 0){ ps.setFloat(8, order.getTaxTotal());} else {ps.setNull(8, Types.FLOAT);}
                if (order.getShipperID() > 0){ps.setInt(9, order.getShipperID());} else {ps.setNull(9, Types.INTEGER);}
                if (order.getShippingSpeedCode() == null){ps.setNull(10, Types.VARCHAR);} else {ps.setString(10, order.getShippingSpeedCode()); }
                if (order.getShippingTotal() > 0){ ps.setFloat(11, order.getShippingTotal());} else {ps.setNull(11, Types.FLOAT);}
                if (order.getOrderShipped() == null){ps.setNull(12, Types.TIMESTAMP);} else {ps.setTimestamp(12, new java.sql.Timestamp(order.getOrderShipped().getTime()));}
                if (order.getTrackingNumber() == null){ps.setNull(13, Types.VARCHAR);} else {ps.setString(13, order.getTrackingNumber());}
                if (order.getShippingAddressLine1() == null){ps.setNull(14, Types.VARCHAR);} else {ps.setString(14, order.getShippingAddressLine1()); }
                if (order.getShippingAddressLine2() == null){ps.setNull(15, Types.VARCHAR);} else {ps.setString(15, order.getShippingAddressLine2()); }
                if (order.getShippingCity() == null){ps.setNull(16, Types.VARCHAR);} else {ps.setString(16, order.getShippingCity()); }
                if (order.getShippingStateCode() == null){ps.setNull(17, Types.VARCHAR);} else {ps.setString(17, order.getShippingStateCode()); }
                if (order.getShippingPostalCode() == null){ps.setNull(18, Types.VARCHAR);} else {ps.setString(18, order.getShippingPostalCode()); }
                if (order.getShippingToName() == null){ps.setNull(19, Types.VARCHAR);} else {ps.setString(19, order.getShippingToName()); }
                if (order.getMergedToOrderID() > 0){ps.setInt(20, order.getMergedToOrderID());} else {ps.setNull(20, Types.INTEGER);}
                if (order.getOrderID() > 0){ps.setInt(21, order.getOrderID());} else {ps.setNull(21, Types.INTEGER);}

                return ps.execute();
            }
        });

    }

    @Override
    public void mergeOrder(Order survivingOrder, Order victimOrder){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = new String();

        //Update Surviving Order Items for which there are matching Victim Order Items
        sql = "UPDATE order_item sur, order_item vic " +
              "   SET sur.quantity = sur.quantity + vic.quantity, sur.order_item_sub_total = sur.price_paid * (sur.quantity + vic.quantity) " +
              " WHERE sur.order_id = ? " +
              "   AND vic.order_id = ? " +
              "   AND sur.product_id = vic.product_id";

        jdbcTemplate.update(sql, new Object[]{survivingOrder.getOrderID(), victimOrder.getOrderID()});

        //Copy Victim Order Items for which there are no matching Surviving Order Items
        sql = "INSERT INTO order_item (order_id, product_id, quantity, price_paid, order_item_sub_total) " +
              "SELECT ?, vic.product_id, vic.quantity, vic.price_paid, vic.order_item_sub_total " +
              "  FROM order_item vic " +
              " WHERE vic.order_id = ? " +
              "   AND NOT EXISTS (SELECT * " +
              "                     FROM order_item sur " +
              "                    WHERE sur.order_id = ? " +
              "                      AND sur.product_id = vic.product_id)";

        jdbcTemplate.update(sql, new Object[]{survivingOrder.getOrderID(), victimOrder.getOrderID(), survivingOrder.getOrderID()});

        sql = "UPDATE order_header " +
              "   SET customer_id = ? " +
              " WHERE order_id = ?";

        jdbcTemplate.update(sql, new Object[]{victimOrder.getCustomerID(), survivingOrder.getOrderID()});

        sql = "UPDATE order_header " +
              "   SET order_status_code = 'M', merged_to_order_id = ? " +
              " WHERE order_id = ?";

        jdbcTemplate.update(sql, new Object[]{survivingOrder.getOrderID(), victimOrder.getOrderID()});
    }

    @Override
    public boolean shippingAllowed(String stateCode){

        int x;

        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

            String sql = "SELECT COUNT(*) " +
                         "  FROM state " +
                         " WHERE state_code = UPPER(?) " +
                         "   AND shipping_allowed_ind = 'Y'";

            x = jdbcTemplate.queryForObject(sql, new Object[]{ stateCode }, Integer.class);
        }
        catch (EmptyResultDataAccessException e) {
            x = 0;
        }

        if(x > 0) {
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public int getStateTaxRateID(String stateCode){

        try{
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

            String sql = "SELECT state_tax_rate_id " +
                         "  FROM state_tax_rate " +
                         " WHERE state_code = UPPER(?) ";

            return jdbcTemplate.queryForObject(sql, new Object[] { stateCode }, Integer.class);

        } catch (EmptyResultDataAccessException e) {
            return Integer.parseInt(null);
        }
    }

    @Override
    public Float getStateTaxRate(int stateTaxRateID){

        try{
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "SELECT IFNULL(tax_rate, 0) " +
                     "  FROM state_tax_rate " +
                     " WHERE state_tax_rate_id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[] { stateTaxRateID }, Float.class);

        } catch (EmptyResultDataAccessException e) {
            return Float.valueOf(0);
        }
    }

    @Override
    public Float getShippingRate(int shipperID, String shippingSpeedCode){

        try{
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "SELECT IFNULL(price, 0) " +
                     "  FROM shipping_rate " +
                     " WHERE shipper_id = ? " +
                     "   AND shipping_speed_code = UPPER(?)";

        return jdbcTemplate.queryForObject(sql, new Object[] { shipperID, shippingSpeedCode }, Float.class);

        } catch (EmptyResultDataAccessException e) {
            return Float.valueOf(0);
        }
    }

    @Override
    public Order getPendingOrder(int customerID) {

        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

            String sql = "SELECT order_id, " +
                         "       customer_id, " +
                         "       order_status_code, " +
                         "       order_created_date, " +
                         "       order_placed_date, " +
                         "       order_sub_total, " +
                         "       shipper_id, " +
                         "       shipping_speed_code, " +
                         "       shipping_total," +
                         "       order_shipped_date, " +
                         "       tracking_number, " +
                         "       tax_total, " +
                         "       payment_card_id, " +
                         "       state_tax_rate_id," +
                         "       shipping_address_line1, " +
                         "       shipping_address_line2, " +
                         "       shipping_city, " +
                         "       shipping_state_code, " +
                         "       shipping_postal_code, " +
                         "       merged_to_order_id, " +
                         "       order_item_count, " +
                         "       shipping_to_name, " +
                         "       payment_card_type_id, " +
                         "       payment_card_type_name, " +
                         "       payment_card_number, " +
                         "       payment_card_cvv, " +
                         "       payment_card_expiry, " +
                         "       payment_card_holder_name, " +
                         "       payment_card_address_line1, " +
                         "       payment_card_address_line2, " +
                         "       payment_card_city, " +
                         "       payment_card_state_code, " +
                         "       payment_card_postal_code, " +
                         "       display_ind, " +
                         "       order_line_count, " +
                         "       order_total " +
                         "  FROM order_header_view " +
                         " WHERE customer_id = ? " +
                         "   AND order_status_code ='P'";

            Order order = jdbcTemplate.queryForObject(sql, new Object[]{ customerID }, new OrderRowMapper());

            return order;

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Order> getOpenOrderListByCustomerID(int customerID){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "SELECT order_id, " +
                     "       customer_id, " +
                     "       order_status_code, " +
                     "       order_created_date, " +
                     "       order_placed_date, " +
                     "       order_sub_total, " +
                     "       shipper_id, " +
                     "       shipping_speed_code, " +
                     "       shipping_total," +
                     "       order_shipped_date, " +
                     "       tracking_number, " +
                     "       tax_total, " +
                     "       payment_card_id, " +
                     "       state_tax_rate_id," +
                     "       shipping_address_line1, " +
                     "       shipping_address_line2, " +
                     "       shipping_city, " +
                     "       shipping_state_code, " +
                     "       shipping_postal_code, " +
                     "       merged_to_order_id, " +
                     "       order_item_count, " +
                     "       shipping_to_name, " +
                     "       payment_card_type_id, " +
                     "       payment_card_type_name, " +
                     "       payment_card_number, " +
                     "       payment_card_cvv, " +
                     "       payment_card_expiry, " +
                     "       payment_card_holder_name, " +
                     "       payment_card_address_line1, " +
                     "       payment_card_address_line2, " +
                     "       payment_card_city, " +
                     "       payment_card_state_code, " +
                     "       payment_card_postal_code, " +
                     "       display_ind, " +
                     "       order_line_count, " +
                     "       order_total " +
                     "  FROM order_header_view " +
                     " WHERE customer_id = ? " +
                     "   AND order_status_code = 'C' " +
                     " ORDER BY order_created_date DESC";

        List orderList = jdbcTemplate.query(sql, new Object[] { customerID }, new OrderRowMapper());

        return orderList;
    }

    @Override
    public List<Order> getOrderHistoryByCustomerID(int customerID, int daysBefore){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "SELECT order_id, " +
                     "       customer_id, " +
                     "       order_status_code, " +
                     "       order_created_date, " +
                     "       order_placed_date, " +
                     "       order_sub_total, " +
                     "       shipper_id, " +
                     "       shipping_speed_code, " +
                     "       shipping_total," +
                     "       order_shipped_date, " +
                     "       tracking_number, " +
                     "       tax_total, " +
                     "       payment_card_id, " +
                     "       state_tax_rate_id," +
                     "       shipping_address_line1, " +
                     "       shipping_address_line2, " +
                     "       shipping_city, " +
                     "       shipping_state_code, " +
                     "       shipping_postal_code, " +
                     "       merged_to_order_id, " +
                     "       order_item_count, " +
                     "       shipping_to_name, " +
                     "       payment_card_type_id, " +
                     "       payment_card_type_name, " +
                     "       payment_card_number, " +
                     "       payment_card_cvv, " +
                     "       payment_card_expiry, " +
                     "       payment_card_holder_name, " +
                     "       payment_card_address_line1, " +
                     "       payment_card_address_line2, " +
                     "       payment_card_city, " +
                     "       payment_card_state_code, " +
                     "       payment_card_postal_code, " +
                     "       display_ind, " +
                     "       order_line_count, " +
                     "       order_total " +
                     "  FROM order_header_view " +
                     " WHERE customer_id = ? " +
                     "   AND order_status_code IN ('C', 'S') " +
                     "   AND order_placed_date > DATE_SUB(now(),INTERVAL ? DAY)" +
                     " ORDER BY order_created_date DESC";

        List orderList = jdbcTemplate.query(sql, new Object[] { customerID, daysBefore }, new OrderRowMapper());

        return orderList;
    }

    @Override
    public Order getOrderByOrderID(int orderID){

        try{
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

            String sql = "SELECT order_id, " +
                    "       customer_id, " +
                    "       order_status_code, " +
                    "       order_created_date, " +
                    "       order_placed_date, " +
                    "       order_sub_total, " +
                    "       shipper_id, " +
                    "       shipping_speed_code, " +
                    "       shipping_total," +
                    "       order_shipped_date, " +
                    "       tracking_number, " +
                    "       tax_total, " +
                    "       payment_card_id, " +
                    "       state_tax_rate_id," +
                    "       shipping_address_line1, " +
                    "       shipping_address_line2, " +
                    "       shipping_city, " +
                    "       shipping_state_code, " +
                    "       shipping_postal_code, " +
                    "       merged_to_order_id, " +
                    "       order_item_count, " +
                    "       shipping_to_name, " +
                    "       payment_card_type_id, " +
                    "       payment_card_type_name, " +
                    "       payment_card_number, " +
                    "       payment_card_cvv, " +
                    "       payment_card_expiry, " +
                    "       payment_card_holder_name, " +
                    "       payment_card_address_line1, " +
                    "       payment_card_address_line2, " +
                    "       payment_card_city, " +
                    "       payment_card_state_code, " +
                    "       payment_card_postal_code, " +
                    "       display_ind, " +
                    "       order_line_count, " +
                    "       order_total " +
                    "  FROM order_header_view " +
                    " WHERE order_id = ?";

            Order order = jdbcTemplate.queryForObject(sql, new Object[] { orderID }, new OrderRowMapper());

            return order;

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
