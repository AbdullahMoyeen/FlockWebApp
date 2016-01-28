package com.viiup.web.flock.providers;


import com.viiup.web.flock.models.OrderItem;
import com.viiup.web.flock.models.ShoppingCart;

import java.util.List;

/**
 * Created by HP on 2/24/2015.
 */
public interface IOrderItemProvider {
    void insertOrderItem(OrderItem orderItem);
    void updateOrderItem(OrderItem orderItem);
    List<OrderItem> getOrderItemList(int orderId);
    List<ShoppingCart> getShoppingCartList(int orderId);
    void deleteOrderItem(int orderItemId);
    OrderItem getOrderItem(int orderItemID);
}
