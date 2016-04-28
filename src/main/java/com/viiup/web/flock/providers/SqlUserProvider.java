package com.viiup.web.flock.providers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.viiup.web.flock.jdbc.*;
import com.viiup.web.flock.models.*;
import com.viiup.web.flock.providers.interfaces.IUserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

/**
 * Created by AbdullahMoyeen on 1/25/2016.
 */
@Service
public class SqlUserProvider implements IUserProvider {

    @Autowired
    DataSource dataSource;

    @Override
    public UserModel insertUser(UserModel user) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        StringBuilder sql = new StringBuilder();

        sql.append("INSERT INTO t_user\n");
        sql.append("(\n");
        sql.append(" first_name\n");
        sql.append(",last_name\n");
        sql.append(",email_address\n");
        sql.append(",password\n");
        sql.append(",salt\n");
        sql.append(",account_status\n");
        sql.append(",password_expired_ind\n");
        sql.append(")\n");
        sql.append("values\n");
        sql.append("(\n");
        sql.append(" ?\n");
        sql.append(",?\n");
        sql.append(",LOWER(?)\n");
        sql.append(",?\n");
        sql.append(",?\n");
        sql.append(",?\n");
        sql.append(",?\n");
        sql.append(")");

        final String finalSql = sql.toString();
        System.out.println(finalSql);
        final UserModel finalUser = user;
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(finalSql, new String[]{"user_id"});
                        ps.setString(1, finalUser.getFirstName());
                        ps.setString(2, finalUser.getLastName());
                        ps.setString(3, finalUser.getEmailAddress());
                        ps.setString(4, finalUser.getPassword());
                        ps.setString(5, finalUser.getSalt());
                        ps.setString(6, finalUser.getAccountStatus());
                        ps.setString(7, finalUser.getIsPasswordExpired() ? "Y" : null);
                        return ps;
                    }
                },
                keyHolder
        );

        user.setUserId(keyHolder.getKey().intValue());

        return user;
    }

    @Override
    public UserRoleModel insertUserRole(UserRoleModel userRole) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        StringBuilder sql = new StringBuilder();

        sql.append("INSERT INTO t_user_role\n");
        sql.append("(\n");
        sql.append(" user_id\n");
        sql.append(",role_name\n");
        sql.append(")\n");
        sql.append("values\n");
        sql.append("(\n");
        sql.append(" ?\n");
        sql.append(",?\n");
        sql.append(")");

        final String finalSql = sql.toString();
        final UserRoleModel finalUserRole = userRole;
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(finalSql, new String[]{"user_role_id"});
                        ps.setInt(1, finalUserRole.getUserId());
                        ps.setString(2, finalUserRole.getRoleName());
                        return ps;
                    }
                },
                keyHolder
        );

        userRole.setUserRoleId(keyHolder.getKey().intValue());

        return userRole;
    }

    @Override
    public UserModel getUserByEmailAddress(String emailAddress) {

        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

            StringBuilder sql = new StringBuilder();

            sql.append("SELECT user_id\n");
            sql.append("      ,create_user\n");
            sql.append("      ,create_date\n");
            sql.append("      ,update_user\n");
            sql.append("      ,update_date\n");
            sql.append("      ,first_name\n");
            sql.append("      ,last_name\n");
            sql.append("      ,email_address\n");
            sql.append("      ,password\n");
            sql.append("      ,salt\n");
            sql.append("      ,IFNULL(failed_login_count, 0) AS failed_login_count\n");
            sql.append("      ,account_status\n");
            sql.append("      ,IFNULL(password_expired_ind, 'N') AS password_expired_ind\n");
            sql.append("  FROM t_user\n");
            sql.append(" WHERE email_address = LOWER(?)");

            UserModel user = jdbcTemplate.queryForObject(sql.toString(), new String[]{emailAddress}, new UserRowMapper());

            return user;

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<UserRoleModel> getUserRolesByUserId(int userId) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT user_role_id\n");
        sql.append("      ,create_user\n");
        sql.append("      ,create_date\n");
        sql.append("      ,update_user\n");
        sql.append("      ,update_date\n");
        sql.append("      ,user_id\n");
        sql.append("      ,role_name\n");
        sql.append("  FROM t_user_role\n");
        sql.append(" WHERE user_id = ?");

        List userRoles = jdbcTemplate.query(sql.toString(), new Object[]{userId}, new UserRoleRowMapper());

        return userRoles;
    }

    @Override
    public UserModel getUserByUserId(int userId) {

        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

            StringBuilder sql = new StringBuilder();

            sql.append("SELECT user_id\n");
            sql.append("      ,create_user\n");
            sql.append("      ,create_date\n");
            sql.append("      ,update_user\n");
            sql.append("      ,update_date\n");
            sql.append("      ,first_name\n");
            sql.append("      ,last_name\n");
            sql.append("      ,email_address\n");
            sql.append("      ,password\n");
            sql.append("      ,salt\n");
            sql.append("      ,IFNULL(failed_login_count, 0) AS failed_login_count\n");
            sql.append("      ,account_status\n");
            sql.append("      ,IFNULL(password_expired_ind, 'N') AS password_expired_ind\n");
            sql.append("  FROM t_user\n");
            sql.append(" WHERE user_id = ?");

            UserModel user = jdbcTemplate.queryForObject(sql.toString(), new Object[]{userId}, new UserRowMapper());

            return user;

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public UserModel getAuthenticatedUser(String emailAddress, String password) {

        UserModel user = getUserByEmailAddress(emailAddress);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (passwordEncoder.matches(password, user.getPassword()))
            return user;

        return null;
    }

    @Override
    public void updateUserPassword(UserPasswordChangeModel userPassword) {
        updateUserPassword(userPassword, false);
    }

    @Override
    public void updateUserPassword(UserPasswordChangeModel userPassword, boolean expirePassword) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        StringBuilder sql = new StringBuilder();

        sql.append("UPDATE t_user\n");
        sql.append("   SET password = ?\n");
        sql.append("      ,update_user = ?\n");
        sql.append("      ,update_date = current_timestamp\n");
        if (expirePassword)
            sql.append("      ,password_expired_ind = 'Y'\n");
        else
            sql.append("      ,password_expired_ind = NULL\n");
        sql.append(" WHERE email_address = LOWER(?)");

        jdbcTemplate.update(sql.toString(), new Object[]{userPassword.getNewPassword(), "FLOCK_DEV_USER", userPassword.getEmailAddress()});
    }
}
