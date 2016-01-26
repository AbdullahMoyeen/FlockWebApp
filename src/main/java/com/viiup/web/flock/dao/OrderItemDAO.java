package com.viiup.web.flock.dao;


import com.viiup.web.flock.models.OrderItem;
import com.viiup.web.flock.models.ShoppingCart;

import java.util.List;

/**
 * Created by HP on 2/24/2015.
 */
public interface OrderItemDAO {
    public void insertOrderItem(OrderItem orderItem);
    public void updateOrderItem(OrderItem orderItem);
    public List<OrderItem> getOrderItemList(int orderId);
    public List<ShoppingCart> getShoppingCartList(int orderId);
    public void deleteOrderItem(int orderItemId);
    public OrderItem getOrderItem(int orderItemID);
}
