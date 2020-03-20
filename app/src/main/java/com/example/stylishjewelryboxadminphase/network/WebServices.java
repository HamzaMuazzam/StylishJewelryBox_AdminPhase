package com.example.stylishjewelryboxadminphase.network;


import com.example.stylishjewelryboxadminphase.addcategoris.addcats.GetAllMeterialCatResponse;
import com.example.stylishjewelryboxadminphase.addcategoris.addcats.GetAllSubCatsResponse;
import com.example.stylishjewelryboxadminphase.addcategoris.addcats.InsertNewCategory;
import com.example.stylishjewelryboxadminphase.addcategoris.addcats.InsertNewItemResponse;
import com.example.stylishjewelryboxadminphase.addcategoris.addcats.InsertNewSubCategory;
import com.example.stylishjewelryboxadminphase.addcategoris.addnewMaterial.AddNewMaterialResponse;
import com.example.stylishjewelryboxadminphase.calculations.getallordersbydatelocation.GetOrderByDateLocationResponse;
import com.example.stylishjewelryboxadminphase.calculations.percentages.GetAllForPercentageResponse;
import com.example.stylishjewelryboxadminphase.network.get_allcateby_Ids.GetAllCatResponse;
import com.example.stylishjewelryboxadminphase.recyclerviews.assignorder.AssignOrders;
import com.example.stylishjewelryboxadminphase.recyclerviews.getalldeliveryboys.GetAllDeliveryBoyResponse;
import com.example.stylishjewelryboxadminphase.recyclerviews.getunassignedorders.gettotalorders.UnassignedDateLocationResponse;
import com.example.stylishjewelryboxadminphase.seeDeliveryboys.jdb_details.GetJDBDetailResponse;
import com.example.stylishjewelryboxadminphase.seeDeliveryboys.jdb_details.SeeJDBAssignedOrderResponse;
import com.example.stylishjewelryboxadminphase.seeDeliveryboys.jdb_details.SeeJDBOrderbystatusDateResponse;
import com.example.stylishjewelryboxadminphase.updateCategory.GetAllSubofSubCatsResponse;
import com.example.stylishjewelryboxadminphase.updateCategory.UpdateCategoryResponse;
import com.example.stylishjewelryboxadminphase.updateCategory.UpdateMaterialResponse;
import com.example.stylishjewelryboxadminphase.updateCategory.UpdateResponse;

