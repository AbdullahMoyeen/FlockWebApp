package com.viiup.web.flock.services;

import com.viiup.web.flock.providers.IEventProvider;
import com.viiup.web.flock.models.Order;
import com.viiup.web.flock.models.ShippingSpeed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by amoyeen on 2/20/2015.
 */
@Service
public class EventService implements IEventService {

    @Autowired
    IEventProvider eventProvider;

    @Override
    public int insertOrder(Order order){
        return eventProvider.insertOrder(order);
    }

    @Override
    public List<ShippingSpeed> getShippingSpeedList() { return eventProvider.getShippingSpeedList(); }

    @Override
    public void updateOrder(Order order) {
        eventProvider.updateOrder(order);
    }

    @Override
    public void mergeOrder(Order survivingOrder, Order victimOrder){
        eventProvider.mergeOrder(survivingOrder, victimOrder);
    }

    @Override
    public boolean shippingAllowed(String stateCode){
        return eventProvider.shippingAllowed(stateCode);
    }

    @Override
    public int getStateTaxRateID(String stateCode) {
        return eventProvider.getStateTaxRateID(stateCode);
    }

    @Override
    public Float getStateTaxRate(int stateTaxRateID) {
        return eventProvider.getStateTaxRate(stateTaxRateID);
    }

    @Override
    public Float getShippingRate(int shipperID, String shippingSpeedCode) {
        return eventProvider.getShippingRate(shipperID, shippingSpeedCode);
    }

    @Override
    public Order getPendingOrder(int customerID) {
        return eventProvider.getPendingOrder(customerID);
    }

    @Override
    public List<Order> getOpenOrderListByCustomerID(int customerID){
        return eventProvider.getOpenOrderListByCustomerID(customerID);
    }

    @Override
    public List<Order> getOrderHistoryByCustomerID(int customerID, int daysBefore){
        return eventProvider.getOrderHistoryByCustomerID(customerID, daysBefore);
    }

    @Override
    public Order getOrderByOrderID(int orderID) {
        return eventProvider.getOrderByOrderID(orderID);
    }
}
