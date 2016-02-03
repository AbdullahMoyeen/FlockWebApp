package com.viiup.web.flock.providers;

import com.viiup.web.flock.jdbc.GroupRowMapper;
import com.viiup.web.flock.jdbc.GroupUserRowMapper;
import com.viiup.web.flock.jdbc.ProductRowMapper;
import com.viiup.web.flock.jdbc.UserRowMapper;
import com.viiup.web.flock.models.GroupModel;
import com.viiup.web.flock.models.GroupUserModel;
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
public class SqlGroupProvider implements IGroupProvider {

    @Autowired
    DataSource dataSource;

    @Override
    public List<GroupModel> getAdminGroupsByUserId(int userId){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        StringBuilder sql =  new StringBuilder();

        sql.append("SELECT g.group_id\n");
        sql.append("      ,g.create_user\n");
        sql.append("      ,g.create_date\n");
        sql.append("      ,g.update_user\n");
        sql.append("      ,g.update_date\n");
        sql.append("      ,g.group_name\n");
        sql.append("      ,g.group_description\n");
        sql.append("      ,IFNULL(guc.active_user_count, 0) AS active_user_count\n");
        sql.append("      ,IFNULL(guc.pending_user_count, 0) AS pending_user_count\n");
        sql.append("      ,IFNULL(gec.upcoming_event_count, 0) AS upcoming_event_count\n");
        sql.append("  FROM t_group g\n");
        sql.append("  JOIN t_group_user gu ON g.group_id = gu.group_id\n");
        sql.append("  LEFT OUTER JOIN (SELECT g.group_id\n");
        sql.append("                         ,SUM(CASE gu.group_membership_status\n");
        sql.append("                                WHEN 'A' THEN 1\n");
        sql.append("                                ELSE 0\n");
        sql.append("                              END) AS active_user_count\n");
        sql.append("                         ,SUM(CASE gu.group_membership_status\n");
        sql.append("                                WHEN 'P' THEN 1\n");
        sql.append("                                ELSE 0\n");
        sql.append("                              END) AS pending_user_count\n");
        sql.append("                     FROM t_group g\n");
        sql.append("                     LEFT OUTER JOIN t_group_user gu ON g.group_id = gu.group_id\n");
        sql.append("                    GROUP BY g.group_id) guc ON g.group_id = guc.group_id\n");
        sql.append("  LEFT OUTER JOIN (SELECT g.group_id\n");
        sql.append("                         ,count(e.event_id) AS upcoming_event_count\n");
        sql.append("                     FROM t_group g\n");
        sql.append("                     LEFT OUTER JOIN t_event e ON g.group_id = e.group_id\n");
        sql.append("                    WHERE e.event_start_datetime > current_timestamp\n");
        sql.append("                    GROUP BY g.group_id) gec ON g.group_id = gec.group_id\n");
        sql.append(" WHERE gu.group_admin_flag = 'Y'\n");
        sql.append("   AND gu.group_membership_status = 'A'\n");
        sql.append("   AND gu.user_id = ?");

        List groupList = jdbcTemplate.query(sql.toString(), new Object[]{userId}, new GroupRowMapper());

        return groupList;
    }

    @Override
    public boolean IsUserAdminOfGroup(int groupId, int userId){


            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

            StringBuilder sql = new StringBuilder();

            sql.append("SELECT count(*)\n");
            sql.append("  FROM t_group_user\n");
            sql.append(" WHERE group_id = ?\n");
            sql.append("   AND user_id = ?\n");
            sql.append("   AND group_membership_status = 'A'\n");
            sql.append("   AND group_admin_flag = 'Y'");

            int groupAdminCount = jdbcTemplate.queryForObject(sql.toString(), new Object[] { groupId, userId }, Integer.class);

            if(groupAdminCount > 0)
                return true;
            else
                return false;
    }

