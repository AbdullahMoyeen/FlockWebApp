package com.viiup.web.flock.services;

import com.viiup.web.flock.models.*;
import com.viiup.web.flock.providers.IUserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by amoyeen on 2/20/2015.
 */
@Service
public class UserService implements IUserService {

    @Autowired
    IUserProvider userProvider;

    @Override
    public UserModel getUserByEmailAddress(String emailAddress) {
        return userProvider.getUserByEmailAddress(emailAddress);
    }

    @Override
    public List<UserRoleModel> getUserRolesByUserId(int userId) {
        return userProvider.getUserRolesByUserId(userId);
    }

    @Override
    public UserModel getUserByUserId(int userId) {
        return userProvider.getUserByUserId(userId);
    }


    @Override
    public void insertCustomer(Customer customer) {
        userProvider.insertCustomer(customer);
    }

    @Override
    public void insertCustomerAddress(CustomerAddress customerAddress) {
        userProvider.insertCustomerAddress(customerAddress);
    }

    @Override
    public void insertCustomerPhone(CustomerPhone customerPhone) {
        userProvider.insertCustomerPhone(customerPhone);
    }

    @Override
    public int insertCustomerPaymentCard(CustomerPaymentCard customerPaymentCard) {
        return userProvider.insertCustomerPaymentCard(customerPaymentCard);
    }

    @Override
    public Customer getCustomerByCustomerID(int customerID) {
        return userProvider.getCustomerByCustomerID(customerID);
    }

    @Override
    public Customer getCustomerByEmailAddress(String emailAddress) {
        return userProvider.getCustomerByEmailAddress(emailAddress);
    }

    @Override
    public List<CustomerAddress> getCustomerAddressList(int customerID){
        return userProvider.getCustomerAddressList(customerID);
    }

    @Override
    public CustomerAddress getCustomerAddressByAddressID(int addressID){
        return userProvider.getCustomerAddressByAddressID(addressID);
    }

    @Override
    public List<CustomerPhone> getCustomerPhoneList(int customerID){
        return userProvider.getCustomerPhoneList(customerID);
    }

    @Override
    public CustomerPhone getCustomerPhoneByPhoneID(int phoneID){
        return userProvider.getCustomerPhoneByPhoneID(phoneID);
    }

    @Override
    public List<CustomerPaymentCard> getCustomerPaymentCardList(int customerID){
        return userProvider.getCustomerPaymentCardList(customerID);
    }

    @Override
    public CustomerPaymentCard getCustomerPaymentCardByPaymentCardID(int paymentCardID){
        return userProvider.getCustomerPaymentCardByPaymentCardID(paymentCardID);
    }

    @Override
    public CustomerPaymentCard getCustomerPaymentCardByPaymentCardNumber(String paymentCardNumber, int customerID){
        return userProvider.getCustomerPaymentCardByPaymentCardNumber(paymentCardNumber, customerID);
    }

    @Override
    public void updateCustomerProfile(Customer customer) {
        userProvider.updateCustomerProfile(customer);
    }

    @Override
    public void updateCustomerPassword(Customer customer) {
        userProvider.updateCustomerPassword(customer);
    }

    @Override
    public void updateCustomerAddress(CustomerAddress customerAddress){
        userProvider.updateCustomerAddress(customerAddress);
    }

    @Override
    public void updateCustomerPhone(CustomerPhone customerPhone){
        userProvider.updateCustomerPhone(customerPhone);
    }

    @Override
    public void updateCustomerPaymentCard(CustomerPaymentCard customerPaymentCard){
        userProvider.updateCustomerPaymentCard(customerPaymentCard);
    }

    @Override
    public void deleteCustomerAddress(int addressID){
        userProvider.deleteCustomerAddress(addressID);
    }

    @Override
    public void deleteCustomerPhone(int phoneID){
        userProvider.deleteCustomerPhone(phoneID);
    }
}
