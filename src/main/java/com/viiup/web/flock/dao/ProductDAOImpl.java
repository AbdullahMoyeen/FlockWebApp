package com.viiup.web.flock.dao;

import com.viiup.web.flock.jdbc.ProductRowMapper;
import com.viiup.web.flock.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import javax.sql.DataSource;
import java.util.List;

/**
 * Created by amoyeen on 3/1/15.
 */
@Service
public class ProductDAOImpl implements ProductDAO {

    @Autowired
    DataSource dataSource;

    @Override
    public List<Product> searchProducts(String searchString){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "SELECT DISTINCT p.product_id, p.manufacturer_id, p.manufacturer_name, p.product_name, p.product_description, p.price, p.image_file_location, p.image_file_name, p.quantity_on_hand " +
                     "  FROM product_view p " +
                     "  LEFT JOIN product_keyword pk ON p.product_id = pk.product_id " +
                     "  LEFT JOIN keyword k ON pk.keyword_id = k.keyword_id " +
                     " WHERE (p.product_name LIKE concat('%', ?, '%') OR p.manufacturer_name LIKE concat('%', ?, '%') OR k.keyword_text LIKE concat('%', ?, '%')) " +
                     " ORDER BY pk.relevance_score DESC ";

        List productList = jdbcTemplate.query(sql, new Object[] { searchString, searchString, searchString }, new ProductRowMapper());

        return productList;
    }

    @Override
    public Product getProductByProductID(int productID){

        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

            String sql = "SELECT product_id, manufacturer_id, manufacturer_name, product_name, product_description, price, image_file_location, image_file_name, quantity_on_hand " +
                         "  FROM product_view " +
                         " WHERE product_id = ? ";
            Product product = jdbcTemplate.queryForObject(sql, new Object[]{productID}, new ProductRowMapper());

            return product;

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public boolean updateInventory(int productID, int quantity){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = new String();

        sql = "SELECT IFNULL(min(inventory_id), 0) " +
              "  FROM inventory " +
              " WHERE product_id = ? " +
              "   AND quantity_on_hand >= ?";

        int inventoryID = jdbcTemplate.queryForObject(sql, new Object[] { productID, quantity }, Integer.class);

        if(inventoryID > 0){
            sql = "UPDATE inventory " +
                  "   SET quantity_on_hand = quantity_on_hand - ? " +
                  " WHERE inventory_id = ?";

            jdbcTemplate.update(sql, new Object[] {quantity, inventoryID});

            return true;
        }
        else{
            return false;
        }
    }
}
