package com.competiton.pregnancy.pregnancyapp.model;

public class PregaShopDetails {


    private String itemName;
    private String itemUrl;

    public PregaShopDetails(String itemName, String itemUrl) {
        this.itemName = itemName;
        this.itemUrl = itemUrl;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemUrl() {
        return itemUrl;
    }
}
