package com.viiup.web.flock.services;

import com.viiup.web.flock.dao.OrderDAO;
import com.viiup.web.flock.models.Order;
import com.viiup.web.flock.models.ShippingSpeed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by amoyeen on 2/20/2015.
 */
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderDAO orderDAO;

    @Override
    public int insertOrder(Order order){
        return orderDAO.insertOrder(order);
    }

    @Override
    public List<ShippingSpeed> getShippingSpeedList() { return orderDAO.getShippingSpeedList(); }

    @Override
    public void updateOrder(Order order) {
        orderDAO.updateOrder(order);
    }

    @Override
    public void mergeOrder(Order survivingOrder, Order victimOrder){
        orderDAO.mergeOrder(survivingOrder, victimOrder);
    }

    @Override
    public boolean shippingAllowed(String stateCode){
        return orderDAO.shippingAllowed(stateCode);
    }

    @Override
    public int getStateTaxRateID(String stateCode) {
        return orderDAO.getStateTaxRateID(stateCode);
    }

    @Override
    public Float getStateTaxRate(int stateTaxRateID) {
        return orderDAO.getStateTaxRate(stateTaxRateID);
    }

    @Override
    public Float getShippingRate(int shipperID, String shippingSpeedCode) {
        return orderDAO.getShippingRate(shipperID, shippingSpeedCode);
    }

    @Override
    public Order getPendingOrder(int customerID) {
        return orderDAO.getPendingOrder(customerID);
    }

    @Override
    public List<Order> getOpenOrderListByCustomerID(int customerID){
        return orderDAO.getOpenOrderListByCustomerID(customerID);
    }

    @Override
    public List<Order> getOrderHistoryByCustomerID(int customerID, int daysBefore){
        return orderDAO.getOrderHistoryByCustomerID(customerID, daysBefore);
    }

    @Override
    public Order getOrderByOrderID(int orderID) {
        return orderDAO.getOrderByOrderID(orderID);
    }
}
