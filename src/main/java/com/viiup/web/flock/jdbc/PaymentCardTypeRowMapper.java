package com.viiup.web.flock.jdbc;

import com.viiup.web.flock.models.PaymentCardType;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by amoyeen on 2/28/15.
 */
public class PaymentCardTypeRowMapper implements RowMapper<PaymentCardType> {
    @Override
    public PaymentCardType mapRow(ResultSet resultSet, int line) throws SQLException {
        PaymentCardTypeExtractor paymentCardTypeExtractor = new PaymentCardTypeExtractor();
        return paymentCardTypeExtractor.extractData(resultSet);
    }
}