    @Override
    public GroupModel getGroupByGroupId(int groupId){

        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

            StringBuilder sql = new StringBuilder();

            sql.append("SELECT g.group_id\n");
            sql.append("      ,g.create_user\n");
            sql.append("      ,g.create_date\n");
            sql.append("      ,g.update_user\n");
            sql.append("      ,g.update_date\n");
            sql.append("      ,g.group_name\n");
            sql.append("      ,g.group_description\n");
            sql.append("      ,IFNULL(guc.active_user_count, 0) AS active_user_count\n");
            sql.append("      ,IFNULL(guc.pending_user_count, 0) AS pending_user_count\n");
            sql.append("      ,IFNULL(gec.upcoming_event_count, 0) AS upcoming_event_count\n");
            sql.append("  FROM t_group g\n");
            sql.append("  LEFT OUTER JOIN (SELECT g.group_id\n");
            sql.append("                         ,SUM(CASE gu.group_membership_status\n");
            sql.append("                                WHEN 'A' THEN 1\n");
            sql.append("                                ELSE 0\n");
            sql.append("                              END) AS active_user_count\n");
            sql.append("                         ,SUM(CASE gu.group_membership_status\n");
            sql.append("                                WHEN 'P' THEN 1\n");
            sql.append("                                ELSE 0\n");
            sql.append("                              END) AS pending_user_count\n");
            sql.append("                     FROM t_group g\n");
            sql.append("                     LEFT OUTER JOIN t_group_user gu ON g.group_id = gu.group_id\n");
            sql.append("                    GROUP BY g.group_id) guc ON g.group_id = guc.group_id\n");
            sql.append("  LEFT OUTER JOIN (SELECT g.group_id\n");
            sql.append("                         ,count(e.event_id) AS upcoming_event_count\n");
            sql.append("                     FROM t_group g\n");
            sql.append("                     LEFT OUTER JOIN t_event e ON g.group_id = e.group_id\n");
            sql.append("                    WHERE e.event_start_datetime > current_timestamp\n");
            sql.append("                    GROUP BY g.group_id) gec ON g.group_id = gec.group_id\n");
            sql.append(" WHERE g.group_id = ?");

            GroupModel group = jdbcTemplate.queryForObject(sql.toString(), new Object[]{groupId}, new GroupRowMapper());

            return group;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public GroupModel updateGroup(GroupModel group){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        StringBuilder sql = new StringBuilder();

        sql.append("UPDATE t_group\n");
        sql.append("   SET group_name = ?\n");
        sql.append("      ,group_description = ?\n");
        sql.append("      ,update_user = ?\n");
        sql.append("      ,update_date = current_timestamp\n");
        sql.append(" WHERE group_id = ?");

        jdbcTemplate.update(sql.toString(), new Object[]{group.getGroupName(), group.getGroupDescription(), "FLOCK_DEV_USER", group.getGroupId()});

        return group;
    }

    @Override
    public List<GroupUserModel> getGroupUsersByGroupId(int groupId){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        StringBuilder sql =  new StringBuilder();

        sql.append("SELECT gu.group_user_id\n");
        sql.append("      ,gu.create_user\n");
        sql.append("      ,gu.create_date\n");
        sql.append("      ,gu.update_user\n");
        sql.append("      ,gu.update_date\n");
        sql.append("      ,gu.group_id\n");
        sql.append("      ,gu.user_id\n");
        sql.append("      ,gu.group_membership_status\n");
        sql.append("      ,gu.group_admin_flag\n");
        sql.append("      ,u.first_name\n");
        sql.append("      ,u.last_name\n");
        sql.append("      ,u.email_address\n");
        sql.append("  FROM t_group_user gu\n");
        sql.append("  JOIN t_user u ON gu.user_id = u.user_id\n");
        sql.append(" WHERE gu.group_id = ?\n");
        sql.append("   AND gu.group_membership_status <> 'I'\n");
        sql.append(" ORDER BY gu.group_membership_status DESC, u.email_address");

        List groupUserList = jdbcTemplate.query(sql.toString(), new Object[]{groupId}, new GroupUserRowMapper());

        return groupUserList;
    }

    @Override
    public void approveGroupMembership(int groupId, int userId){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        StringBuilder sql = new StringBuilder();

        sql.append("UPDATE t_group_user\n");
        sql.append("   SET group_membership_status = 'A'\n");
        sql.append("      ,update_user = ?\n");
        sql.append("      ,update_date = current_timestamp\n");
        sql.append(" WHERE group_id = ?\n");
        sql.append("   AND user_id = ?");

        jdbcTemplate.update(sql.toString(), new Object[]{"FLOCK_DEV_USER", groupId, userId});
    }

    @Override
    public void denyGroupMembership(int groupId, int userId){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        StringBuilder sql = new StringBuilder();

        sql.append("UPDATE t_group_user\n");
        sql.append("   SET group_membership_status = 'I'\n");
        sql.append("      ,join_request_denial_count = IFNULL(join_request_denial_count, 0) + 1\n");
        sql.append("      ,update_user = ?\n");
        sql.append("      ,update_date = current_timestamp\n");
        sql.append(" WHERE group_id = ?\n");
        sql.append("   AND user_id = ?");

        jdbcTemplate.update(sql.toString(), new Object[]{"FLOCK_DEV_USER", groupId, userId});
    }



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
