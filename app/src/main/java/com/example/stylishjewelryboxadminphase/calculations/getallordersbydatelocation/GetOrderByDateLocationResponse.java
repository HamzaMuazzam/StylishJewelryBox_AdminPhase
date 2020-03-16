package com.example.stylishjewelryboxadminphase.calculations.getallordersbydatelocation;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetOrderByDateLocationResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("GetOrderByDateLocation")
    @Expose
    private List<GetOrderByDateLocation> getOrderByDateLocation = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetOrderByDateLocationResponse() {
    }

    /**
     *
     * @param getOrderByDateLocation
     * @param status
     */
    public GetOrderByDateLocationResponse(Boolean status, List<GetOrderByDateLocation> getOrderByDateLocation) {
        super();
        this.status = status;
        this.getOrderByDateLocation = getOrderByDateLocation;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<GetOrderByDateLocation> getGetOrderByDateLocation() {
        return getOrderByDateLocation;
    }

    public void setGetOrderByDateLocation(List<GetOrderByDateLocation> getOrderByDateLocation) {
        this.getOrderByDateLocation = getOrderByDateLocation;
    }

}