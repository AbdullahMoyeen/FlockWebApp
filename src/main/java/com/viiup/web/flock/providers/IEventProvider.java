package com.viiup.web.flock.providers;

import com.viiup.web.flock.models.*;

import java.util.List;

/**
 * Created by HP on 2/23/2015.
 */
public interface IEventProvider {
    void insertEvent(EventModel event);
    void updateEvent(EventModel event);
    EventModel getEventByEventID(int eventID);

    List<RefState> getRefStateList();

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
