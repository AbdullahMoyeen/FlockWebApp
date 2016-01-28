package com.viiup.web.flock.services;

import com.viiup.web.flock.providers.IOrderItemProvider;
import com.viiup.web.flock.models.OrderItem;
import com.viiup.web.flock.models.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by HP on 2/24/2015.
 */
@Service
public class OrderItemService implements IOrderItemService {

    @Autowired
    IOrderItemProvider IOrderItemProvider;

    @Override
    public void insertOrderItem(OrderItem orderItem) { IOrderItemProvider.insertOrderItem(orderItem);
    }

    @Override
    public void updateOrderItem(OrderItem orderItem) { IOrderItemProvider.updateOrderItem(orderItem);
    }

    @Override
    public List<OrderItem> getOrderItemList(int orderID){ return IOrderItemProvider.getOrderItemList(orderID);

    }

    @Override
    public List<ShoppingCart> getShoppingCartList(int orderID) {return IOrderItemProvider.getShoppingCartList(orderID);
    }

    @Override
    public void deleteOrderItem(int orderItemID) { IOrderItemProvider.deleteOrderItem(orderItemID);
    }

    @Override
    public OrderItem getOrderItem(int orderItemID) { return IOrderItemProvider.getOrderItem(orderItemID);
    }
}
