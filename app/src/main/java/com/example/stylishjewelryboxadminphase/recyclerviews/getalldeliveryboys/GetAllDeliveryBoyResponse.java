package com.example.stylishjewelryboxadminphase.recyclerviews.getalldeliveryboys;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllDeliveryBoyResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("GetAllDeliveryBoy")
    @Expose
    private List<GetAllDeliveryBoy> getAllDeliveryBoy = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetAllDeliveryBoyResponse() {
    }

    /**
     *
     * @param getAllDeliveryBoy
     * @param status
     */
    public GetAllDeliveryBoyResponse(Boolean status, List<GetAllDeliveryBoy> getAllDeliveryBoy) {
        super();
        this.status = status;
        this.getAllDeliveryBoy = getAllDeliveryBoy;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<GetAllDeliveryBoy> getGetAllDeliveryBoy() {
        return getAllDeliveryBoy;
    }

    public void setGetAllDeliveryBoy(List<GetAllDeliveryBoy> getAllDeliveryBoy) {
        this.getAllDeliveryBoy = getAllDeliveryBoy;
    }

}