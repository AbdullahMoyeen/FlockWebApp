package com.viiup.web.flock.services;

import com.viiup.web.flock.models.*;

import java.util.List;

/**
 * Created by amoyeen on 2/26/2015.
 */
public interface IBaseService {

    boolean emailAddressExists(String emailAddress);

    List<PasswordSecurityQuestion> getPasswordSecurityQuestionList();
    boolean accountExistsForEmail(String emailAddress);
    void signUpCustomer(Customer customer);
    List<AddressState> getAddressStateList();
    List<PhoneType> getPhoneTypeList();
    List<PaymentCardType> getPaymentCardTypeList();
    PasswordSecurity getPasswordSecurity(String emailAddress);
    boolean validatePasswordSecurity(PasswordSecurity passwordSecurity);
}
