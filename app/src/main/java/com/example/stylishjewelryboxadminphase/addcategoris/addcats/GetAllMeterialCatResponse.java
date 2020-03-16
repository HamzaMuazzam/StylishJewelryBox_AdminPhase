package com.example.stylishjewelryboxadminphase.addcategoris.addcats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetAllMeterialCatResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("GetAllMeterialCat")
    @Expose
    private List<GetAllMeterialCat> getAllMeterialCat = null;

    /**
     * No args constructor for use in serialization
     */
    public GetAllMeterialCatResponse() {
    }

    /**
     * @param getAllMeterialCat
     * @param status
     */
    public GetAllMeterialCatResponse(Boolean status, List<GetAllMeterialCat> getAllMeterialCat) {
        super();
        this.status = status;
        this.getAllMeterialCat = getAllMeterialCat;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<GetAllMeterialCat> getGetAllMeterialCat() {
        return getAllMeterialCat;
    }

    public void setGetAllMeterialCat(List<GetAllMeterialCat> getAllMeterialCat) {
        this.getAllMeterialCat = getAllMeterialCat;
    }

}