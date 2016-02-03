package com.viiup.web.flock.services;

import com.viiup.web.flock.models.OrderItem;
import com.viiup.web.flock.models.ShoppingCart;

import java.util.List;

/**
 * Created by HP on 2/23/2015.
 */
public interface IOrderItemService {

       void insertOrderItem(OrderItem orderItem);
       void updateOrderItem(OrderItem orderItem);
       List<OrderItem> getOrderItemList(int orderID);
       List<ShoppingCart> getShoppingCartList(int orderID);
       void deleteOrderItem(int orderItemID);
       OrderItem getOrderItem(int orderItemID);
}
