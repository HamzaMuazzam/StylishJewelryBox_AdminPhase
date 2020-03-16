package com.example.stylishjewelryboxadminphase.addcategoris.addcats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllMeterialCat {

    @SerializedName("mc_id")
    @Expose
    private String mcId;
    @SerializedName("mc_name")
    @Expose
    private String mcName;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetAllMeterialCat() {
    }

    /**
     *
     * @param mcId
     * @param mcName
     */
    public GetAllMeterialCat(String mcId, String mcName) {
        super();
        this.mcId = mcId;
        this.mcName = mcName;
    }

    public String getMcId() {
        return mcId;
    }

    public void setMcId(String mcId) {
        this.mcId = mcId;
    }

    public String getMcName() {
        return mcName;
    }

    public void setMcName(String mcName) {
        this.mcName = mcName;
    }

}