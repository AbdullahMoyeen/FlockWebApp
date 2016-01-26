package com.viiup.web.flock.jdbc;

import com.viiup.web.flock.models.CustomerPaymentCard;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by amoyeen on 2/28/15.
 */
public class CustomerPaymentCardExtractor implements ResultSetExtractor<CustomerPaymentCard> {

    public CustomerPaymentCard extractData(ResultSet resultSet) throws SQLException, DataAccessException {

        CustomerPaymentCard customerPaymentCard = new CustomerPaymentCard();

        customerPaymentCard.setPaymentCardID(resultSet.getInt(1));
        customerPaymentCard.setCustomerID(resultSet.getInt(2));
        customerPaymentCard.setPaymentCardTypeID(resultSet.getInt(3));
        customerPaymentCard.setPaymentCardTypeName(resultSet.getString(4));
        customerPaymentCard.setPaymentCardNumber(resultSet.getString(5));
        customerPaymentCard.setPaymentCardCVV(resultSet.getString(6));
        customerPaymentCard.setPaymentCardExpiry(resultSet.getString(7));
        customerPaymentCard.setPaymentCardHolderName(resultSet.getString(8));
        customerPaymentCard.setPaymentCardAddressLine1(resultSet.getString(9));
        customerPaymentCard.setPaymentCardAddressLine2(resultSet.getString(10));
        customerPaymentCard.setPaymentCardCity(resultSet.getString(11));
        customerPaymentCard.setPaymentCardStateCode(resultSet.getString(12));
        customerPaymentCard.setPaymentCardPostalCode(resultSet.getString(13));
        customerPaymentCard.setDisplayInd(resultSet.getString(14));
        customerPaymentCard.setPaymentCardFullAddress(resultSet.getString(15));

        return customerPaymentCard;
    }
}
