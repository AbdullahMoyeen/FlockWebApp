package com.viiup.web.flock.jdbc;

import com.viiup.web.flock.models.Product;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by amoyeen on 3/1/15.
 */
public class ProductExtractor implements ResultSetExtractor<Product> {

    public Product extractData(ResultSet resultSet) throws SQLException, DataAccessException {

        Product product = new Product();

        product.setProductID(resultSet.getInt(1));
        product.setManufacturerID(resultSet.getInt(2));
        product.setManufacturerName(resultSet.getString(3));
        product.setProductName(resultSet.getString(4));
        product.setProductDescription(resultSet.getString(5));
        product.setPrice(resultSet.getFloat(6));
        product.setImageFileLocation(resultSet.getString(7));
        product.setImageFileName(resultSet.getString(8));
        product.setQuantityOnHand(resultSet.getInt(9));

        return product;
    }
}
