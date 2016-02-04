package com.viiup.web.flock.providers;

import com.viiup.web.flock.jdbc.*;
import com.viiup.web.flock.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by amoyeen on 2/26/2015.
 */
@Service
public class SqlBaseProvider implements IBaseProvider {

    @Autowired
    DataSource dataSource;
    @Autowired
    IUserProvider userProvider;

    @Override
    public List<PasswordSecurityQuestion> getPasswordSecurityQuestionList() {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "SELECT security_question_id, security_question_text " +
                     "  FROM security_question";
        List securityQuestionList = jdbcTemplate.query(sql, new PasswordSecurityQuestionRowMapper());

        return securityQuestionList;
    }

    @Override
    public boolean accountExistsForEmail(String emailAddress){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "SELECT COUNT(*) " +
                     "  FROM customer " +
                     " WHERE email_address = UPPER(?)";

        int x = jdbcTemplate.queryForObject(sql, new Object[]{emailAddress}, Integer.class);

        if(x > 0) {
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void signUpCustomer(Customer customer) {

        PasswordSecurity passwordSecurity = new PasswordSecurity();
        customer = userProvider.insertCustomer(customer);

        passwordSecurity.setCustomerID(customer.getCustomerID());
        passwordSecurity.setSecurityQuestionID(customer.getSecurityQuestionID());
        passwordSecurity.setSecurityQuestionAnswer(customer.getSecurityQuestionAnswer());
        userProvider.insertCustomerSecurity(passwordSecurity);
    }

    @Override
    public List<AddressState> getAddressStateList(){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "SELECT state_code, state_name, shipping_allowed_ind, concat(state_code, '-', state_name) AS state_code_and_name " +
                     "  FROM state " +
                     " ORDER BY state_code";
        List addressStateList = jdbcTemplate.query(sql, new AddressStateRowMapper());

        return addressStateList;
    }

    @Override
    public List<PhoneType> getPhoneTypeList(){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "SELECT phone_type_code, phone_type_name, phone_type_description " +
                     "  FROM phone_type " +
                     " ORDER BY phone_type_code";
        List phoneTypeList = jdbcTemplate.query(sql, new PhoneTypeRowMapper());

        return phoneTypeList;
    }

    @Override
    public List<PaymentCardType> getPaymentCardTypeList(){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "SELECT * FROM payment_card_type ORDER BY payment_card_type_id";
        List paymentCardTypeList = jdbcTemplate.query(sql, new PaymentCardTypeRowMapper());

        return paymentCardTypeList;
    }

    @Override
    public PasswordSecurity getPasswordSecurity(String emailAddress){

        try{
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

            String sql = "SELECT customer_id, email_address, security_question_id, security_question_text, NULL AS security_question_answer " +
                         "  FROM customer_security_view " +
                         " WHERE email_address = UPPER(?)";

            PasswordSecurity passwordSecurity = jdbcTemplate.queryForObject(sql, new String[] { emailAddress }, new PasswordSecurityRowMapper());

            return passwordSecurity;

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public boolean validatePasswordSecurity(PasswordSecurity passwordSecurity){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "SELECT COUNT(*) " +
                     "  FROM customer_security_view " +
                     " WHERE customer_id = ? " +
                     "   AND security_question_id = ? " +
                     "   AND security_question_answer = UPPER(?)";

        int x = jdbcTemplate.queryForObject(sql, new Object[]{ passwordSecurity.getCustomerID(), passwordSecurity.getSecurityQuestionID(), passwordSecurity.getSecurityQuestionAnswer() }, Integer.class);

        if(x > 0) {
            return true;
        }
        else{
            return false;
        }
    }
}
