package com.example.stylishjewelryboxadminphase.get_allcateby_Ids;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetAllCatResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("GetAllCat")
    @Expose
    private List<GetAllCat> getAllCat = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetAllCatResponse() {
    }

    /**
     *
     * @param getAllCat
     * @param status
     */
    public GetAllCatResponse(Boolean status, List<GetAllCat> getAllCat) {
        super();
        this.status = status;
        this.getAllCat = getAllCat;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<GetAllCat> getGetAllCat() {
        return getAllCat;
    }

    public void setGetAllCat(List<GetAllCat> getAllCat) {
        this.getAllCat = getAllCat;
    }

}