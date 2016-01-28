package com.viiup.web.flock.services;

import com.viiup.web.flock.providers.IGroupProvider;
import com.viiup.web.flock.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by amoyeen on 3/1/15.
 */
@Service
public class GroupService implements IGroupService {

    @Autowired
    IGroupProvider groupProvider;

    @Override
    public List<Product> searchProducts(String searchString) {
        return groupProvider.searchProducts(searchString);
    }

    @Override
    public Product getProductByProductID(int productID) {
        return groupProvider.getProductByProductID(productID);
    }

    @Override
    public boolean updateInventory(int productID, int quantity){
        return groupProvider.updateInventory(productID, quantity);
    }
}
