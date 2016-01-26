package com.viiup.web.flock.services;

import com.viiup.web.flock.dao.ProductDAO;
import com.viiup.web.flock.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by amoyeen on 3/1/15.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDAO productDAO;

    @Override
    public List<Product> searchProducts(String searchString) {
        return productDAO.searchProducts(searchString);
    }

    @Override
    public Product getProductByProductID(int productID) {
        return productDAO.getProductByProductID(productID);
    }

    @Override
    public boolean updateInventory(int productID, int quantity){
        return productDAO.updateInventory(productID, quantity);
    }
}
