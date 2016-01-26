package com.viiup.web.flock.controllers;

import com.viiup.web.flock.models.*;
import com.viiup.web.flock.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by amoyeen on 3/1/15.
 */
@Controller
public class OrderController {

    @Autowired
    HttpSession httpSession;

    @Autowired
    BaseService baseService;

    @Autowired
    CustomerService customerService;

    @Autowired
    ProductService productService;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderItemService orderItemService;

    @RequestMapping("/shoppingCart/addItem")
    public String shoppingCartAddItem(@RequestParam int productID, int addToCartQuantity){

        Order order = (Order) httpSession.getAttribute("order");
        Customer customer = (Customer) httpSession.getAttribute("customer");

        if (order == null){

            order = new Order();

            if (customer != null) {
                order.setCustomerID(customer.getCustomerID());
            }
            order.setOrderStatusCode("P");

            int orderID = orderService.insertOrder(order);

            order.setOrderID(orderID);
        }

        Product product = productService.getProductByProductID(productID);

        OrderItem orderItem = new OrderItem();

        orderItem.setOrderID(order.getOrderID());
        orderItem.setProductID(product.getProductID());
        orderItem.setQuantity(addToCartQuantity);
        orderItem.setPricePaid(product.getPrice());
        orderItem.setOrderItemSubTotal(product.getPrice() * addToCartQuantity);

        orderItemService.insertOrderItem(orderItem);

        order = orderService.getOrderByOrderID(order.getOrderID());
        httpSession.setAttribute("order", order);

        if(addToCartQuantity > product.getQuantityOnHand()){
            return "redirect:/shoppingCart/viewCart?orderID=" + order.getOrderID() + "&quantityOnHand=" + product.getQuantityOnHand();
        }
        else {
            return "redirect:/shoppingCart/viewCart?orderID=" + order.getOrderID();
        }
    }

    @RequestMapping("/shoppingCart/viewCart")
    public ModelAndView shoppingCartViewCart(@RequestParam int orderID){

        List<ShoppingCart> shoppingCartList = orderItemService.getShoppingCartList(orderID);
        Order order = orderService.getOrderByOrderID(orderID);

        ModelAndView modelAndView = new ModelAndView("shoppingCart");

        modelAndView.addObject("shoppingCartList", shoppingCartList);
        modelAndView.addObject("order", order);

        return modelAndView;
    }

    @RequestMapping("/shoppingCart/removeItem")
    public String shoppingCartRemoveItem(@RequestParam int orderID, int orderItemID) {

        orderItemService.deleteOrderItem(orderItemID);

        Order order = orderService.getOrderByOrderID(orderID);
        httpSession.setAttribute("order", order);

        return "redirect:/shoppingCart/viewCart?orderID=" + orderID;
    }

    @RequestMapping("/shoppingCart/updateItem")
    public String shoppingCartUpdateItem(@RequestParam int orderID, int orderItemID, int quantity) {

        OrderItem orderItem = orderItemService.getOrderItem(orderItemID);
        Product product = productService.getProductByProductID(orderItem.getProductID());

        orderItem.setQuantity(quantity);
        orderItem.setOrderItemSubTotal(orderItem.getPricePaid() * quantity);

        orderItemService.updateOrderItem(orderItem);

        Order order = orderService.getOrderByOrderID(orderID);
        httpSession.setAttribute("order", order);

        if(quantity > product.getQuantityOnHand()){
            return "redirect:/shoppingCart/viewCart?orderID=" + order.getOrderID() + "&quantityOnHand=" + product.getQuantityOnHand();
        }
        else {
            return "redirect:/shoppingCart/viewCart?orderID=" + order.getOrderID();
        }
    }

