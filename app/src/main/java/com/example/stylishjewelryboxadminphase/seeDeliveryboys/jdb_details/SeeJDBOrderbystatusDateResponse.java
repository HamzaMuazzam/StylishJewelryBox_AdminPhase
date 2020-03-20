package com.example.stylishjewelryboxadminphase.seeDeliveryboys.jdb_details;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SeeJDBOrderbystatusDateResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("SeeJDBOrderbystatusDate")
    @Expose
    private SeeJDBOrderbystatusDate seeJDBOrderbystatusDate;

    /**
     * No args constructor for use in serialization
     *
     */
    public SeeJDBOrderbystatusDateResponse() {
    }

    /**
     *
     * @param seeJDBOrderbystatusDate
     * @param status
     */
    public SeeJDBOrderbystatusDateResponse(Boolean status, SeeJDBOrderbystatusDate seeJDBOrderbystatusDate) {
        super();
        this.status = status;
        this.seeJDBOrderbystatusDate = seeJDBOrderbystatusDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public SeeJDBOrderbystatusDate getSeeJDBOrderbystatusDate() {
        return seeJDBOrderbystatusDate;
    }

    public void setSeeJDBOrderbystatusDate(SeeJDBOrderbystatusDate seeJDBOrderbystatusDate) {
        this.seeJDBOrderbystatusDate = seeJDBOrderbystatusDate;
    }

}