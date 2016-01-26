package com.viiup.web.flock.models;

/**
 * Created by HP on 2/23/2015.
 */
public class OrderItem {
    private int orderID;
    private int orderItemID;
    private int productID;
    private int quantity;
    private Float pricePaid;
    private Float orderItemSubTotal;

    public int getOrderItemID(){ return orderItemID; }
    public void setOrderItemID(int orderItemID) { this.orderItemID= orderItemID; }

    public int getOrderID(){ return orderID; }
    public void setOrderID(int orderID) { this.orderID= orderID; }

    public int getProductID(){ return productID; }
    public void setProductID(int productID) { this.productID= productID; }

    public int getQuantity(){ return quantity; }
    public void setQuantity(int quantity) { this.quantity= quantity; }

    public Float getPricePaid(){ return pricePaid; }
    public void setPricePaid(Float pricePaid) { this.pricePaid= pricePaid; }

    public Float getOrderItemSubTotal(){ return orderItemSubTotal; }
    public void setOrderItemSubTotal(Float orderItemSubTotal) { this.orderItemSubTotal= orderItemSubTotal; }


}
