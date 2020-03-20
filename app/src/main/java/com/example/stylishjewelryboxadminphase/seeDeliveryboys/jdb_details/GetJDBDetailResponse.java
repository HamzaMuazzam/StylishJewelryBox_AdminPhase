package com.example.stylishjewelryboxadminphase.seeDeliveryboys.jdb_details;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetJDBDetailResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("GetJDBDetail")
    @Expose
    private List<GetJDBDetail> getJDBDetail = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetJDBDetailResponse() {
    }

    /**
     *
     * @param getJDBDetail
     * @param status
     */
    public GetJDBDetailResponse(Boolean status, List<GetJDBDetail> getJDBDetail) {
        super();
        this.status = status;
        this.getJDBDetail = getJDBDetail;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<GetJDBDetail> getGetJDBDetail() {
        return getJDBDetail;
    }

    public void setGetJDBDetail(List<GetJDBDetail> getJDBDetail) {
        this.getJDBDetail = getJDBDetail;
    }

}