    @RequestMapping("/order/enterShipping")
    public ModelAndView orderEnterShipping(@RequestParam int orderID){

        Order order = orderService.getOrderByOrderID(orderID);
        List<CustomerAddress> customerAddressList = customerService.getCustomerAddressList(order.getCustomerID());
        List<AddressState> addressStateList = baseService.getAddressStateList();
        List<ShippingSpeed> shippingSpeedList = orderService.getShippingSpeedList();

        ModelAndView modelAndView = new ModelAndView("orderEnterShipping");

        modelAndView.addObject("order", order);
        modelAndView.addObject("customerAddressList", customerAddressList);
        modelAndView.addObject("addressStateList", addressStateList);
        modelAndView.addObject("shippingSpeedList", shippingSpeedList);

        return modelAndView;
    }

    @RequestMapping("/order/enterShipping/bindAddress")
    public String orderEnterShippingBindAddress(@RequestParam int orderID, int addressID){

        Order order = orderService.getOrderByOrderID(orderID);
        CustomerAddress customerAddress = customerService.getCustomerAddressByAddressID(addressID);

        if(orderService.shippingAllowed(customerAddress.getStateCode())) {
            if (order.getShippingToName() == null) {
                Customer customer = customerService.getCustomerByCustomerID(order.getCustomerID());
                order.setShippingToName(customer.getFirstName() + " " + customer.getLastName());
            }
            order.setShippingAddressLine1(customerAddress.getAddressLine1());
            order.setShippingAddressLine2(customerAddress.getAddressLine2());
            order.setShippingCity(customerAddress.getCity());
            order.setShippingStateCode(customerAddress.getStateCode());
            order.setShippingPostalCode(customerAddress.getPostalCode());

            orderService.updateOrder(order);

            return "redirect:/order/enterShipping?orderID=" + orderID;
        }
        else{
            return "redirect:/order/enterShipping?orderID=" + orderID + "&stateCode=" + customerAddress.getStateCode() + "&shippingNotAllowed=true";
        }
    }

    @RequestMapping("/order/enterShipping/submit")
    public String orderEnterShippingSubmit(@ModelAttribute Order maOrder){

        Order order = orderService.getOrderByOrderID(maOrder.getOrderID());

        if (maOrder.getSaveShippingAddress()) {

            CustomerAddress customerAddress = new CustomerAddress();

            customerAddress.setCustomerID(order.getCustomerID());
            customerAddress.setAddressLine1(maOrder.getShippingAddressLine1());
            customerAddress.setAddressLine2(maOrder.getShippingAddressLine2());
            customerAddress.setCity(maOrder.getShippingCity());
            customerAddress.setStateCode(maOrder.getShippingStateCode());
            customerAddress.setPostalCode(maOrder.getShippingPostalCode());

            customerService.insertCustomerAddress(customerAddress);
        }

        if(orderService.shippingAllowed(maOrder.getShippingStateCode())) {
            if (maOrder.getShippingToName().isEmpty() || order.getShippingToName() == null) {
                Customer customer = customerService.getCustomerByCustomerID(order.getCustomerID());
                order.setShippingToName(customer.getFirstName() + " " + customer.getLastName());
            } else {
                order.setShippingToName(maOrder.getShippingToName());
            }

            order.setShippingAddressLine1(maOrder.getShippingAddressLine1());
            order.setShippingAddressLine2(maOrder.getShippingAddressLine2());
            order.setShippingCity(maOrder.getShippingCity());
            order.setShippingStateCode(maOrder.getShippingStateCode());
            order.setShippingPostalCode(maOrder.getShippingPostalCode());
            order.setShippingSpeedCode(maOrder.getShippingSpeedCode());
            order.setStateTaxRateID(orderService.getStateTaxRateID(order.getShippingStateCode()));
            order.setTaxTotal(order.getOrderSubTotal() * orderService.getStateTaxRate(order.getStateTaxRateID()));
            order.setShipperID(maOrder.getShipperID());
            order.setShippingSpeedCode(maOrder.getShippingSpeedCode());
            order.setShippingTotal(orderService.getShippingRate(order.getShipperID(), order.getShippingSpeedCode()));

            orderService.updateOrder(order);

            return "redirect:/order/enterPayment?orderID=" + order.getOrderID();
        }
        else{
            return "redirect:/order/enterShipping?orderID=" + maOrder.getOrderID() + "&stateCode=" + maOrder.getShippingStateCode() + "&shippingNotAllowed=true";
        }
    }

