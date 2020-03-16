package com.example.stylishjewelryboxadminphase.updateCategory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllSubofSubCat {

    @SerializedName("jsosc_id")
    @Expose
    private String jsoscId;
    @SerializedName("jsosc_name")
    @Expose
    private String jsoscName;
    @SerializedName("jsosc_desc")
    @Expose
    private String jsoscDesc;
    @SerializedName("jsosc_price")
    @Expose
    private String jsoscPrice;
    @SerializedName("jsc_id_fk")
    @Expose
    private String jscIdFk;
    @SerializedName("jsosc_image")
    @Expose
    private String jsoscImage;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetAllSubofSubCat() {
    }

    /**
     *
     * @param jsoscPrice
     * @param jsoscId
     * @param jsoscDesc
     * @param jsoscName
     * @param jsoscImage
     * @param jscIdFk
     */
    public GetAllSubofSubCat(String jsoscId, String jsoscName, String jsoscDesc, String jsoscPrice, String jscIdFk, String jsoscImage) {
        super();
        this.jsoscId = jsoscId;
        this.jsoscName = jsoscName;
        this.jsoscDesc = jsoscDesc;
        this.jsoscPrice = jsoscPrice;
        this.jscIdFk = jscIdFk;
        this.jsoscImage = jsoscImage;
    }

    public String getJsoscId() {
        return jsoscId;
    }

    public void setJsoscId(String jsoscId) {
        this.jsoscId = jsoscId;
    }

    public String getJsoscName() {
        return jsoscName;
    }

    public void setJsoscName(String jsoscName) {
        this.jsoscName = jsoscName;
    }

    public String getJsoscDesc() {
        return jsoscDesc;
    }

    public void setJsoscDesc(String jsoscDesc) {
        this.jsoscDesc = jsoscDesc;
    }

    public String getJsoscPrice() {
        return jsoscPrice;
    }

    public void setJsoscPrice(String jsoscPrice) {
        this.jsoscPrice = jsoscPrice;
    }

    public String getJscIdFk() {
        return jscIdFk;
    }

    public void setJscIdFk(String jscIdFk) {
        this.jscIdFk = jscIdFk;
    }

    public String getJsoscImage() {
        return jsoscImage;
    }

    public void setJsoscImage(String jsoscImage) {
        this.jsoscImage = jsoscImage;
    }

}