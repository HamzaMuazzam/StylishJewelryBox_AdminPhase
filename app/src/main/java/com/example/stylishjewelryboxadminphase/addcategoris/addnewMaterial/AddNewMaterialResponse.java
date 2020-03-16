package com.example.stylishjewelryboxadminphase.addcategoris.addnewMaterial;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddNewMaterialResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;

    /**
     * No args constructor for use in serialization
     *
     */
    public AddNewMaterialResponse() {
    }

    /**
     *
     * @param status
     */
    public AddNewMaterialResponse(Boolean status) {
        super();
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}