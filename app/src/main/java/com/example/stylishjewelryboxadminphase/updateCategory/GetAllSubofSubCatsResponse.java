package com.example.stylishjewelryboxadminphase.updateCategory;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllSubofSubCatsResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("GetAllSubofSubCats")
    @Expose
    private List<GetAllSubofSubCat> getAllSubofSubCats = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetAllSubofSubCatsResponse() {
    }

    /**
     *
     * @param getAllSubofSubCats
     * @param status
     */
    public GetAllSubofSubCatsResponse(Boolean status, List<GetAllSubofSubCat> getAllSubofSubCats) {
        super();
        this.status = status;
        this.getAllSubofSubCats = getAllSubofSubCats;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<GetAllSubofSubCat> getGetAllSubofSubCats() {
        return getAllSubofSubCats;
    }

    public void setGetAllSubofSubCats(List<GetAllSubofSubCat> getAllSubofSubCats) {
        this.getAllSubofSubCats = getAllSubofSubCats;
    }

}