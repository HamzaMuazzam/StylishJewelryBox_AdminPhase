package com.example.stylishjewelryboxadminphase.addcategoris.addcats;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InsertNewSubCategory {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("name")
    @Expose
    private String name;

    /**
     * No args constructor for use in serialization
     *
     */
    public InsertNewSubCategory() {
    }

    /**
     *
     * @param name
     * @param url
     * @param status
     */
    public InsertNewSubCategory(Boolean status, String url, String name) {
        super();
        this.status = status;
        this.url = url;
        this.name = name;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}