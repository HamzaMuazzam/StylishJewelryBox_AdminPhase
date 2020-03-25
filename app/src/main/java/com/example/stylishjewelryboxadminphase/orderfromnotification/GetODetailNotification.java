package com.example.stylishjewelryboxadminphase.orderfromnotification;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetODetailNotification {

    @SerializedName("jomd_order_id")
    @Expose
    private String jomdOrderId;
    @SerializedName("jomd_datetime")
    @Expose
    private String jomdDatetime;
    @SerializedName("jomd_location")
    @Expose
    private String jomdLocation;
    @SerializedName("jomd_longitude")
    @Expose
    private String jomdLongitude;
    @SerializedName("jomd_latitude")
    @Expose
    private String jomdLatitude;
    @SerializedName("jomd_instructions")
    @Expose
    private String jomdInstructions;
    @SerializedName("orderstatus")
    @Expose
    private String orderstatus;
    @SerializedName("jcd_id_fk")
    @Expose
    private String jcdIdFk;
    @SerializedName("total cost")
    @Expose
    private String totalCost;
    @SerializedName("jcd_name")
    @Expose
    private String jcdName;
    @SerializedName("jcd_phone")
    @Expose
    private String jcdPhone;
    @SerializedName("total items")
    @Expose
    private String totalItems;

    /**
     * No args constructor for use in serialization
     */
    public GetODetailNotification() {
    }

    /**
     * @param jcdPhone
     * @param totalItems
     * @param jomdLocation
     * @param jcdIdFk
     * @param orderstatus
     * @param jomdOrderId
     * @param jomdLatitude
     * @param jomdInstructions
     * @param jomdLongitude
     * @param jomdDatetime
     * @param jcdName
     * @param totalCost
     */
    public GetODetailNotification(String jomdOrderId, String jomdDatetime, String jomdLocation, String jomdLongitude, String jomdLatitude, String jomdInstructions, String orderstatus, String jcdIdFk, String totalCost, String jcdName, String jcdPhone, String totalItems) {
        super();
        this.jomdOrderId = jomdOrderId;
        this.jomdDatetime = jomdDatetime;
        this.jomdLocation = jomdLocation;
        this.jomdLongitude = jomdLongitude;
        this.jomdLatitude = jomdLatitude;
        this.jomdInstructions = jomdInstructions;
        this.orderstatus = orderstatus;
        this.jcdIdFk = jcdIdFk;
        this.totalCost = totalCost;
        this.jcdName = jcdName;
        this.jcdPhone = jcdPhone;
        this.totalItems = totalItems;
    }

    public String getJomdOrderId() {
        return jomdOrderId;
    }

    public void setJomdOrderId(String jomdOrderId) {
        this.jomdOrderId = jomdOrderId;
    }

    public String getJomdDatetime() {
        return jomdDatetime;
    }

    public void setJomdDatetime(String jomdDatetime) {
        this.jomdDatetime = jomdDatetime;
    }

    public String getJomdLocation() {
        return jomdLocation;
    }

    public void setJomdLocation(String jomdLocation) {
        this.jomdLocation = jomdLocation;
    }

    public String getJomdLongitude() {
        return jomdLongitude;
    }

    public void setJomdLongitude(String jomdLongitude) {
        this.jomdLongitude = jomdLongitude;
    }

    public String getJomdLatitude() {
        return jomdLatitude;
    }

    public void setJomdLatitude(String jomdLatitude) {
        this.jomdLatitude = jomdLatitude;
    }

    public String getJomdInstructions() {
        return jomdInstructions;
    }

    public void setJomdInstructions(String jomdInstructions) {
        this.jomdInstructions = jomdInstructions;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public String getJcdIdFk() {
        return jcdIdFk;
    }

    public void setJcdIdFk(String jcdIdFk) {
        this.jcdIdFk = jcdIdFk;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }

    public String getJcdName() {
        return jcdName;
    }

    public void setJcdName(String jcdName) {
        this.jcdName = jcdName;
    }

    public String getJcdPhone() {
        return jcdPhone;
    }

    public void setJcdPhone(String jcdPhone) {
        this.jcdPhone = jcdPhone;
    }

    public String getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(String totalItems) {
        this.totalItems = totalItems;
    }

}
