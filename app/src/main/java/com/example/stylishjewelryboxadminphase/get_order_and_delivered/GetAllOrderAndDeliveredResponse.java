package com.example.stylishjewelryboxadminphase.get_order_and_delivered;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllOrderAndDeliveredResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("GetAllOrderAndDelivered")
    @Expose
    private GetAllOrderAndDelivered getAllOrderAndDelivered;

    /**
     * No args constructor for use in serialization
     */
    public GetAllOrderAndDeliveredResponse() {
    }

    /**
     * @param getAllOrderAndDelivered
     * @param status
     */
    public GetAllOrderAndDeliveredResponse(Boolean status, GetAllOrderAndDelivered getAllOrderAndDelivered) {
        super();
        this.status = status;
        this.getAllOrderAndDelivered = getAllOrderAndDelivered;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public GetAllOrderAndDelivered getGetAllOrderAndDelivered() {
        return getAllOrderAndDelivered;
    }

    public void setGetAllOrderAndDelivered(GetAllOrderAndDelivered getAllOrderAndDelivered) {
        this.getAllOrderAndDelivered = getAllOrderAndDelivered;
    }

}