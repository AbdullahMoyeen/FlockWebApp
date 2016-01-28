package com.viiup.web.flock.services;

import com.viiup.web.flock.models.Product;

import java.util.List;

/**
 * Created by amoyeen on 3/1/15.
 */
public interface IGroupService {

    List<Product> searchProducts(String searchString);
    Product getProductByProductID(int productID);
    boolean updateInventory(int productID, int quantity);

}
