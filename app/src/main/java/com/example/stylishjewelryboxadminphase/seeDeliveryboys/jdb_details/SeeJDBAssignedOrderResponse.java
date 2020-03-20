package com.example.stylishjewelryboxadminphase.seeDeliveryboys.jdb_details;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SeeJDBAssignedOrderResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("SeeJDBAssignedOrder")
    @Expose
    private SeeJDBAssignedOrder seeJDBAssignedOrder;

    /**
     * No args constructor for use in serialization
     *
     */
    public SeeJDBAssignedOrderResponse() {
    }

    /**
     *
     * @param seeJDBAssignedOrder
     * @param status
     */
    public SeeJDBAssignedOrderResponse(Boolean status, SeeJDBAssignedOrder seeJDBAssignedOrder) {
        super();
        this.status = status;
        this.seeJDBAssignedOrder = seeJDBAssignedOrder;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public SeeJDBAssignedOrder getSeeJDBAssignedOrder() {
        return seeJDBAssignedOrder;
    }

    public void setSeeJDBAssignedOrder(SeeJDBAssignedOrder seeJDBAssignedOrder) {
        this.seeJDBAssignedOrder = seeJDBAssignedOrder;
    }

}