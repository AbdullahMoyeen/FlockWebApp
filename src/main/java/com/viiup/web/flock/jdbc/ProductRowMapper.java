package com.viiup.web.flock.jdbc;

import com.viiup.web.flock.models.Product;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by amoyeen on 3/1/15.
 */
public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet resultSet, int line) throws SQLException {
        ProductExtractor productExtractor = new ProductExtractor();
        return productExtractor.extractData(resultSet);
    }
}