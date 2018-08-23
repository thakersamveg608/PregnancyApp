package com.competiton.pregnancy.pregnancyapp.model;

public class DrugStoreDetail  {

    private String drugName, drugImage;

    public DrugStoreDetail(String drugName, String drugImage) {
        this.drugName = drugName;
        this.drugImage = drugImage;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDrugImage() {
        return drugImage;
    }

    public void setDrugImage(String drugImage) {
        this.drugImage = drugImage;
    }
}