    @RequestMapping("/order/enterPayment")
    public ModelAndView orderEnterPayment(@RequestParam int orderID){

        Order order = orderService.getOrderByOrderID(orderID);
        List<CustomerPaymentCard> customerPaymentCardList = customerService.getCustomerPaymentCardList(order.getCustomerID());
        List<PaymentCardType> paymentCardTypeList = baseService.getPaymentCardTypeList();
        List<AddressState> addressStateList = baseService.getAddressStateList();

        ModelAndView modelAndView = new ModelAndView("orderEnterPayment");

        modelAndView.addObject("order", order);
        modelAndView.addObject("customerPaymentCardList", customerPaymentCardList);
        modelAndView.addObject("paymentCardTypeList", paymentCardTypeList);
        modelAndView.addObject("addressStateList", addressStateList);

        return modelAndView;
    }

    @RequestMapping("/order/enterPayment/bindPaymentCard")
    public String orderEnterPaymentBindPaymentCard(@RequestParam int orderID, int paymentCardID){

        Order order = orderService.getOrderByOrderID(orderID);

        order.setPaymentCardID(paymentCardID);

        orderService.updateOrder(order);

        return "redirect:/order/enterPayment?orderID=" + orderID;
    }

    @RequestMapping("/order/enterPayment/submit")
    public String orderEnterPaymentSubmit(@ModelAttribute Order maOrder){

        Order order = orderService.getOrderByOrderID(maOrder.getOrderID());
        CustomerPaymentCard customerPaymentCard;

        if((maOrder.getPaymentCardNumber().contains("*"))){

            customerPaymentCard = customerService.getCustomerPaymentCardByPaymentCardID(maOrder.getPaymentCardID());

            customerPaymentCard.setPaymentCardTypeID(maOrder.getPaymentCardTypeID());
            customerPaymentCard.setPaymentCardCVV(maOrder.getPaymentCardCVV());
            customerPaymentCard.setPaymentCardExpiry(maOrder.getPaymentCardExpiry());
            customerPaymentCard.setPaymentCardHolderName(maOrder.getPaymentCardHolderName());
            customerPaymentCard.setPaymentCardAddressLine1(maOrder.getPaymentCardAddressLine1());
            customerPaymentCard.setPaymentCardAddressLine2(maOrder.getPaymentCardAddressLine2());
            customerPaymentCard.setPaymentCardCity(maOrder.getPaymentCardCity());
            customerPaymentCard.setPaymentCardStateCode(maOrder.getPaymentCardStateCode());
            customerPaymentCard.setPaymentCardPostalCode(maOrder.getPaymentCardPostalCode());
            if (maOrder.getSavePaymentCard()) {
                customerPaymentCard.setDisplayInd("Y");
            }

            customerService.updateCustomerPaymentCard(customerPaymentCard);
        }
        else{

            customerPaymentCard = customerService.getCustomerPaymentCardByPaymentCardNumber(maOrder.getPaymentCardNumber(), order.getCustomerID());

            if(customerPaymentCard == null){

                customerPaymentCard = new CustomerPaymentCard();

                customerPaymentCard.setCustomerID(order.getCustomerID());
                customerPaymentCard.setPaymentCardTypeID(maOrder.getPaymentCardTypeID());
                customerPaymentCard.setPaymentCardNumber(maOrder.getPaymentCardNumber());
                customerPaymentCard.setPaymentCardCVV(maOrder.getPaymentCardCVV());
                customerPaymentCard.setPaymentCardExpiry(maOrder.getPaymentCardExpiry());
                customerPaymentCard.setPaymentCardHolderName(maOrder.getPaymentCardHolderName());
                customerPaymentCard.setPaymentCardAddressLine1(maOrder.getPaymentCardAddressLine1());
                customerPaymentCard.setPaymentCardAddressLine2(maOrder.getPaymentCardAddressLine2());
                customerPaymentCard.setPaymentCardCity(maOrder.getPaymentCardCity());
                customerPaymentCard.setPaymentCardStateCode(maOrder.getPaymentCardStateCode());
                customerPaymentCard.setPaymentCardPostalCode(maOrder.getPaymentCardPostalCode());
                if (maOrder.getSavePaymentCard()) {
                    customerPaymentCard.setDisplayInd("Y");
                } else {
                    customerPaymentCard.setDisplayInd("N");
                }

                customerPaymentCard.setPaymentCardID(customerService.insertCustomerPaymentCard(customerPaymentCard));
            }
            else {

                customerPaymentCard.setPaymentCardTypeID(maOrder.getPaymentCardTypeID());
                customerPaymentCard.setPaymentCardCVV(maOrder.getPaymentCardCVV());
                customerPaymentCard.setPaymentCardExpiry(maOrder.getPaymentCardExpiry());
                customerPaymentCard.setPaymentCardHolderName(maOrder.getPaymentCardHolderName());
                customerPaymentCard.setPaymentCardAddressLine1(maOrder.getPaymentCardAddressLine1());
                customerPaymentCard.setPaymentCardAddressLine2(maOrder.getPaymentCardAddressLine2());
                customerPaymentCard.setPaymentCardCity(maOrder.getPaymentCardCity());
                customerPaymentCard.setPaymentCardStateCode(maOrder.getPaymentCardStateCode());
                customerPaymentCard.setPaymentCardPostalCode(maOrder.getPaymentCardPostalCode());
                if (maOrder.getSavePaymentCard()) {
                    customerPaymentCard.setDisplayInd("Y");
                }

                customerService.updateCustomerPaymentCard(customerPaymentCard);
            }

        }

        order.setPaymentCardID(customerPaymentCard.getPaymentCardID());
        orderService.updateOrder(order);

        return "redirect:/order/placeOrder?orderID=" + order.getOrderID();
    }

