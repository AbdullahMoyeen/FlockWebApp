package com.viiup.web.flock.dao;

import com.viiup.web.flock.models.*;

import java.util.List;

/**
 * Created by amoyeen on 2/20/2015.
 */
public interface CustomerDAO {

    public Customer insertCustomer(Customer customer);
    public void insertCustomerSecurity(PasswordSecurity passwordSecurity);
    public void insertCustomerAddress(CustomerAddress customerAddress);
    public void insertCustomerPhone(CustomerPhone customerPhone);
    public int insertCustomerPaymentCard(CustomerPaymentCard customerPaymentCard);
    public Customer getCustomerByCustomerID(int customerID);
    public Customer getCustomerByEmailAddress(String emailAddress);
    public List<CustomerAddress> getCustomerAddressList(int customerID);
    public CustomerAddress getCustomerAddressByAddressID(int addressID);
    public List<CustomerPhone> getCustomerPhoneList(int customerID);
    public CustomerPhone getCustomerPhoneByPhoneID(int phoneID);
    public List<CustomerPaymentCard> getCustomerPaymentCardList(int customerID);
    public CustomerPaymentCard getCustomerPaymentCardByPaymentCardID(int paymentCardID);
    public CustomerPaymentCard getCustomerPaymentCardByPaymentCardNumber(String paymentCardNumber, int customerID);
    public void updateCustomerProfile(Customer customer);
    public void updateCustomerPassword(Customer customer);
    public void updateCustomerAddress(CustomerAddress customerAddress);
    public void updateCustomerPhone(CustomerPhone customerPhone);
    public void updateCustomerPaymentCard(CustomerPaymentCard customerPaymentCard);
    public void deleteCustomerAddress(int addressID);
    public void deleteCustomerPhone(int phoneID);
}
