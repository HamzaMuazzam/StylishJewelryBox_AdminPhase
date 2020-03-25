package com.example.stylishjewelryboxadminphase.get_order_and_delivered;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllOrderAndDelivered {

    @SerializedName("total_order")
    @Expose
    private String totalOrder;
    @SerializedName("total_delivered")
    @Expose
    private String totalDelivered;

    /**
     * No args constructor for use in serialization
     */
    public GetAllOrderAndDelivered() {
    }

    /**
     * @param totalDelivered
     * @param totalOrder
     */
    public GetAllOrderAndDelivered(String totalOrder, String totalDelivered) {
        super();
        this.totalOrder = totalOrder;
        this.totalDelivered = totalDelivered;
    }

    public String getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(String totalOrder) {
        this.totalOrder = totalOrder;
    }

    public String getTotalDelivered() {
        return totalDelivered;
    }

    public void setTotalDelivered(String totalDelivered) {
        this.totalDelivered = totalDelivered;
    }

}