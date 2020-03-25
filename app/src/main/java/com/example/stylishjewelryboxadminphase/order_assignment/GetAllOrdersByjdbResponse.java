package com.example.stylishjewelryboxadminphase.order_assignment;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetAllOrdersByjdbResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("GetAllOrdersByjdb")
    @Expose
    private List<GetAllOrdersByjdb> getAllOrdersByjdb = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetAllOrdersByjdbResponse() {
    }

    /**
     *
     * @param getAllOrdersByjdb
     * @param status
     */
    public GetAllOrdersByjdbResponse(Boolean status, List<GetAllOrdersByjdb> getAllOrdersByjdb) {
        super();
        this.status = status;
        this.getAllOrdersByjdb = getAllOrdersByjdb;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<GetAllOrdersByjdb> getGetAllOrdersByjdb() {
        return getAllOrdersByjdb;
    }

    public void setGetAllOrdersByjdb(List<GetAllOrdersByjdb> getAllOrdersByjdb) {
        this.getAllOrdersByjdb = getAllOrdersByjdb;
    }

}