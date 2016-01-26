package com.viiup.web.flock.controllers;

import com.viiup.web.flock.models.*;
import com.viiup.web.flock.services.BaseService;
import com.viiup.web.flock.services.CustomerService;
import com.viiup.web.flock.services.OrderItemService;
import com.viiup.web.flock.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by amoyeen on 2/24/15.
 */
@Controller
public class CustomerController {

    @Autowired
    HttpSession httpSession;

    @Autowired
    BaseService baseService;

    @Autowired
    CustomerService customerService;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderItemService orderItemService;

    @RequestMapping("/customer/viewProfile")
    public ModelAndView customerViewProfile(@RequestParam int customerID) {

        Customer customer = customerService.getCustomerByCustomerID(customerID);
        List<CustomerAddress> customerAddressList = customerService.getCustomerAddressList(customerID);
        List<CustomerPhone> customerPhoneList = customerService.getCustomerPhoneList(customerID);
        List<CustomerPaymentCard> customerPaymentCardList = customerService.getCustomerPaymentCardList(customerID);

        ModelAndView modelAndView = new ModelAndView("customerProfile");

        modelAndView.addObject("customer", customer);
        modelAndView.addObject("customerAddressList", customerAddressList);
        modelAndView.addObject("customerPhoneList", customerPhoneList);
        modelAndView.addObject("customerPaymentCardList", customerPaymentCardList);

        return modelAndView;
    }

    @RequestMapping("/customer/viewOpenOrders")
    public ModelAndView customerViewOpenOrders(@RequestParam int customerID){

        List<Order> orderList = orderService.getOpenOrderListByCustomerID(customerID);

        ModelAndView modelAndView = new ModelAndView("customerOrderList");

        modelAndView.addObject("orderList", orderList);

        return modelAndView;
    }

    @RequestMapping("/customer/viewOrderHistory")
    public ModelAndView customerViewOrderHistory(@RequestParam int customerID, int daysBefore){

        List<Order> orderList = orderService.getOrderHistoryByCustomerID(customerID, daysBefore);

        ModelAndView modelAndView = new ModelAndView("customerOrderHistory");

        modelAndView.addObject("orderList", orderList);
        modelAndView.addObject("daysBefore", daysBefore);

        return modelAndView;
    }

    @RequestMapping("/customer/viewOrderDetails")
    public ModelAndView customerViewOrderDetails(@RequestParam int orderID){

        List<ShoppingCart> shoppingCartList = orderItemService.getShoppingCartList(orderID);
        Order order = orderService.getOrderByOrderID(orderID);

        ModelAndView modelAndView = new ModelAndView("customerOrderDetails");

        modelAndView.addObject("shoppingCartList", shoppingCartList);
        modelAndView.addObject("order", order);

        return modelAndView;
    }

    @RequestMapping("/customer/addAddress")
    public ModelAndView customerAddAddress(@RequestParam int customerID){

        CustomerAddress customerAddress = new CustomerAddress();
        customerAddress.setCustomerID(customerID);
        List<AddressState> addressStateList = baseService.getAddressStateList();

        ModelAndView modelAndView = new ModelAndView("customerAddAddress");

        modelAndView.addObject("customerAddress", customerAddress);
        modelAndView.addObject("addressStateList", addressStateList);

        return modelAndView;
    }

    @RequestMapping("/customer/addAddress/submit")
    public String customerAddAddressSubmit(@ModelAttribute CustomerAddress customerAddress){

        customerService.insertCustomerAddress(customerAddress);

        return "redirect:/customer/viewProfile?customerID=" + customerAddress.getCustomerID();
    }

    @RequestMapping("/customer/addPhone")
    public ModelAndView customerAddPhone(@RequestParam int customerID){

        CustomerPhone customerPhone = new CustomerPhone();
        customerPhone.setCustomerID(customerID);
        List<PhoneType> phoneTypeList = baseService.getPhoneTypeList();

        ModelAndView modelAndView = new ModelAndView("customerAddPhone");

        modelAndView.addObject("customerPhone", customerPhone);
        modelAndView.addObject("phoneTypeList", phoneTypeList);

        return modelAndView;
    }

    @RequestMapping("/customer/addPhone/submit")
    public String customerAddPhoneSubmit(@ModelAttribute CustomerPhone customerPhone){

        customerService.insertCustomerPhone(customerPhone);

        return "redirect:/customer/viewProfile?customerID=" + customerPhone.getCustomerID();
    }

