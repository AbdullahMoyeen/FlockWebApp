package com.viiup.web.flock.jdbc;

import com.viiup.web.flock.models.ShoppingCart;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by HP on 2/25/2015.
 */
public class ShoppingCartExtractor implements ResultSetExtractor<ShoppingCart> {

    public ShoppingCart extractData(ResultSet resultSet) throws SQLException, DataAccessException {

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setOrderItemID(resultSet.getInt(1));
        shoppingCart.setOrderID(resultSet.getInt(2));
        shoppingCart.setProductID(resultSet.getInt(3));
        shoppingCart.setQuantity(resultSet.getInt(4));
        shoppingCart.setPricePaid(resultSet.getFloat(5));
        shoppingCart.setOrderItemSubTotal(resultSet.getFloat(6));
        shoppingCart.setProductName(resultSet.getString(7));
        shoppingCart.setProductDescription(resultSet.getString(8));
        shoppingCart.setPrice(resultSet.getFloat(9));
        shoppingCart.setImageFileLocation(resultSet.getString(10));
        shoppingCart.setImageFileName(resultSet.getString(11));
        shoppingCart.setManufacturerID(resultSet.getInt(12));
        shoppingCart.setManufacturerName(resultSet.getString(13));

        return shoppingCart;
    }
}
