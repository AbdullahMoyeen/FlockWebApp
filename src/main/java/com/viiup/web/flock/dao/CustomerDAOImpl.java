package com.viiup.web.flock.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import com.viiup.web.flock.jdbc.CustomerAddressRowMapper;
import com.viiup.web.flock.jdbc.CustomerPaymentCardRowMapper;
import com.viiup.web.flock.jdbc.CustomerPhoneRowMapper;
import com.viiup.web.flock.jdbc.CustomerRowMapper;
import com.viiup.web.flock.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import javax.sql.DataSource;

/**
 * Created by amoyeen on 2/20/2015.
 */
@Service
public class CustomerDAOImpl implements CustomerDAO{

    @Autowired
    DataSource dataSource;

    @Override
    public Customer insertCustomer(Customer customer){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "INSERT INTO customer(first_name, last_name, email_address, password)" +
                     "VALUES (UPPER(?),UPPER(?),UPPER(?),?)";

        final String finalSql = sql;
        final Customer finalCustomer = customer;
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(finalSql, new String[]{"customer_id"});
                        ps.setString(1, finalCustomer.getFirstName());
                        ps.setString(2, finalCustomer.getLastName());
                        ps.setString(3, finalCustomer.getEmailAddress());
                        ps.setString(4, finalCustomer.getPassword());
                        return ps;
                    }
                },
                keyHolder
        );

        customer.setCustomerID(keyHolder.getKey().intValue());

        return customer;
    }

    @Override
    public void insertCustomerSecurity(PasswordSecurity passwordSecurity){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql =   "INSERT INTO customer_security(customer_id, security_question_id, security_question_answer)" +
                       "VALUES (?,?,UPPER(?))";

        jdbcTemplate.update(sql, new Object[]{passwordSecurity.getCustomerID(), passwordSecurity.getSecurityQuestionID(), passwordSecurity.getSecurityQuestionAnswer()});
    }

    @Override
    public void insertCustomerAddress(CustomerAddress customerAddress){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "INSERT INTO address(customer_id, address_line1, address_line2, city, state_code, postal_code)" +
                     "VALUES (?,UPPER(?),UPPER(?),UPPER(?),UPPER(?),UPPER(?))";

        jdbcTemplate.update(sql, new Object[]{customerAddress.getCustomerID(), customerAddress.getAddressLine1(), customerAddress.getAddressLine2(), customerAddress.getCity(), customerAddress.getStateCode(), customerAddress.getPostalCode()});
    }

    @Override
    public void insertCustomerPhone(CustomerPhone customerPhone){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "INSERT INTO phone(customer_id, phone_type_code, phone_number)" +
                     "VALUES (?,UPPER(?),?)";

        jdbcTemplate.update(sql, new Object[]{customerPhone.getCustomerID(), customerPhone.getPhoneTypeCode(), customerPhone.getPhoneNumber()});
    }

    @Override
    public int insertCustomerPaymentCard(CustomerPaymentCard customerPaymentCard){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "INSERT INTO payment_card(customer_id, payment_card_type_id, payment_card_number, payment_card_cvv, payment_card_expiry, payment_card_holder_name, payment_card_address_line1, payment_card_address_line2, payment_card_city, payment_card_state_code, payment_card_postal_code, display_ind)" +
                     "VALUES (?,?,UPPER(?),UPPER(?),UPPER(?),UPPER(?),UPPER(?),UPPER(?),UPPER(?),UPPER(?),UPPER(?),UPPER(?))";

        final String finalSql = sql;
        final CustomerPaymentCard finalCustomerPaymentCard = customerPaymentCard;
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(finalSql, new String[]{"payment_card_id"});
                        ps.setInt(1, finalCustomerPaymentCard.getCustomerID());
                        ps.setInt(2, finalCustomerPaymentCard.getPaymentCardTypeID());
                        ps.setString(3, finalCustomerPaymentCard.getPaymentCardNumber());
                        ps.setString(4, finalCustomerPaymentCard.getPaymentCardCVV());
                        ps.setString(5, finalCustomerPaymentCard.getPaymentCardExpiry());
                        ps.setString(6, finalCustomerPaymentCard.getPaymentCardHolderName());
                        ps.setString(7, finalCustomerPaymentCard.getPaymentCardAddressLine1());
                        ps.setString(8, finalCustomerPaymentCard.getPaymentCardAddressLine2());
                        ps.setString(9, finalCustomerPaymentCard.getPaymentCardCity());
                        ps.setString(10, finalCustomerPaymentCard.getPaymentCardStateCode());
                        ps.setString(11, finalCustomerPaymentCard.getPaymentCardCity());
                        ps.setString(12, finalCustomerPaymentCard.getDisplayInd());
                        return ps;
                    }
                },
                keyHolder
        );

        return keyHolder.getKey().intValue();
    }

    @Override
    public Customer getCustomerByCustomerID(int customerID){

        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

            String sql = "SELECT customer_id, first_name, last_name, email_address, password, registration_date " +
                         "  FROM customer " +
                         " WHERE customer_id = ?";

            Customer customer = jdbcTemplate.queryForObject(sql, new Object[]{customerID}, new CustomerRowMapper());

            return customer;

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    @Override
    public Customer getCustomerByEmailAddress(String emailAddress){

        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

            String sql = "SELECT customer_id, first_name, last_name, email_address, password, registration_date " +
                         "  FROM customer " +
                         " WHERE email_address = UPPER(?)";

            Customer customer = jdbcTemplate.queryForObject(sql, new String[]{emailAddress}, new CustomerRowMapper());

            return customer;

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<CustomerAddress> getCustomerAddressList(int customerID){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "SELECT address_id, " +
                     "       customer_id, address_line1, " +
                     "       address_line2, " +
                     "       city, " +
                     "       state_code, " +
                     "       postal_code, " +
                     "       concat_ws(', ', address_line1, if(length(address_line2), address_line2, null), city, concat(state_code, '-', postal_code)) as full_address " +
                     "  FROM address " +
                     " WHERE customer_id = ?";

        List customerAddressList = jdbcTemplate.query(sql, new Object[]{customerID}, new CustomerAddressRowMapper());

        return customerAddressList;
    }

    @Override
    public CustomerAddress getCustomerAddressByAddressID(int addressID){

        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

            String sql = "SELECT address_id, " +
                         "       customer_id, address_line1, " +
                         "       address_line2, " +
                         "       city, " +
                         "       state_code, " +
                         "       postal_code, " +
                         "       concat_ws(', ', address_line1, if(length(address_line2), address_line2, null), city, concat(state_code, '-', postal_code)) as full_address " +
                         "  FROM address " +
                         " WHERE address_id = ?";

            CustomerAddress customerAddress = jdbcTemplate.queryForObject(sql, new Object[]{addressID}, new CustomerAddressRowMapper());

            return customerAddress;

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<CustomerPhone> getCustomerPhoneList(int customerID){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "SELECT p.phone_id, p.customer_id, p.phone_type_code, pt.phone_type_name, p.phone_number " +
                     "  FROM phone p, phone_type pt " +
                     " WHERE p.phone_type_code = pt.phone_type_code " +
                     "   AND p.customer_id = ?";

        List customerPhoneList = jdbcTemplate.query(sql, new Object[]{customerID}, new CustomerPhoneRowMapper());

        return customerPhoneList;
    }

    @Override
    public CustomerPhone getCustomerPhoneByPhoneID(int phoneID){

        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

            String sql = "SELECT p.phone_id, p.customer_id, p.phone_type_code, pt.phone_type_name, p.phone_number " +
                         "  FROM phone p, phone_type pt " +
                         " WHERE p.phone_type_code = pt.phone_type_code " +
                         "   AND p.phone_id = ?";

            CustomerPhone customerPhone = jdbcTemplate.queryForObject(sql, new Object[]{phoneID}, new CustomerPhoneRowMapper());

            return customerPhone;

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<CustomerPaymentCard> getCustomerPaymentCardList(int customerID){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "SELECT payment_card_id, customer_id, payment_card_type_id, payment_card_type_name, payment_card_number, payment_card_cvv, payment_card_expiry, payment_card_holder_name, payment_card_address_line1, payment_card_address_line2, payment_card_city, payment_card_state_code, payment_card_postal_code, display_ind, payment_card_full_address " +
                     "  FROM payment_card_view " +
                     " WHERE customer_id = ? " +
                     "   AND display_ind = 'Y'";

        List customerPaymentCardList = jdbcTemplate.query(sql, new Object[]{customerID}, new CustomerPaymentCardRowMapper());

        return customerPaymentCardList;
    }

    @Override
    public CustomerPaymentCard getCustomerPaymentCardByPaymentCardID(int paymentCardID){

        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

            String sql = "SELECT payment_card_id, customer_id, payment_card_type_id, payment_card_type_name, payment_card_number, payment_card_cvv, payment_card_expiry, payment_card_holder_name, payment_card_address_line1, payment_card_address_line2, payment_card_city, payment_card_state_code, payment_card_postal_code, display_ind, payment_card_full_address " +
                         "  FROM payment_card_view " +
                         " WHERE payment_card_id = ?";

            CustomerPaymentCard customerPaymentCard = jdbcTemplate.queryForObject(sql, new Object[]{paymentCardID}, new CustomerPaymentCardRowMapper());

            return customerPaymentCard;

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public CustomerPaymentCard getCustomerPaymentCardByPaymentCardNumber(String paymentCardNumber, int customerID){

        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

            String sql = "SELECT payment_card_id, customer_id, payment_card_type_id, payment_card_type_name, payment_card_number, payment_card_cvv, payment_card_expiry, payment_card_holder_name, payment_card_address_line1, payment_card_address_line2, payment_card_city, payment_card_state_code, payment_card_postal_code, display_ind, payment_card_full_address " +
                         "  FROM payment_card_view " +
                         " WHERE unmasked_payment_card_number = ? " +
                         "   AND customer_id =?";

            CustomerPaymentCard customerPaymentCard = jdbcTemplate.queryForObject(sql, new Object[]{paymentCardNumber, customerID}, new CustomerPaymentCardRowMapper());

            return customerPaymentCard;

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void updateCustomerProfile(Customer customer){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "UPDATE customer SET first_name = UPPER(?), last_name = UPPER(?) " +
                     " WHERE customer_id = ?";

        jdbcTemplate.update(sql, new Object[]{customer.getFirstName(), customer.getLastName(), customer.getCustomerID()});
    }

    @Override
    public void updateCustomerPassword(Customer customer){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "UPDATE customer SET password = ? " +
                     " WHERE customer_id = ?";

        jdbcTemplate.update(sql, new Object[]{customer.getPassword(), customer.getCustomerID()});
    }

    @Override
    public void updateCustomerAddress(CustomerAddress customerAddress){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "UPDATE address SET address_line1 = UPPER(?), address_line2 = UPPER(?), city = UPPER(?), state_code = UPPER(?), postal_code = UPPER(?)" +
                     " WHERE address_id = ?";

        jdbcTemplate.update(sql, new Object[]{customerAddress.getAddressLine1(), customerAddress.getAddressLine2(), customerAddress.getCity(), customerAddress.getStateCode(), customerAddress.getPostalCode(), customerAddress.getAddressID()});
    }

    @Override
    public void updateCustomerPhone(CustomerPhone customerPhone){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "UPDATE phone SET phone_type_code = UPPER(?), phone_number = UPPER(?)" +
                     " WHERE phone_id = ?";

        jdbcTemplate.update(sql, new Object[]{customerPhone.getPhoneTypeCode(), customerPhone.getPhoneNumber(), customerPhone.getPhoneID()});
    }

    @Override
    public void updateCustomerPaymentCard(CustomerPaymentCard customerPaymentCard){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "UPDATE payment_card " +
                     "   SET payment_card_type_id = ?, " +
                     "       payment_card_number = CASE WHEN ? LIKE '%*%' THEN payment_card_number ELSE UPPER(?) END, " +
                     "       payment_card_cvv = CASE WHEN ? LIKE '%*%' THEN payment_card_cvv ELSE UPPER(?) END, " +
                     "       payment_card_expiry = UPPER(?), " +
                     "       payment_card_holder_name = UPPER(?), " +
                     "       payment_card_address_line1 = UPPER(?), " +
                     "       payment_card_address_line2 = UPPER(?), " +
                     "       payment_card_city = UPPER(?), " +
                     "       payment_card_state_code = UPPER(?), " +
                     "       payment_card_postal_code = UPPER(?), " +
                     "       display_ind = ? " +
                     " WHERE payment_card_id = ?";

        jdbcTemplate.update(sql, new Object[]{customerPaymentCard.getPaymentCardTypeID(), customerPaymentCard.getPaymentCardNumber(), customerPaymentCard.getPaymentCardNumber(), customerPaymentCard.getPaymentCardCVV(), customerPaymentCard.getPaymentCardCVV(), customerPaymentCard.getPaymentCardExpiry(), customerPaymentCard.getPaymentCardHolderName(), customerPaymentCard.getPaymentCardAddressLine1(), customerPaymentCard.getPaymentCardAddressLine2(), customerPaymentCard.getPaymentCardCity(), customerPaymentCard.getPaymentCardStateCode(), customerPaymentCard.getPaymentCardPostalCode(), customerPaymentCard.getDisplayInd(), customerPaymentCard.getPaymentCardID()});
    }

    @Override
    public void deleteCustomerAddress(int addressID){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "DELETE FROM address WHERE address_id = ?";

        jdbcTemplate.update(sql,new Integer[] { addressID });
    }

    @Override
    public void deleteCustomerPhone(int phoneID){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "DELETE FROM phone WHERE phone_id = ?";

        jdbcTemplate.update(sql,new Integer[] { phoneID });
    }
}