    @RequestMapping("/customer/addPaymentCard")
    public ModelAndView customerAddPaymentCard(@RequestParam int customerID){

        CustomerPaymentCard customerPaymentCard = new CustomerPaymentCard();

        customerPaymentCard.setCustomerID(customerID);
        List<PaymentCardType> paymentCardTypeList = baseService.getPaymentCardTypeList();
        List<CustomerAddress> customerAddressList = customerService.getCustomerAddressList(customerID);
        List<AddressState> addressStateList = baseService.getAddressStateList();

        ModelAndView modelAndView = new ModelAndView("customerAddPaymentCard");

        modelAndView.addObject("customerPaymentCard", customerPaymentCard);
        modelAndView.addObject("paymentCardTypeList", paymentCardTypeList);
        modelAndView.addObject("customerAddressList", customerAddressList);
        modelAndView.addObject("addressStateList", addressStateList);

        return modelAndView;
    }

    @RequestMapping("/customer/addPaymentCard/submit")
    public String customerAddPaymentCardSubmit(@ModelAttribute CustomerPaymentCard maCustomerPaymentCard){

        CustomerPaymentCard customerPaymentCard = customerService.getCustomerPaymentCardByPaymentCardNumber(maCustomerPaymentCard.getPaymentCardNumber(), maCustomerPaymentCard.getCustomerID());

        if(customerPaymentCard == null) {

            int paymentCardID = customerService.insertCustomerPaymentCard(maCustomerPaymentCard);
        }
        else{

            maCustomerPaymentCard.setPaymentCardID(customerPaymentCard.getPaymentCardID());

            customerService.updateCustomerPaymentCard(maCustomerPaymentCard);
        }

        return "redirect:/customer/viewProfile?customerID=" + maCustomerPaymentCard.getCustomerID();
    }

    @RequestMapping("/customer/updateProfile")
    public String customerUpdateProfile(@ModelAttribute Customer customer){

        customerService.updateCustomerProfile(customer);
        customer = customerService.getCustomerByCustomerID(customer.getCustomerID());

        httpSession.setAttribute("customer", customer);

        return "redirect:/customer/viewProfile?customerID=" + customer.getCustomerID();

    }

    @RequestMapping("/customer/updatePassword")
    public ModelAndView customerUpdatePassword(@RequestParam int customerID){

        Customer customer = customerService.getCustomerByCustomerID(customerID);
        customer.setPassword(null);

        ModelAndView modelAndView = new ModelAndView("customerUpdatePassword");

        modelAndView.addObject("customer", customer);

        return modelAndView;
    }

    @RequestMapping("/customer/updatePassword/submit")
    public String customerUpdatePasswordSubmit(@ModelAttribute Customer customer){

        if (customer != null) {
            customerService.updateCustomerPassword(customer);
        }

        return "redirect:/customer/viewProfile?customerID=" + customer.getCustomerID();
    }

    @RequestMapping("/customer/updateAddress")
    public ModelAndView customerUpdateAddress(@RequestParam int addressID){

        CustomerAddress customerAddress = customerService.getCustomerAddressByAddressID(addressID);
        List<AddressState> addressStateList = baseService.getAddressStateList();

        ModelAndView modelAndView = new ModelAndView("customerUpdateAddress");

        modelAndView.addObject("customerAddress", customerAddress);
        modelAndView.addObject("addressStateList", addressStateList);

        return modelAndView;
    }

    @RequestMapping("/customer/updateAddress/submit")
    public String customerUpdateAddressSubmit(@ModelAttribute CustomerAddress customerAddress){

        if (customerAddress != null) {
            customerService.updateCustomerAddress(customerAddress);
        }

        return "redirect:/customer/viewProfile?customerID=" + customerAddress.getCustomerID();
    }

    @RequestMapping("/customer/updatePhone")
    public ModelAndView customerUpdatePhone(@RequestParam int phoneID){

        CustomerPhone customerPhone = customerService.getCustomerPhoneByPhoneID(phoneID);
        List<PhoneType> phoneTypeList = baseService.getPhoneTypeList();

        ModelAndView modelAndView = new ModelAndView("customerUpdatePhone");

        modelAndView.addObject("customerPhone", customerPhone);
        modelAndView.addObject("phoneTypeList", phoneTypeList);

        return modelAndView;
    }

