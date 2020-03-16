package com.example.stylishjewelryboxadminphase.calculations.percentages;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllForPercentageResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("GetAllForPercentage")
    @Expose
    private GetAllForPercentage getAllForPercentage;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetAllForPercentageResponse() {
    }

    /**
     *
     * @param getAllForPercentage
     * @param status
     */
    public GetAllForPercentageResponse(Boolean status, GetAllForPercentage getAllForPercentage) {
        super();
        this.status = status;
        this.getAllForPercentage = getAllForPercentage;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public GetAllForPercentage getGetAllForPercentage() {
        return getAllForPercentage;
    }

    public void setGetAllForPercentage(GetAllForPercentage getAllForPercentage) {
        this.getAllForPercentage = getAllForPercentage;
    }

}