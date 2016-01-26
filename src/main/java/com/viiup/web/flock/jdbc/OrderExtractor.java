package com.viiup.web.flock.jdbc;

import com.viiup.web.flock.models.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by HP on 2/25/2015.
 */
public class OrderExtractor implements ResultSetExtractor<Order> {

    public Order extractData(ResultSet resultSet) throws SQLException, DataAccessException {

        Order order = new Order();

        order.setOrderID(resultSet.getInt(1));
        order.setCustomerID(resultSet.getInt(2));
        order.setOrderStatusCode(resultSet.getString(3));
        order.setOrderCreated(resultSet.getTimestamp(4));
        order.setOrderPlaced(resultSet.getTimestamp(5));
        order.setOrderSubTotal(resultSet.getFloat(6));
        order.setShipperID(resultSet.getInt(7));
        order.setShippingSpeedCode(resultSet.getString(8));
        order.setShippingTotal(resultSet.getFloat(9));
        order.setOrderShipped(resultSet.getTimestamp(10));
        order.setTrackingNumber(resultSet.getString(11));
        order.setTaxTotal(resultSet.getFloat(12));
        order.setPaymentCardID(resultSet.getInt(13));
        order.setStateTaxRateID(resultSet.getInt(14));
        order.setShippingAddressLine1(resultSet.getString(15));
        order.setShippingAddressLine2(resultSet.getString(16));
        order.setShippingCity(resultSet.getString(17));
        order.setShippingStateCode(resultSet.getString(18));
        order.setShippingPostalCode(resultSet.getString(19));
        order.setMergedToOrderID(resultSet.getInt(20));
        order.setOrderItemCount(resultSet.getInt(21));
        order.setShippingToName(resultSet.getString(22));
        order.setPaymentCardTypeID(resultSet.getInt(23));
        order.setPaymentCardTypeName(resultSet.getString(24));
        order.setPaymentCardNumber(resultSet.getString(25));
        order.setPaymentCardCVV(resultSet.getString(26));
        order.setPaymentCardExpiry(resultSet.getString(27));
        order.setPaymentCardHolderName(resultSet.getString(28));
        order.setPaymentCardAddressLine1(resultSet.getString(29));
        order.setPaymentCardAddressLine2(resultSet.getString(30));
        order.setPaymentCardCity(resultSet.getString(31));
        order.setPaymentCardStateCode(resultSet.getString(32));
        order.setPaymentCardPostalCode(resultSet.getString(33));
        order.setPaymentCardDisplayInd(resultSet.getString(34));
        order.setOrderLineCount(resultSet.getInt(35));
        order.setOrderTotal(resultSet.getFloat(36));

        return order;
    }
}
