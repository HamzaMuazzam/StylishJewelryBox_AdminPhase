package com.example.stylishjewelryboxadminphase.calculations;
import android.graphics.Bitmap;

public class Calculation_Model_Class {
    private Bitmap cats_image;
    private String cats_name;

    public Calculation_Model_Class(Bitmap cats_image, String cats_name) {
        this.cats_image = cats_image;
        this.cats_name = cats_name;
    }

    public Bitmap getCats_image() {
        return cats_image;
    }

    public void setCats_image(Bitmap cats_image) {
        this.cats_image = cats_image;
    }

    public String getCats_name() {
        return cats_name;
    }

    public void setCats_name(String cats_name) {
        this.cats_name = cats_name;
    }
}