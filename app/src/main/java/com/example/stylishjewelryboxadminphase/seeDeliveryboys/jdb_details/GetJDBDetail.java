package com.example.stylishjewelryboxadminphase.seeDeliveryboys.jdb_details;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetJDBDetail {

    @SerializedName("jdb_id")
    @Expose
    private String jdbId;
    @SerializedName("jdb_name")
    @Expose
    private String jdbName;
    @SerializedName("jdb_email")
    @Expose
    private String jdbEmail;
    @SerializedName("jdb_phone")
    @Expose
    private String jdbPhone;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetJDBDetail() {
    }

    /**
     *
     * @param jdbName
     * @param jdbEmail
     * @param jdbId
     * @param jdbPhone
     */
    public GetJDBDetail(String jdbId, String jdbName, String jdbEmail, String jdbPhone) {
        super();
        this.jdbId = jdbId;
        this.jdbName = jdbName;
        this.jdbEmail = jdbEmail;
        this.jdbPhone = jdbPhone;
    }

    public String getJdbId() {
        return jdbId;
    }

    public void setJdbId(String jdbId) {
        this.jdbId = jdbId;
    }

    public String getJdbName() {
        return jdbName;
    }

    public void setJdbName(String jdbName) {
        this.jdbName = jdbName;
    }

    public String getJdbEmail() {
        return jdbEmail;
    }

    public void setJdbEmail(String jdbEmail) {
        this.jdbEmail = jdbEmail;
    }

    public String getJdbPhone() {
        return jdbPhone;
    }

    public void setJdbPhone(String jdbPhone) {
        this.jdbPhone = jdbPhone;
    }

}