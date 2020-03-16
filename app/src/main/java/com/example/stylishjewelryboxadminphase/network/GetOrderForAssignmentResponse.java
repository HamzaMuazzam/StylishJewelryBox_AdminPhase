package com.example.stylishjewelryboxadminphase.network;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetOrderForAssignmentResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("GetOrderForAssignment")
    @Expose
    private List<GetOrderForAssignment> getOrderForAssignment = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetOrderForAssignmentResponse() {
    }

    /**
     *
     * @param status
     * @param getOrderForAssignment
     */
    public GetOrderForAssignmentResponse(Boolean status, List<GetOrderForAssignment> getOrderForAssignment) {
        super();
        this.status = status;
        this.getOrderForAssignment = getOrderForAssignment;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<GetOrderForAssignment> getGetOrderForAssignment() {
        return getOrderForAssignment;
    }

    public void setGetOrderForAssignment(List<GetOrderForAssignment> getOrderForAssignment) {
        this.getOrderForAssignment = getOrderForAssignment;
    }

}