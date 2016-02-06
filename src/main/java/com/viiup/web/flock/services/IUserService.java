package com.viiup.web.flock.services;

import com.viiup.web.flock.models.*;

import java.util.List;

/**
 * Created by amoyeen on 2/20/2015.
 */
public interface IUserService {

    UserModel getUserByUserId(int userId);

    void insertCustomer(Customer customer);
    void insertCustomerAddress(CustomerAddress customerAddress);
    void insertCustomerPhone(CustomerPhone customerPhone);
    int insertCustomerPaymentCard(CustomerPaymentCard customerPaymentCard);
    Customer getCustomerByCustomerID(int customerID);
    Customer getCustomerByEmailAddress(String emailAddress);
    List<CustomerAddress> getCustomerAddressList(int customerID);
    CustomerAddress getCustomerAddressByAddressID(int addressID);
    List<CustomerPhone> getCustomerPhoneList(int customerID);
    CustomerPhone getCustomerPhoneByPhoneID(int phoneID);
    List<CustomerPaymentCard> getCustomerPaymentCardList(int customerID);
    CustomerPaymentCard getCustomerPaymentCardByPaymentCardID(int paymentCardID);
    CustomerPaymentCard getCustomerPaymentCardByPaymentCardNumber(String paymentCardNumber, int customerID);
    void updateCustomerProfile(Customer customer);
    void updateCustomerPassword(Customer customer);
    void updateCustomerAddress(CustomerAddress customerAddress);
    void updateCustomerPhone(CustomerPhone customerPhone);
    void updateCustomerPaymentCard(CustomerPaymentCard customerPaymentCard);
    void deleteCustomerAddress(int addressID);
    void deleteCustomerPhone(int phoneID);
}