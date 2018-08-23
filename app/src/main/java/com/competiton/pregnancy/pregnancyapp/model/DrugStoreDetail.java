package com.competiton.pregnancy.pregnancyapp.model;

public class DrugStoreDetail  {

    private String drugName, drugPrice;
    private int drugImage;

    public DrugStoreDetail(String drugName, int drugImage, String drugPrice) {
        this.drugName = drugName;
        this.drugImage = drugImage;
        this.drugPrice = drugPrice;
    }

    public String getDrugName() {
        return drugName;
    }

    public int getDrugImage() {
        return drugImage;
    }

    public String getDrugPrice() {
        return drugPrice;
    }
}
