package com.viiup.web.flock.dao;

import com.viiup.web.flock.models.*;

import java.util.List;

/**
 * Created by amoyeen on 2/26/2015.
 */
public interface BaseDAO {

    public List<PasswordSecurityQuestion> getPasswordSecurityQuestionList();
    public boolean accountExistsForEmail(String emailAddress);
    public void signUpCustomer(Customer customer);
    public List<AddressState> getAddressStateList();
    public List<PhoneType> getPhoneTypeList();
    public List<PaymentCardType> getPaymentCardTypeList();
    public PasswordSecurity getPasswordSecurity(String emailAddress);
    public boolean validatePasswordSecurity(PasswordSecurity passwordSecurity);
}
