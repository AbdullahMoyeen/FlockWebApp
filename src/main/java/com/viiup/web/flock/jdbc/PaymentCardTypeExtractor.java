package com.viiup.web.flock.jdbc;

import com.viiup.web.flock.models.PaymentCardType;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by amoyeen on 2/28/15.
 */
public class PaymentCardTypeExtractor implements ResultSetExtractor<PaymentCardType> {

    public PaymentCardType extractData(ResultSet resultSet) throws SQLException, DataAccessException {

        PaymentCardType paymentCardType = new PaymentCardType();

        paymentCardType.setPaymentCardTypeID(resultSet.getInt(1));
        paymentCardType.setPaymentCardTypeName(resultSet.getString(2));
        paymentCardType.setPaymentCardTypeDescription(resultSet.getString(3));

        return paymentCardType;
    }
}
