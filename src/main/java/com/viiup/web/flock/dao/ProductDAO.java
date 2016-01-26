package com.viiup.web.flock.dao;

import com.viiup.web.flock.models.Product;

import java.util.List;

/**
 * Created by amoyeen on 3/1/15.
 */
public interface ProductDAO {

    public List<Product> searchProducts(String searchString);
    public Product getProductByProductID(int productID);
    public boolean updateInventory(int productID, int quantity);
}
