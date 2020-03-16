package com.example.stylishjewelryboxadminphase.recyclerviews.getunassignedorders.gettotalorders;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UnassignedDateLocationResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("UnassignedDateLocation")
    @Expose
    private List<UnassignedDateLocation> unassignedDateLocation = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public UnassignedDateLocationResponse() {
    }

    /**
     *
     * @param unassignedDateLocation
     * @param status
     */
    public UnassignedDateLocationResponse(Boolean status, List<UnassignedDateLocation> unassignedDateLocation) {
        super();
        this.status = status;
        this.unassignedDateLocation = unassignedDateLocation;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<UnassignedDateLocation> getUnassignedDateLocation() {
        return unassignedDateLocation;
    }

    public void setUnassignedDateLocation(List<UnassignedDateLocation> unassignedDateLocation) {
        this.unassignedDateLocation = unassignedDateLocation;
    }

}