package com.example.stylishjewelryboxadminphase.addcategoris.addcats;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllSubCatsResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("GetAllSubCats")
    @Expose
    private List<GetAllSubCat> getAllSubCats = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetAllSubCatsResponse() {
    }

    /**
     *
     * @param getAllSubCats
     * @param status
     */
    public GetAllSubCatsResponse(Boolean status, List<GetAllSubCat> getAllSubCats) {
        super();
        this.status = status;
        this.getAllSubCats = getAllSubCats;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<GetAllSubCat> getGetAllSubCats() {
        return getAllSubCats;
    }

    public void setGetAllSubCats(List<GetAllSubCat> getAllSubCats) {
        this.getAllSubCats = getAllSubCats;
    }

}