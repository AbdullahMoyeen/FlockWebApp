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

import java.util.Date;

/**
 * Created by amoyeen on 3/1/15.
 */
@Controller
public class EventController {

    @Autowired
    HttpSession httpSession;

    @Autowired
    IBaseService baseService;

    @Autowired
    IUserService userService;

    @Autowired
    IGroupService groupService;

    @Autowired
    IEventService eventService;

    @Autowired
    IOrderItemService orderItemService;

    @RequestMapping("/groupEvents/eventDetail")
    public ModelAndView groupEventsCallEvent(@RequestParam int eventID, int groupID){

        EventModel event = eventID == 0 ? new EventModel(): eventService.getEventByEventID(eventID);
        event.setGroupID(groupID);
        ModelAndView modelAndView = new ModelAndView("adminGroupEventDetails");
        modelAndView.addObject("event", event);

        return modelAndView;
    }

    @RequestMapping("/groupEventDetail/submit")
    public String eventSubmit(@ModelAttribute EventModel event){
        if (event.getEventID() == 0) {
            String str = "Flock_DEV";
            event.setCreateDate(new Date());
            event.setCreateUser(str);
            event.setUpdateDate(new Date());
            event.setUpdateUser(str);
            event.setEventStartDatetime(new Date());
            event.seteventEndDatetime(new Date());
            event.setEventStateCode("TX");
            eventService.insertEvent(event);
        }else {
            event.setCreateDate(new Date());
            event.setCreateUser("Flock_DEV");
            event.setUpdateDate(new Date());
            event.setUpdateUser("Flock_DEV");
            event.setEventStartDatetime(new Date());
            event.seteventEndDatetime(new Date());

            eventService.updateEvent(event);
        }

        return "redirect:/customer/viewProfile?customerID=" + event.getGroupID();
    }

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

            int orderID = eventService.insertOrder(order);

