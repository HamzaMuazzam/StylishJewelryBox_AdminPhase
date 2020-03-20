package com.example.stylishjewelryboxadminphase.seeDeliveryboys.jdb_details;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SeeJDBAssignedOrder {

    @SerializedName("Last7DaysOrders")
    @Expose
    private String last7DaysOrders;
    @SerializedName("Last30DaysOrders")
    @Expose
    private String last30DaysOrders;
    @SerializedName("currentdateorder")
    @Expose
    private String currentdateorder;

    /**
     * No args constructor for use in serialization
     *
     */
    public SeeJDBAssignedOrder() {
    }

    /**
     *
     * @param last7DaysOrders
     * @param last30DaysOrders
     * @param currentdateorder
     */
    public SeeJDBAssignedOrder(String last7DaysOrders, String last30DaysOrders, String currentdateorder) {
        super();
        this.last7DaysOrders = last7DaysOrders;
        this.last30DaysOrders = last30DaysOrders;
        this.currentdateorder = currentdateorder;
    }

    public String getLast7DaysOrders() {
        return last7DaysOrders;
    }

    public void setLast7DaysOrders(String last7DaysOrders) {
        this.last7DaysOrders = last7DaysOrders;
    }

    public String getLast30DaysOrders() {
        return last30DaysOrders;
    }

    public void setLast30DaysOrders(String last30DaysOrders) {
        this.last30DaysOrders = last30DaysOrders;
    }

    public String getCurrentdateorder() {
        return currentdateorder;
    }

    public void setCurrentdateorder(String currentdateorder) {
        this.currentdateorder = currentdateorder;
    }

}