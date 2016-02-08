package com.viiup.web.flock.providers;

import com.viiup.web.flock.jdbc.*;
import com.viiup.web.flock.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.security.SecureRandom;
import java.util.List;

/**
 * Created by amoyeen on 1/27/2016.
 */
@Service
public class SqlBaseProvider implements IBaseProvider {

    @Autowired
    DataSource dataSource;

    @Autowired
    IUserProvider userProvider;

    private static final char[] PASSWORD_VALID_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456879!@#$".toCharArray();
    private static final int PASSWORD_TEMP_LENGTH = 8;

    @Override
    public String generateTempPassword(){

        StringBuilder tempPassword = new StringBuilder();
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < PASSWORD_TEMP_LENGTH; i++) {
                tempPassword.append(PASSWORD_VALID_CHARACTERS[random.nextInt(65)]);
        }

        return tempPassword.toString();
    }

    @Override
    public boolean emailAddressExists(String emailAddress){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT count(*)\n");
        sql.append("  FROM t_user\n");
        sql.append(" WHERE email_address = LOWER(?)");

        int x = jdbcTemplate.queryForObject(sql.toString(), new Object[]{emailAddress}, Integer.class);

        if(x > 0) {
            return true;
        }
        else{
            return false;
        }
    }
}
