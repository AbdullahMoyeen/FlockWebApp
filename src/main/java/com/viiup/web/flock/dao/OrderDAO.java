package com.viiup.web.flock.dao;

import com.viiup.web.flock.models.Order;
import com.viiup.web.flock.models.ShippingSpeed;

import java.util.List;

/**
 * Created by HP on 2/23/2015.
 */
public interface OrderDAO {

    public int insertOrder(Order order);
    public List<ShippingSpeed> getShippingSpeedList();
    public void updateOrder(Order order);
    public void mergeOrder(Order survivingOrder, Order victimOrder);
    public boolean shippingAllowed(String stateCode);
    public int getStateTaxRateID(String stateCode);
    public Float getStateTaxRate(int stateTaxRateID);
    public Float getShippingRate(int shipperID, String shippingSpeedCode);
    public Order getPendingOrder(int customerID);
    public List<Order> getOpenOrderListByCustomerID(int customerID);
    public List<Order> getOrderHistoryByCustomerID(int customerID, int daysBefore);
    public Order getOrderByOrderID(int orderID);
}
