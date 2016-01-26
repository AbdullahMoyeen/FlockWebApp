package com.viiup.web.flock.services;

import com.viiup.web.flock.dao.OrderItemDAO;
import com.viiup.web.flock.models.OrderItem;
import com.viiup.web.flock.models.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by HP on 2/24/2015.
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    OrderItemDAO orderItemDAO;

    @Override
    public void insertOrderItem(OrderItem orderItem) { orderItemDAO.insertOrderItem(orderItem);
    }

    @Override
    public void updateOrderItem(OrderItem orderItem) { orderItemDAO.updateOrderItem(orderItem);
    }

    @Override
    public List<OrderItem> getOrderItemList(int orderID){ return orderItemDAO.getOrderItemList(orderID);

    }

    @Override
    public List<ShoppingCart> getShoppingCartList(int orderID) {return orderItemDAO.getShoppingCartList(orderID);
    }

    @Override
    public void deleteOrderItem(int orderItemID) { orderItemDAO.deleteOrderItem(orderItemID);
    }

    @Override
    public OrderItem getOrderItem(int orderItemID) { return orderItemDAO.getOrderItem(orderItemID);
    }
}
