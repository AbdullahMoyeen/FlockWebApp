package com.viiup.web.flock.providers;

import com.viiup.web.flock.jdbc.*;
import com.viiup.web.flock.models.*;
import com.viiup.web.flock.providers.interfaces.IGroupProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by AbdullahMoyeen on 2/1/2016.
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
        sql.append("      ,g.group_category\n");
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
    public List<UserGroupModel> getGroupsByUserId(int userId){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        StringBuilder sql =  new StringBuilder();

        sql.append("SELECT g.group_id\n");
        sql.append("      ,g.create_user\n");
        sql.append("      ,g.create_date\n");
        sql.append("      ,g.update_user\n");
        sql.append("      ,g.update_date\n");
        sql.append("      ,g.group_name\n");
        sql.append("      ,g.group_description\n");
        sql.append("      ,g.group_category\n");
        sql.append("      ,IFNULL(guc.active_user_count, 0) AS active_user_count\n");
        sql.append("      ,IFNULL(guc.pending_user_count, 0) AS pending_user_count\n");
        sql.append("      ,IFNULL(gec.upcoming_event_count, 0) AS upcoming_event_count\n");
        sql.append("      ,IFNULL(gu.group_membership_status,'I') AS group_membership_status\n");
        sql.append("      ,u.user_id\n");
        sql.append("  FROM t_group g\n");
        sql.append("  JOIN t_user u ON u.user_id = ?\n");
        sql.append("  LEFT OUTER JOIN t_group_user gu ON g.group_id = gu.group_id AND gu.user_id = u.user_id\n");
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

        List userGroupList = jdbcTemplate.query(sql.toString(), new Object[]{userId}, new UserGroupRowMapper());

        return userGroupList;
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

        return groupAdminCount > 0;
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
            sql.append("      ,g.group_category\n");
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
        sql.append("      ,group_category = ?\n");
        sql.append("      ,update_user = ?\n");
        sql.append("      ,update_date = current_timestamp\n");
        sql.append(" WHERE group_id = ?");

        jdbcTemplate.update(sql.toString(), new Object[]{group.getGroupName(), group.getGroupDescription(), group.getGroupCategory(), "FLOCK_DEV_USER", group.getGroupId()});

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
    public List<EventModel> getGroupEventsByGroupId(int groupId){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        StringBuilder sql =  new StringBuilder();
        sql.append("SELECT event_id\n");
        sql.append("      ,group_id\n");
        sql.append("      ,event_name\n");
        sql.append("      ,event_description\n");
        sql.append("      ,event_start_datetime\n");
        sql.append("      ,event_end_datetime\n");
        sql.append("      ,event_address_line1\n");
        sql.append("      ,event_address_line2\n");
        sql.append("      ,event_city\n");
        sql.append("      ,event_state_code\n");
        sql.append("      ,event_postal_code\n");
        sql.append("      ,event_keywords\n");
        sql.append("      ,event_latitude\n");
        sql.append("      ,event_longitude\n");
        sql.append("      ,IFNULL(private_event_ind, 'N')\n");
        sql.append("      ,create_user\n");
        sql.append("      ,create_date\n");
        sql.append("      ,update_user\n");
        sql.append("      ,update_date\n");
        sql.append("      ,event_category\n");
        sql.append("      ,0 AttendeeCount\n");
        sql.append("  FROM t_event\n");
        sql.append(" WHERE group_id = ?\n");
        sql.append("   AND event_start_datetime > current_timestamp\n");
        sql.append(" ORDER BY event_start_datetime, event_name");

        List groupEventList = jdbcTemplate.query(sql.toString(), new Object[]{groupId}, new EventRowMapper());

        return groupEventList;
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
    public void insertGroupUser(GroupUserModel groupUser){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        StringBuilder sql = new StringBuilder();

        sql.append("	INSERT INTO t_group_user\n");
        sql.append("	(group_id\n");
        sql.append("	,user_id\n");
        sql.append("	,group_membership_status\n");
        sql.append("	)\n");
        sql.append("	VALUES\n");
        sql.append("	( \n");
        sql.append("    ?\n");
        sql.append("    ,?\n");
        sql.append("    ,?\n");
        sql.append("    )");

        final String finalSql = sql.toString();
        final GroupUserModel finalGroupUser = groupUser;
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(finalSql, new String[]{"group_user_id"});

                        ps.setInt(1, finalGroupUser.getGroupId());
                        ps.setInt(2, finalGroupUser.getUserId());
                        ps.setString(3, finalGroupUser.getGroupMembershipStatus());
                        return ps;
                    }
                },
                keyHolder
        );

        groupUser.setGroupUserId(keyHolder.getKey().intValue());
    }

    @Override
    public void deleteGroupUser(int userId, int groupId) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "DELETE FROM t_group_user " +
                "WHERE " +
                "user_id = ? " +
                "AND group_id = ? " ;
        jdbcTemplate.update(sql.toString(), new Object[]{userId, groupId});
    }
    @Override
    public List<RefGroupCategoryModel> getRefGroupCategoryList(){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "SELECT group_category " +
                "  FROM t_ref_group_category " +
                " ORDER BY group_category";
        List refGroupCategoryList = jdbcTemplate.query(sql, new RefGroupCategoryRowMapper());

        return refGroupCategoryList;
    }
}
