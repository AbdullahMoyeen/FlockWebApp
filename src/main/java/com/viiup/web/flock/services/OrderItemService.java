package com.viiup.web.flock.services;

import com.viiup.web.flock.models.OrderItem;
import com.viiup.web.flock.models.ShoppingCart;

import java.util.List;

/**
 * Created by HP on 2/23/2015.
 */
public interface OrderItemService {

       public void insertOrderItem(OrderItem orderItem);
       public void updateOrderItem(OrderItem orderItem);
       public List<OrderItem> getOrderItemList(int orderID);
       public List<ShoppingCart> getShoppingCartList(int orderID);
       public void deleteOrderItem(int orderItemID);
       public OrderItem getOrderItem(int orderItemID);
}