    @RequestMapping("/order/placeOrder")
    public ModelAndView orderPlaceOrder(@RequestParam int orderID){

        List<ShoppingCart> shoppingCartList = orderItemService.getShoppingCartList(orderID);
        Order order = orderService.getOrderByOrderID(orderID);

        ModelAndView modelAndView = new ModelAndView("orderPlaceOrder");

        modelAndView.addObject("shoppingCartList", shoppingCartList);
        modelAndView.addObject("order", order);

        return modelAndView;
    }

    @RequestMapping("/order/placeOrder/submit")
    public String orderPlaceOrderSubmit(@ModelAttribute Order maOrder) {

        Order order = orderService.getOrderByOrderID(maOrder.getOrderID());

        List<ShoppingCart> shoppingCartList = orderItemService.getShoppingCartList(order.getOrderID());

        boolean backOrderedItem = false;

        for (ShoppingCart orderItem : shoppingCartList){

            boolean inventoryUpdated = productService.updateInventory(orderItem.getProductID(), orderItem.getQuantity());

            if (!inventoryUpdated){
                backOrderedItem = true;
            }
        }

        order.setOrderSubTotal(maOrder.getOrderSubTotal());
        order.setOrderPlaced(new java.sql.Timestamp(System.currentTimeMillis()));
        order.setOrderStatusCode("C");

        orderService.updateOrder(order);

        httpSession.removeAttribute("order");

        if (backOrderedItem) {
            return "redirect:/order/confirmation?backOrderedItem=true";
        }
        else{
            return "redirect:/order/confirmation";
        }
    }

    @RequestMapping("/order/confirmation")
    public String orderConfirmation (){
        return "orderConfirmation";
    }

    @RequestMapping("/order/cancelOrder")
    public String cancelOrder(@RequestParam int orderID) {

        Order order = orderService.getOrderByOrderID(orderID);
        order.setOrderStatusCode("X");
        orderService.updateOrder(order);

        return "redirect:/customer/viewOpenOrders?customerID=" + order.getCustomerID();
    }
}
