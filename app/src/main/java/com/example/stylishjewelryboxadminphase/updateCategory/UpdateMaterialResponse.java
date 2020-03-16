package com.example.stylishjewelryboxadminphase.updateCategory;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateMaterialResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;

    /**
     * No args constructor for use in serialization
     *
     */
    public UpdateMaterialResponse() {
    }

    /**
     *
     * @param status
     */
    public UpdateMaterialResponse(Boolean status) {
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