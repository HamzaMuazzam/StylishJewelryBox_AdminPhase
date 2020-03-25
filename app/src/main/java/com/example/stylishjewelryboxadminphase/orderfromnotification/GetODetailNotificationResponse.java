package com.example.stylishjewelryboxadminphase.orderfromnotification;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetODetailNotificationResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("GetODetailNotification")
    @Expose
    private List<GetODetailNotification> getODetailNotification = null;

    /**
     * No args constructor for use in serialization
     */
    public GetODetailNotificationResponse() {
    }

    /**
     * @param getODetailNotification
     * @param status
     */
    public GetODetailNotificationResponse(Boolean status, List<GetODetailNotification> getODetailNotification) {
        super();
        this.status = status;
        this.getODetailNotification = getODetailNotification;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<GetODetailNotification> getGetODetailNotification() {
        return getODetailNotification;
    }

    public void setGetODetailNotification(List<GetODetailNotification> getODetailNotification) {
        this.getODetailNotification = getODetailNotification;
    }

}