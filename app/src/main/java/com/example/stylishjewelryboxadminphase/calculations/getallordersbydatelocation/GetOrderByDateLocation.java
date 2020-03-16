package com.example.stylishjewelryboxadminphase.calculations.getallordersbydatelocation;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetOrderByDateLocation {

    @SerializedName("jomd_order_id")
    @Expose
    private String jomdOrderId;
    @SerializedName("orderstatus")
    @Expose
    private String orderstatus;

    @SerializedName("jomd_datetime")
    @Expose
    private String jomdDatetime;
    @SerializedName("jomd_uid")
    @Expose
    private String jomdUid;
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
    @SerializedName("delivered_time")
    @Expose
    private String deliveredTime;
    @SerializedName("delivered_date")
    @Expose
    private String deliveredDate;
    @SerializedName("delivered_by")
    @Expose
    private String deliveredBy;
    @SerializedName("jdb_id_fk")
    @Expose
    private String jdbIdFk;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetOrderByDateLocation() {
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    /**
     *
     * @param deliveredDate
     * @param jomdLocation
     * @param deliveredBy
     * @param jomdOrderId
     * @param jomdLatitude
     * @param jomdInstructions
     * @param jdbIdFk
     * @param jomdUid
     * @param jomdLongitude
     * @param jomdDatetime
     * @param deliveredTime
     */

    public GetOrderByDateLocation(String jomdOrderId, String jomdDatetime, String jomdUid, String jomdLocation, String jomdLongitude, String jomdLatitude,
     String jomdInstructions, String deliveredTime, String deliveredDate, String deliveredBy, String jdbIdFk, String orderstatus) {
        super();
        this.jomdOrderId = jomdOrderId;
        this.jomdDatetime = jomdDatetime;
        this.jomdUid = jomdUid;
        this.jomdLocation = jomdLocation;
        this.jomdLongitude = jomdLongitude;
        this.jomdLatitude = jomdLatitude;
        this.jomdInstructions = jomdInstructions;
        this.deliveredTime = deliveredTime;
        this.deliveredDate = deliveredDate;
        this.deliveredBy = deliveredBy;
        this.jdbIdFk = jdbIdFk;
        this.orderstatus = orderstatus;
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

    public String getJomdUid() {
        return jomdUid;
    }

    public void setJomdUid(String jomdUid) {
        this.jomdUid = jomdUid;
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

    public String getDeliveredTime() {
        return deliveredTime;
    }

    public void setDeliveredTime(String deliveredTime) {
        this.deliveredTime = deliveredTime;
    }

    public String getDeliveredDate() {
        return deliveredDate;
    }

    public void setDeliveredDate(String deliveredDate) {
        this.deliveredDate = deliveredDate;
    }

    public String getDeliveredBy() {
        return deliveredBy;
    }

    public void setDeliveredBy(String deliveredBy) {
        this.deliveredBy = deliveredBy;
    }

    public String getJdbIdFk() {
        return jdbIdFk;
    }

    public void setJdbIdFk(String jdbIdFk) {
        this.jdbIdFk = jdbIdFk;
    }

}