package com.viiup.web.flock.services;

import com.viiup.web.flock.models.CustomerAddress;
import com.viiup.web.flock.models.CustomerPaymentCard;
import com.viiup.web.flock.models.CustomerPhone;
import com.viiup.web.flock.dao.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import com.viiup.web.flock.models.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by amoyeen on 2/20/2015.
 */
@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerDAO customerDAO;

    @Override
    public void insertCustomer(Customer customer) {
        customerDAO.insertCustomer(customer);
    }

    @Override
    public void insertCustomerAddress(CustomerAddress customerAddress) {
        customerDAO.insertCustomerAddress(customerAddress);
    }

    @Override
    public void insertCustomerPhone(CustomerPhone customerPhone) {
        customerDAO.insertCustomerPhone(customerPhone);
    }

    @Override
    public int insertCustomerPaymentCard(CustomerPaymentCard customerPaymentCard) {
        return customerDAO.insertCustomerPaymentCard(customerPaymentCard);
    }

    @Override
    public Customer getCustomerByCustomerID(int customerID) {
        return customerDAO.getCustomerByCustomerID(customerID);
    }

    @Override
    public Customer getCustomerByEmailAddress(String emailAddress) {
        return customerDAO.getCustomerByEmailAddress(emailAddress);
    }

    @Override
    public List<CustomerAddress> getCustomerAddressList(int customerID){
        return customerDAO.getCustomerAddressList(customerID);
    }

    @Override
    public CustomerAddress getCustomerAddressByAddressID(int addressID){
        return customerDAO.getCustomerAddressByAddressID(addressID);
    }

    @Override
    public List<CustomerPhone> getCustomerPhoneList(int customerID){
        return customerDAO.getCustomerPhoneList(customerID);
    }

    @Override
    public CustomerPhone getCustomerPhoneByPhoneID(int phoneID){
        return customerDAO.getCustomerPhoneByPhoneID(phoneID);
    }

    @Override
    public List<CustomerPaymentCard> getCustomerPaymentCardList(int customerID){
        return customerDAO.getCustomerPaymentCardList(customerID);
    }

    @Override
    public CustomerPaymentCard getCustomerPaymentCardByPaymentCardID(int paymentCardID){
        return customerDAO.getCustomerPaymentCardByPaymentCardID(paymentCardID);
    }

    @Override
    public CustomerPaymentCard getCustomerPaymentCardByPaymentCardNumber(String paymentCardNumber, int customerID){
        return customerDAO.getCustomerPaymentCardByPaymentCardNumber(paymentCardNumber, customerID);
    }

    @Override
    public void updateCustomerProfile(Customer customer) {
        customerDAO.updateCustomerProfile(customer);
    }

    @Override
    public void updateCustomerPassword(Customer customer) {
        customerDAO.updateCustomerPassword(customer);
    }

    @Override
    public void updateCustomerAddress(CustomerAddress customerAddress){
        customerDAO.updateCustomerAddress(customerAddress);
    }

    @Override
    public void updateCustomerPhone(CustomerPhone customerPhone){
        customerDAO.updateCustomerPhone(customerPhone);
    }

    @Override
    public void updateCustomerPaymentCard(CustomerPaymentCard customerPaymentCard){
        customerDAO.updateCustomerPaymentCard(customerPaymentCard);
    }

    @Override
    public void deleteCustomerAddress(int addressID){
        customerDAO.deleteCustomerAddress(addressID);
    }

    @Override
    public void deleteCustomerPhone(int phoneID){
        customerDAO.deleteCustomerPhone(phoneID);
    }
}
