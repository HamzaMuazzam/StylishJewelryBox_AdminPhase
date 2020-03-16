package com.example.stylishjewelryboxadminphase.calculations.percentages;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllForPercentage {

    @SerializedName("Total Orders")
    @Expose
    private String totalOrders;
    @SerializedName("Total Sale")
    @Expose
    private String totalSale;
    @SerializedName("Total Sale of Pending")
    @Expose
    private String totalSaleOfPending;
    @SerializedName("Total Sale of Delivered")
    @Expose
    private String totalSaleOfDelivered;
    @SerializedName("Total Sale of unassigned")
    @Expose
    private String totalSaleOfUnassigned;
    @SerializedName("Total Pending Orders")
    @Expose
    private String totalPendingOrders;
    @SerializedName("Total Delivered Orders")
    @Expose
    private String totalDeliveredOrders;
    @SerializedName("Total Unassigned")
    @Expose
    private String totalUnassigned;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetAllForPercentage() {
    }

    /**
     *
     * @param totalSaleOfDelivered
     * @param totalUnassigned
     * @param totalSaleOfPending
     * @param totalSaleOfUnassigned
     * @param totalOrders
     * @param totalPendingOrders
     * @param totalSale
     * @param totalDeliveredOrders
     */
    public GetAllForPercentage(String totalOrders, String totalSale, String totalSaleOfPending, String totalSaleOfDelivered, String totalSaleOfUnassigned, String totalPendingOrders, String totalDeliveredOrders, String totalUnassigned) {
        super();
        this.totalOrders = totalOrders;
        this.totalSale = totalSale;
        this.totalSaleOfPending = totalSaleOfPending;
        this.totalSaleOfDelivered = totalSaleOfDelivered;
        this.totalSaleOfUnassigned = totalSaleOfUnassigned;
        this.totalPendingOrders = totalPendingOrders;
        this.totalDeliveredOrders = totalDeliveredOrders;
        this.totalUnassigned = totalUnassigned;
    }

    public String getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(String totalOrders) {
        this.totalOrders = totalOrders;
    }

    public String getTotalSale() {
        return totalSale;
    }

    public void setTotalSale(String totalSale) {
        this.totalSale = totalSale;
    }

    public String getTotalSaleOfPending() {
        return totalSaleOfPending;
    }

    public void setTotalSaleOfPending(String totalSaleOfPending) {
        this.totalSaleOfPending = totalSaleOfPending;
    }

    public String getTotalSaleOfDelivered() {
        return totalSaleOfDelivered;
    }

    public void setTotalSaleOfDelivered(String totalSaleOfDelivered) {
        this.totalSaleOfDelivered = totalSaleOfDelivered;
    }

    public String getTotalSaleOfUnassigned() {
        return totalSaleOfUnassigned;
    }

    public void setTotalSaleOfUnassigned(String totalSaleOfUnassigned) {
        this.totalSaleOfUnassigned = totalSaleOfUnassigned;
    }

    public String getTotalPendingOrders() {
        return totalPendingOrders;
    }

    public void setTotalPendingOrders(String totalPendingOrders) {
        this.totalPendingOrders = totalPendingOrders;
    }

    public String getTotalDeliveredOrders() {
        return totalDeliveredOrders;
    }

    public void setTotalDeliveredOrders(String totalDeliveredOrders) {
        this.totalDeliveredOrders = totalDeliveredOrders;
    }

    public String getTotalUnassigned() {
        return totalUnassigned;
    }

    public void setTotalUnassigned(String totalUnassigned) {
        this.totalUnassigned = totalUnassigned;
    }

}