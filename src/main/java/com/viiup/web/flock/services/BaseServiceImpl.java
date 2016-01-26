package com.viiup.web.flock.services;

import com.viiup.web.flock.dao.BaseDAO;
import com.viiup.web.flock.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by amoyeen on 2/26/2015.
 */
@Service
public class BaseServiceImpl implements BaseService {

    @Autowired
    BaseDAO baseDAO;

    @Override
    public List<PasswordSecurityQuestion> getPasswordSecurityQuestionList(){

        return baseDAO.getPasswordSecurityQuestionList();
    }

    @Override
    public boolean accountExistsForEmail(String emailAddress){

        return baseDAO.accountExistsForEmail(emailAddress);
    }

    @Override
    public void signUpCustomer(Customer customer) {

        baseDAO.signUpCustomer(customer);
    }

    @Override
    public List<AddressState> getAddressStateList(){

        return baseDAO.getAddressStateList();
    }

    @Override
    public List<PhoneType> getPhoneTypeList(){

        return baseDAO.getPhoneTypeList();
    }

    @Override
    public List<PaymentCardType> getPaymentCardTypeList(){

        return baseDAO.getPaymentCardTypeList();
    }

    @Override
    public PasswordSecurity getPasswordSecurity(String emailAddress){

        return baseDAO.getPasswordSecurity(emailAddress);
    }

    @Override
    public boolean validatePasswordSecurity(PasswordSecurity passwordSecurity){

        return baseDAO.validatePasswordSecurity(passwordSecurity);
    }
}
