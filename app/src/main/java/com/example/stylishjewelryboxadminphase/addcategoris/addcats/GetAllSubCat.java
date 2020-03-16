package com.example.stylishjewelryboxadminphase.addcategoris.addcats;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllSubCat {

    @SerializedName("jsc_id")
    @Expose
    private String jscId;
    @SerializedName("jsc_name")
    @Expose
    private String jscName;
    @SerializedName("jsc_price")
    @Expose
    private String jscPrice;
    @SerializedName("jsc_image")
    @Expose
    private String jscImage;
    @SerializedName("jc_id_fk")
    @Expose
    private String jcIdFk;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetAllSubCat() {
    }

    /**
     *
     * @param jscImage
     * @param jscPrice
     * @param jscName
     * @param jscId
     * @param jcIdFk
     */
    public GetAllSubCat(String jscId, String jscName, String jscPrice, String jscImage, String jcIdFk) {
        super();
        this.jscId = jscId;
        this.jscName = jscName;
        this.jscPrice = jscPrice;
        this.jscImage = jscImage;
        this.jcIdFk = jcIdFk;
    }

    public String getJscId() {
        return jscId;
    }

    public void setJscId(String jscId) {
        this.jscId = jscId;
    }

    public String getJscName() {
        return jscName;
    }

    public void setJscName(String jscName) {
        this.jscName = jscName;
    }

    public String getJscPrice() {
        return jscPrice;
    }

    public void setJscPrice(String jscPrice) {
        this.jscPrice = jscPrice;
    }

    public String getJscImage() {
        return jscImage;
    }

    public void setJscImage(String jscImage) {
        this.jscImage = jscImage;
    }

    public String getJcIdFk() {
        return jcIdFk;
    }

    public void setJcIdFk(String jcIdFk) {
        this.jcIdFk = jcIdFk;
    }

}