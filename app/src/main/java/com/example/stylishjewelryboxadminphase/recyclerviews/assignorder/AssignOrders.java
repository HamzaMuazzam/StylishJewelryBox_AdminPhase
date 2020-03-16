package com.example.stylishjewelryboxadminphase.recyclerviews.assignorder;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AssignOrders {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("AssignOrders")
    @Expose
    private List<String> assignOrders = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public AssignOrders() {
    }

    /**
     *
     * @param assignOrders
     * @param status
     */
    public AssignOrders(Boolean status, List<String> assignOrders) {
        super();
        this.status = status;
        this.assignOrders = assignOrders;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<String> getAssignOrders() {
        return assignOrders;
    }

    public void setAssignOrders(List<String> assignOrders) {
        this.assignOrders = assignOrders;
    }

}