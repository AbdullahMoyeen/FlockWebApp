package com.viiup.web.flock.services;

import com.viiup.web.flock.models.Order;
import com.viiup.web.flock.models.ShippingSpeed;

import java.util.List;

/**
 * Created by HP on 2/23/2015.
 */
public interface IEventService {

    int insertOrder(Order order);
    List<ShippingSpeed> getShippingSpeedList();
    void updateOrder(Order order);
    void mergeOrder(Order survivingOrder, Order victimOrder);
    boolean shippingAllowed(String stateCode);
    int getStateTaxRateID(String stateCode);
    Float getStateTaxRate(int stateTaxRateID);
    Float getShippingRate(int shipperID, String shippingSpeedCode);
    Order getPendingOrder(int customerID);
    List<Order> getOpenOrderListByCustomerID(int customerID);
    List<Order> getOrderHistoryByCustomerID(int customerID, int daysBefore);
    Order getOrderByOrderID(int orderID);
}