import org.json.JSONArray;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface WebServices {
    String BASEURL = "http://sourceinflow.com/jewelry/apis/";


    String GETALLDELIVERYBOYS = "getalldeliveryboys.php";
    String GETALLORDERSBYJDB = "getallordersofjdb.php";
    String GETORDERSFORASSIGNMENT = "get_order_for_Assignment_to_boy.php";
    String GET_ALL_MATERIAL_NAMES = "get_meterialcats.php";
    String GET_ALL_CATS_NAME_BY_ID = "get_all_categories.php";
    String GET_ALL_SUB_CATS_NAME_BY_ID = "get_sub_cats.php";
    String GETORDERBYLOCAIONDATE = "getAllOrderbyDate_location.php";
    String GETORDERBYDATELOCATIONSTATUS = "getByLocationDATEStatus.php";
    String GETUNASSIGNEDORDERS = "getunassignedOrders.php";

    String ADD_MATERIAL = "add_new_Material.php";
    String GET_JDB_DETAILS = "get_jdb_Details.php";


    String UPDATE_MATERIAL_NAME = "updatemeterial.php";
    String UPDATE_Category = "updateCategory.php";
    String UPDATE_childCategory = "updatesubcategory.php";
    String UPDATE_ITEM = "updateSOS.php";

    String INSERNEWCATEGORY = "insertNewCategory.php";
    String INSERNEW_SUB_CATEGORY = "inser_sub_cats.php";
    String INSERT_NEW_ITEM = "insert_into_sos_cats.php";
    String GETALLSOSBYID = "get_all_sos_cats.php";
    String ASSIGNORDERS = "assign_order.php";
    String GETALLORDERPERCENTAGES = "getAllOrderForPercentage.php";
    String SEE_JDB_ORDERBYSTATUS_DATE = "seedlieveryboys/getpendingbydate.php";
    String SEE_JDB_ASSIGNED_ORDERBYSTATUS_DATE = "seedlieveryboys/get_assignedorderbydate.php";


    Retrofit RETROFIT = new Retrofit.Builder().baseUrl(BASEURL).addConverterFactory(GsonConverterFactory.create()).build();


    @FormUrlEncoded
    @POST(SEE_JDB_ASSIGNED_ORDERBYSTATUS_DATE)
    Call<SeeJDBAssignedOrderResponse> seeJDB_Assigned_order_By_DateStatus(@Field("id") String id,
                                                                 @Field("currentdate") String currentdate,
                                                                 @Field("last30days") String last30days,
                                                                 @Field("last7days") String last7days);

    @FormUrlEncoded
    @POST(SEE_JDB_ORDERBYSTATUS_DATE)
    Call<SeeJDBOrderbystatusDateResponse> seeJDB_order_By_DateStatus(@Field("id") String id,
                                                                     @Field("status") String status,
                                                                     @Field("currentdate") String currentdate,
                                                                     @Field("last30days") String last30days,
                                                                     @Field("last7days") String last7days);

    @FormUrlEncoded
    @POST(GETALLDELIVERYBOYS)
    Call<GetAllDeliveryBoyResponse> getAll_JDBs(@Field("status") String status);

    @FormUrlEncoded
    @POST(GET_JDB_DETAILS)
    Call<GetJDBDetailResponse> get_JDB_Details(@Field("jdbid") String jdbid);


    @FormUrlEncoded
    @POST(GETALLORDERSBYJDB)
    Call<GetAllOrdersByjdbResponse> getallorders_by_jdb(@Field("id") String id,
                                                        @Field("date") String date);


    @FormUrlEncoded
    @POST(GET_ALL_CATS_NAME_BY_ID)
    Call<GetAllCatResponse> getAllCatsByID(@Field("mc_id") String mc_id);


    @FormUrlEncoded
    @POST(UPDATE_MATERIAL_NAME)
    Call<UpdateMaterialResponse> updateMaterialById(@Field("price") String price,
                                                    @Field("id") String id,
                                                    @Field("name") String name);


    @FormUrlEncoded
    @POST(GET_ALL_SUB_CATS_NAME_BY_ID)
    Call<GetAllSubCatsResponse> getAllSubCatBYID(@Field("id") String id);

    @FormUrlEncoded
    @POST(GETORDERSFORASSIGNMENT)
    Call<GetOrderForAssignmentResponse> getOrderForAssignments(
            @Field("jdb_id") String jdb_id,
            @Field("location") String location,
            @Field("date") String date
    );

    @FormUrlEncoded
    @POST(GETALLSOSBYID)
    Call<GetAllSubofSubCatsResponse> getAllSOS(
            @Field("sc_id") String sc_id);

    @FormUrlEncoded
    @POST(ADD_MATERIAL)
    Call<AddNewMaterialResponse> addNewMaterial(@Field("name") String name, @Field("price") String price);


    @FormUrlEncoded
    @POST(INSERNEWCATEGORY)
    Call<InsertNewCategory> insertNewCategory(@Field("name") String name,
                                              @Field("price") String price
            , @Field("image") String image
            , @Field("id_fk") String id_fk);

    @FormUrlEncoded
    @POST(INSERNEW_SUB_CATEGORY)
    Call<InsertNewSubCategory> insertNewSubCategory(@Field("name") String name,
                                                    @Field("price") String price
            , @Field("image") String image
            , @Field("id_fk") String id_fk);


    @FormUrlEncoded
    @POST(INSERT_NEW_ITEM)
    Call<InsertNewItemResponse> insertNewItem(@Field("name") String name,
                                              @Field("price") String price
            , @Field("image") String image
            , @Field("desc") String desc
            , @Field("id_fk") String id_fk);

    @FormUrlEncoded
    @POST(UPDATE_Category)
    Call<UpdateCategoryResponse> updateCats(@Field("name") String name,
                                            @Field("price") String price
            , @Field("image") String image
            , @Field("id") String id_fk);

    @FormUrlEncoded
    @POST(UPDATE_childCategory)
    Call<UpdateResponse> updateChildCats(@Field("name") String name,
                                         @Field("price") String price
            , @Field("image") String image
            , @Field("id") String id_fk);


    @FormUrlEncoded
    @POST(UPDATE_ITEM)
    Call<UpdateResponse> updateSingleItem(@Field("name") String name,
                                          @Field("price") String price,
                                          @Field("desc") String desc,
                                          @Field("image") String image, @Field("id") String id_fk);


    @FormUrlEncoded
    @POST(GETORDERBYDATELOCATIONSTATUS)
    Call<GetOrderByDateLocationResponse> getOrdersByLocationDateStatus(@Field("location") String location,
                                                                       @Field("date") String date,
                                                                       @Field("orderstatus") String orderStatus);

    @FormUrlEncoded
    @POST(GETUNASSIGNEDORDERS)
    Call<UnassignedDateLocationResponse> getUnassignedOrders(@Field("location") String location,
                                                             @Field("date") String date,
                                                             @Field("assignment") String assignment
    );

    //    @FormUrlEncoded
    @POST(ASSIGNORDERS)
    Call<AssignOrders> assign_Orders(@Query("ids") JSONArray status);

    @FormUrlEncoded
    @POST(GETALLORDERPERCENTAGES)
    Call<GetAllForPercentageResponse> getAllOrderToMakePercentage(@Field("location") String location, @Field("date") String date);


    @GET(GET_ALL_MATERIAL_NAMES)
    Call<GetAllMeterialCatResponse> getmterialname();


}
