package com.viiup.web.flock.services;

import com.viiup.web.flock.providers.IBaseProvider;
import com.viiup.web.flock.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by amoyeen on 2/26/2015.
 */
@Service
public class BaseService implements IBaseService {

    @Autowired
    IBaseProvider baseProvider;

    @Override
    public List<PasswordSecurityQuestion> getPasswordSecurityQuestionList(){

        return baseProvider.getPasswordSecurityQuestionList();
    }

    @Override
    public boolean accountExistsForEmail(String emailAddress){

        return baseProvider.accountExistsForEmail(emailAddress);
    }

    @Override
    public void signUpCustomer(Customer customer) {

        baseProvider.signUpCustomer(customer);
    }

    @Override
    public List<AddressState> getAddressStateList(){

        return baseProvider.getAddressStateList();
    }

    @Override
    public List<PhoneType> getPhoneTypeList(){

        return baseProvider.getPhoneTypeList();
    }

    @Override
    public List<PaymentCardType> getPaymentCardTypeList(){

        return baseProvider.getPaymentCardTypeList();
    }

    @Override
    public PasswordSecurity getPasswordSecurity(String emailAddress){

        return baseProvider.getPasswordSecurity(emailAddress);
    }

    @Override
    public boolean validatePasswordSecurity(PasswordSecurity passwordSecurity){

        return baseProvider.validatePasswordSecurity(passwordSecurity);
    }
}
