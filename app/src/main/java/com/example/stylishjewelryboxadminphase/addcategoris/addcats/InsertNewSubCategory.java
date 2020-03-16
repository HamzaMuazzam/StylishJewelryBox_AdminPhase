package com.example.stylishjewelryboxadminphase.addcategoris.addcats;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InsertNewSubCategory {

    @SerializedName("status")
    @Expose
    private Boolean status;

    /**
     * No args constructor for use in serialization
     *
     */
    public InsertNewSubCategory() {
    }

    /**
     *
     * @param status
     */
    public InsertNewSubCategory(Boolean status) {
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