            order.setOrderID(orderID);
        }

        Product product = groupService.getProductByProductID(productID);

        OrderItem orderItem = new OrderItem();

        orderItem.setOrderID(order.getOrderID());
        orderItem.setProductID(product.getProductID());
        orderItem.setQuantity(addToCartQuantity);
        orderItem.setPricePaid(product.getPrice());
        orderItem.setOrderItemSubTotal(product.getPrice() * addToCartQuantity);

        orderItemService.insertOrderItem(orderItem);

        order = eventService.getOrderByOrderID(order.getOrderID());
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
        Order order = eventService.getOrderByOrderID(orderID);

        ModelAndView modelAndView = new ModelAndView("shoppingCart");

        modelAndView.addObject("shoppingCartList", shoppingCartList);
        modelAndView.addObject("order", order);

        return modelAndView;
    }

    @RequestMapping("/shoppingCart/removeItem")
    public String shoppingCartRemoveItem(@RequestParam int orderID, int orderItemID) {

        orderItemService.deleteOrderItem(orderItemID);

        Order order = eventService.getOrderByOrderID(orderID);
        httpSession.setAttribute("order", order);

        return "redirect:/shoppingCart/viewCart?orderID=" + orderID;
    }

    @RequestMapping("/shoppingCart/updateItem")
    public String shoppingCartUpdateItem(@RequestParam int orderID, int orderItemID, int quantity) {

        OrderItem orderItem = orderItemService.getOrderItem(orderItemID);
        Product product = groupService.getProductByProductID(orderItem.getProductID());

        orderItem.setQuantity(quantity);
        orderItem.setOrderItemSubTotal(orderItem.getPricePaid() * quantity);

        orderItemService.updateOrderItem(orderItem);

        Order order = eventService.getOrderByOrderID(orderID);
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

        Order order = eventService.getOrderByOrderID(orderID);
        List<CustomerAddress> customerAddressList = userService.getCustomerAddressList(order.getCustomerID());
        List<AddressState> addressStateList = baseService.getAddressStateList();
        List<ShippingSpeed> shippingSpeedList = eventService.getShippingSpeedList();

        ModelAndView modelAndView = new ModelAndView("orderEnterShipping");

        modelAndView.addObject("order", order);
        modelAndView.addObject("customerAddressList", customerAddressList);
        modelAndView.addObject("addressStateList", addressStateList);
        modelAndView.addObject("shippingSpeedList", shippingSpeedList);

        return modelAndView;
    }

    @RequestMapping("/order/enterShipping/bindAddress")
    public String orderEnterShippingBindAddress(@RequestParam int orderID, int addressID){

        Order order = eventService.getOrderByOrderID(orderID);
        CustomerAddress customerAddress = userService.getCustomerAddressByAddressID(addressID);

        if(eventService.shippingAllowed(customerAddress.getStateCode())) {
            if (order.getShippingToName() == null) {
                Customer customer = userService.getCustomerByCustomerID(order.getCustomerID());
                order.setShippingToName(customer.getFirstName() + " " + customer.getLastName());
            }
            order.setShippingAddressLine1(customerAddress.getAddressLine1());
            order.setShippingAddressLine2(customerAddress.getAddressLine2());
            order.setShippingCity(customerAddress.getCity());
            order.setShippingStateCode(customerAddress.getStateCode());
            order.setShippingPostalCode(customerAddress.getPostalCode());

            eventService.updateOrder(order);

            return "redirect:/order/enterShipping?orderID=" + orderID;
        }
        else{
            return "redirect:/order/enterShipping?orderID=" + orderID + "&stateCode=" + customerAddress.getStateCode() + "&shippingNotAllowed=true";
        }
    }

    @RequestMapping("/order/enterShipping/submit")
    public String orderEnterShippingSubmit(@ModelAttribute Order maOrder){

        Order order = eventService.getOrderByOrderID(maOrder.getOrderID());

        if (maOrder.getSaveShippingAddress()) {

            CustomerAddress customerAddress = new CustomerAddress();

            customerAddress.setCustomerID(order.getCustomerID());
            customerAddress.setAddressLine1(maOrder.getShippingAddressLine1());
            customerAddress.setAddressLine2(maOrder.getShippingAddressLine2());
            customerAddress.setCity(maOrder.getShippingCity());
            customerAddress.setStateCode(maOrder.getShippingStateCode());
            customerAddress.setPostalCode(maOrder.getShippingPostalCode());

            userService.insertCustomerAddress(customerAddress);
        }

        if(eventService.shippingAllowed(maOrder.getShippingStateCode())) {
            if (maOrder.getShippingToName().isEmpty() || order.getShippingToName() == null) {
                Customer customer = userService.getCustomerByCustomerID(order.getCustomerID());
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
            order.setStateTaxRateID(eventService.getStateTaxRateID(order.getShippingStateCode()));
            order.setTaxTotal(order.getOrderSubTotal() * eventService.getStateTaxRate(order.getStateTaxRateID()));
            order.setShipperID(maOrder.getShipperID());
            order.setShippingSpeedCode(maOrder.getShippingSpeedCode());
            order.setShippingTotal(eventService.getShippingRate(order.getShipperID(), order.getShippingSpeedCode()));

            eventService.updateOrder(order);

            return "redirect:/order/enterPayment?orderID=" + order.getOrderID();
        }
        else{
            return "redirect:/order/enterShipping?orderID=" + maOrder.getOrderID() + "&stateCode=" + maOrder.getShippingStateCode() + "&shippingNotAllowed=true";
        }
    }

    @RequestMapping("/order/enterPayment")
    public ModelAndView orderEnterPayment(@RequestParam int orderID){

        Order order = eventService.getOrderByOrderID(orderID);
        List<CustomerPaymentCard> customerPaymentCardList = userService.getCustomerPaymentCardList(order.getCustomerID());
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

        Order order = eventService.getOrderByOrderID(orderID);

        order.setPaymentCardID(paymentCardID);

        eventService.updateOrder(order);

        return "redirect:/order/enterPayment?orderID=" + orderID;
    }

    @RequestMapping("/order/enterPayment/submit")
    public String orderEnterPaymentSubmit(@ModelAttribute Order maOrder){

        Order order = eventService.getOrderByOrderID(maOrder.getOrderID());
        CustomerPaymentCard customerPaymentCard;

        if((maOrder.getPaymentCardNumber().contains("*"))){

            customerPaymentCard = userService.getCustomerPaymentCardByPaymentCardID(maOrder.getPaymentCardID());

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

            userService.updateCustomerPaymentCard(customerPaymentCard);
        }
        else{

            customerPaymentCard = userService.getCustomerPaymentCardByPaymentCardNumber(maOrder.getPaymentCardNumber(), order.getCustomerID());

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

                customerPaymentCard.setPaymentCardID(userService.insertCustomerPaymentCard(customerPaymentCard));
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

                userService.updateCustomerPaymentCard(customerPaymentCard);
            }

        }

        order.setPaymentCardID(customerPaymentCard.getPaymentCardID());
        eventService.updateOrder(order);

        return "redirect:/order/placeOrder?orderID=" + order.getOrderID();
    }

    @RequestMapping("/order/placeOrder")
    public ModelAndView orderPlaceOrder(@RequestParam int orderID){

        List<ShoppingCart> shoppingCartList = orderItemService.getShoppingCartList(orderID);
        Order order = eventService.getOrderByOrderID(orderID);

        ModelAndView modelAndView = new ModelAndView("orderPlaceOrder");

        modelAndView.addObject("shoppingCartList", shoppingCartList);
        modelAndView.addObject("order", order);

        return modelAndView;
    }

    @RequestMapping("/order/placeOrder/submit")
    public String orderPlaceOrderSubmit(@ModelAttribute Order maOrder) {

        Order order = eventService.getOrderByOrderID(maOrder.getOrderID());

        List<ShoppingCart> shoppingCartList = orderItemService.getShoppingCartList(order.getOrderID());

        boolean backOrderedItem = false;

        for (ShoppingCart orderItem : shoppingCartList){

            boolean inventoryUpdated = groupService.updateInventory(orderItem.getProductID(), orderItem.getQuantity());

            if (!inventoryUpdated){
                backOrderedItem = true;
            }
        }

        order.setOrderSubTotal(maOrder.getOrderSubTotal());
        order.setOrderPlaced(new java.sql.Timestamp(System.currentTimeMillis()));
        order.setOrderStatusCode("C");

        eventService.updateOrder(order);

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

        Order order = eventService.getOrderByOrderID(orderID);
        order.setOrderStatusCode("X");
        eventService.updateOrder(order);

        return "redirect:/customer/viewOpenOrders?customerID=" + order.getCustomerID();
    }
}
