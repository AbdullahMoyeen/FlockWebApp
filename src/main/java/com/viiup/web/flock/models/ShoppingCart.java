package com.viiup.web.flock.models;

/**
 * Created by HP on 2/25/2015.
 */
public class ShoppingCart {
    private int orderItemID;
    private int orderID;
    private int productID;
    private int quantity;
    private Float pricePaid;
    private Float orderItemSubTotal;
    private String productName;
    private String productDescription;
    private Float price;
    private String imageFileLocation;
    private String imageFileName;
    private int manufacturerID;
    private String manufacturerName;

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

    public String getProductName(){ return productName; }
    public void setProductName(String productName) { this.productName= productName; }

    public String getProductDescription(){ return productDescription; }
    public void setProductDescription(String productDescription) { this.productDescription= productDescription; }

    public Float getPrice(){ return price; }
    public void setPrice(Float price) { this.price= price; }

    public String getImageFileLocation(){ return imageFileLocation; }
    public void setImageFileLocation(String imageFileLocation) { this.imageFileLocation= imageFileLocation; }

    public String getImageFileName(){ return imageFileName; }
    public void setImageFileName(String imageFileName) { this.imageFileName= imageFileName; }

    public int getManufacturerID(){ return manufacturerID; }
    public void setManufacturerID(int manufacturerID) { this.manufacturerID= manufacturerID; }

    public String getManufacturerName(){ return manufacturerName; }
    public void setManufacturerName(String manufacturerName) { this.manufacturerName= manufacturerName; }
}
