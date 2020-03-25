package com.example.stylishjewelryboxadminphase.get_allcateby_Ids;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllCat {

    @SerializedName("jc_id")
    @Expose
    private String jcId;
    @SerializedName("jc_image")
    @Expose
    private String jcImage;
    @SerializedName("jc_name")
    @Expose
    private String jcName;
    @SerializedName("jc_displayprice")
    @Expose
    private String jcDisplayprice;
    @SerializedName("mc_id_fk")
    @Expose
    private String mcIdFk;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetAllCat() {
    }

    /**
     *
     * @param mcIdFk
     * @param jcDisplayprice
     * @param jcId
     * @param jcName
     * @param jcImage
     */
    public GetAllCat(String jcId, String jcImage, String jcName, String jcDisplayprice, String mcIdFk) {
        super();
        this.jcId = jcId;
        this.jcImage = jcImage;
        this.jcName = jcName;
        this.jcDisplayprice = jcDisplayprice;
        this.mcIdFk = mcIdFk;
    }

    public String getJcId() {
        return jcId;
    }

    public void setJcId(String jcId) {
        this.jcId = jcId;
    }

    public String getJcImage() {
        return jcImage;
    }

    public void setJcImage(String jcImage) {
        this.jcImage = jcImage;
    }

    public String getJcName() {
        return jcName;
    }

    public void setJcName(String jcName) {
        this.jcName = jcName;
    }

    public String getJcDisplayprice() {
        return jcDisplayprice;
    }

    public void setJcDisplayprice(String jcDisplayprice) {
        this.jcDisplayprice = jcDisplayprice;
    }

    public String getMcIdFk() {
        return mcIdFk;
    }

    public void setMcIdFk(String mcIdFk) {
        this.mcIdFk = mcIdFk;
    }

}