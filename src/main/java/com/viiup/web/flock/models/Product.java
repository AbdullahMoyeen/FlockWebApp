package com.viiup.web.flock.models;

/**
 * Created by amoyeen on 3/1/15.
 */
public class Product {

    private int productID;
    private int manufacturerID;
    private String manufacturerName;
    private String productName;
    private String productDescription;
    private float price;
    private String imageFileLocation;
    private String imageFileName;
    private int quantityOnHand;
    private int addToCartQuantity;

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getManufacturerID() {
        return manufacturerID;
    }

    public void setManufacturerID(int manufacturerID) {
        this.manufacturerID = manufacturerID;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImageFileLocation() {
        return imageFileLocation;
    }

    public void setImageFileLocation(String imageFileLocation) {
        this.imageFileLocation = imageFileLocation;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public int getQuantityOnHand() {
        return quantityOnHand;
    }

    public void setQuantityOnHand(int quantityOnHand) {
        this.quantityOnHand = quantityOnHand;
    }

    public int getAddToCartQuantity() {
        return addToCartQuantity;
    }

    public void setAddToCartQuantity(int addToCartQuantity) {
        this.addToCartQuantity = addToCartQuantity;
    }
}