    @RequestMapping("/customer/updatePhone/submit")
    public String customerUpdatePhoneSubmit(@ModelAttribute CustomerPhone customerPhone){

        customerService.updateCustomerPhone(customerPhone);

        return "redirect:/customer/viewProfile?customerID=" + customerPhone.getCustomerID();
    }

    @RequestMapping("/customer/updatePaymentCard")
    public ModelAndView customerUpdatePaymentCard(@RequestParam int paymentCardID){

        CustomerPaymentCard customerPaymentCard = customerService.getCustomerPaymentCardByPaymentCardID(paymentCardID);
        customerPaymentCard.setPaymentCardNumber(null);
        customerPaymentCard.setPaymentCardCVV(null);
        List<PaymentCardType> paymentCardTypeList = baseService.getPaymentCardTypeList();
        List<AddressState> addressStateList = baseService.getAddressStateList();

        ModelAndView modelAndView = new ModelAndView("customerUpdatePaymentCard");

        modelAndView.addObject("customerPaymentCard", customerPaymentCard);
        modelAndView.addObject("paymentCardTypeList", paymentCardTypeList);
        modelAndView.addObject("addressStateList", addressStateList);

        return modelAndView;
    }

    @RequestMapping("/customer/updatePaymentCard/submit")
    public String customerUpdatePaymentCardSubmit(@ModelAttribute CustomerPaymentCard maCustomerPaymentCard){

        CustomerPaymentCard customerPaymentCard = customerService.getCustomerPaymentCardByPaymentCardNumber(maCustomerPaymentCard.getPaymentCardNumber(), maCustomerPaymentCard.getCustomerID());

        if (customerPaymentCard == null) {

            customerService.updateCustomerPaymentCard(maCustomerPaymentCard);
        }
        else {

            if (customerPaymentCard.getPaymentCardID() == maCustomerPaymentCard.getPaymentCardID()) {

                customerService.updateCustomerPaymentCard(maCustomerPaymentCard);

            } else {

                customerPaymentCard.setPaymentCardTypeID(maCustomerPaymentCard.getPaymentCardTypeID());
                customerPaymentCard.setPaymentCardCVV(maCustomerPaymentCard.getPaymentCardCVV());
                customerPaymentCard.setPaymentCardExpiry(maCustomerPaymentCard.getPaymentCardExpiry());
                customerPaymentCard.setPaymentCardHolderName(maCustomerPaymentCard.getPaymentCardHolderName());
                customerPaymentCard.setPaymentCardAddressLine1(maCustomerPaymentCard.getPaymentCardAddressLine1());
                customerPaymentCard.setPaymentCardAddressLine2(maCustomerPaymentCard.getPaymentCardAddressLine2());
                customerPaymentCard.setPaymentCardCity(maCustomerPaymentCard.getPaymentCardCity());
                customerPaymentCard.setPaymentCardStateCode(maCustomerPaymentCard.getPaymentCardStateCode());
                customerPaymentCard.setPaymentCardPostalCode(maCustomerPaymentCard.getPaymentCardPostalCode());
                customerPaymentCard.setDisplayInd("Y");

                customerService.updateCustomerPaymentCard(customerPaymentCard);

                maCustomerPaymentCard = customerService.getCustomerPaymentCardByPaymentCardID(maCustomerPaymentCard.getPaymentCardID());
                maCustomerPaymentCard.setDisplayInd("N");

                customerService.updateCustomerPaymentCard(maCustomerPaymentCard);

            }
        }

        return "redirect:/customer/viewProfile?customerID=" + customerPaymentCard.getCustomerID();
    }

    @RequestMapping("/customer/deleteAddress")
    public String customerDeleteAddress(@RequestParam int addressID, int customerID){

        customerService.deleteCustomerAddress(addressID);

        return "redirect:/customer/viewProfile?customerID=" + customerID;
    }

    @RequestMapping("/customer/deletePhone")
    public String customerDeletePhone(@RequestParam int phoneID, int customerID){

        customerService.deleteCustomerPhone(phoneID);

        return "redirect:/customer/viewProfile?customerID=" + customerID;
    }

    @RequestMapping("/customer/deletePaymentCard")
    public String customerDeletePaymentCard(@RequestParam int paymentCardID, int customerID){

        CustomerPaymentCard customerPaymentCard = customerService.getCustomerPaymentCardByPaymentCardID(paymentCardID);

        customerPaymentCard.setDisplayInd("N");

        customerService.updateCustomerPaymentCard(customerPaymentCard);

        return "redirect:/customer/viewProfile?customerID=" + customerID;
    }
}
