package com.viiup.web.flock.providers;

import com.viiup.web.flock.models.*;

import java.util.List;

/**
 * Created by amoyeen on 2/26/2015.
 */
public interface IBaseProvider {

    List<PasswordSecurityQuestion> getPasswordSecurityQuestionList();
    boolean accountExistsForEmail(String emailAddress);
    void signUpCustomer(Customer customer);
    List<AddressState> getAddressStateList();
    List<PhoneType> getPhoneTypeList();
    List<PaymentCardType> getPaymentCardTypeList();
    PasswordSecurity getPasswordSecurity(String emailAddress);
    boolean validatePasswordSecurity(PasswordSecurity passwordSecurity);
}
