package com.example.stylishjewelryboxadminphase.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.stylishjewelryboxadminphase.R;
import com.example.stylishjewelryboxadminphase.addcategoris.addcats.GetAllMeterialCat;
import com.example.stylishjewelryboxadminphase.addcategoris.addcats.GetAllMeterialCatResponse;
import com.example.stylishjewelryboxadminphase.addcategoris.addcats.GetAllSubCat;
import com.example.stylishjewelryboxadminphase.addcategoris.addcats.GetAllSubCatsResponse;
import com.example.stylishjewelryboxadminphase.network.WebServices;
import com.example.stylishjewelryboxadminphase.network.get_allcateby_Ids.GetAllCat;
import com.example.stylishjewelryboxadminphase.network.get_allcateby_Ids.GetAllCatResponse;
import com.example.stylishjewelryboxadminphase.updateCategory.GetAllSubofSubCat;
import com.example.stylishjewelryboxadminphase.updateCategory.GetAllSubofSubCatsResponse;
import com.example.stylishjewelryboxadminphase.updateCategory.UpdateResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateItemActivity extends AppCompatActivity {
    public static final String TAG = "MYTAG";
    Spinner spv_select_material, spv_select_category, spv_select_item, spv_select_childtoUpdatecategory;
    CardView card_selec_category, card_select_childtoUpdatecategory, card_seelctitems, card_updateItem;
    WebServices webServices;
    Button btn_updatechildCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_item);
        initviews();
        getMaterialNames();
    }

    private void initviews() {
        webServices = WebServices.RETROFIT.create(WebServices.class);
        spv_select_material = findViewById(R.id.spv_select_material);
        spv_select_category = findViewById(R.id.spv_select_category);
        card_selec_category = findViewById(R.id.card_selec_category);
        card_select_childtoUpdatecategory = findViewById(R.id.card_select_childtoUpdatecategory);
        spv_select_childtoUpdatecategory = findViewById(R.id.spv_select_childtoUpdatecategory);


        btn_updatechildCategory = findViewById(R.id.btn_updatechildCategory);
        card_seelctitems = findViewById(R.id.card_seelctitems);
        spv_select_item = findViewById(R.id.spv_select_item);
        card_updateItem = findViewById(R.id.card_updateItem);


//        btn_updatechildCategory = findViewById(R.id.btn_updatechildCategory);
    }

    private void getMaterialNames() {
        webServices.getmterialname().enqueue(new Callback<GetAllMeterialCatResponse>() {
            @Override
            public void onResponse(Call<GetAllMeterialCatResponse> call, Response<GetAllMeterialCatResponse> response) {

                if (response.isSuccessful() && response.body() != null) {

                    if (response.body().getStatus()) {
                        List<GetAllMeterialCat> meterialCatList = response.body().getGetAllMeterialCat();
                        int listsize = meterialCatList.size();
                        String[] array1 = new String[1];
                        array1[0] = "Select Material";

                        String[] array2 = new String[listsize];

                        String[] array3 = new String[1];
                        array3[0] = "Select Material";

                        String[] array4 = new String[listsize];


                        for (int x = 0; x < meterialCatList.size(); x++) {
                            array2[x] = meterialCatList.get(x).getMcName();
                            array4[x] = meterialCatList.get(x).getMcId();

                        }

                        int length1 = array1.length; //slect
                        int length2 = array2.length; //name

                        int length3 = array3.length; //slect
                        int length4 = array4.length; //id

                        String[] new_array = new String[length2 + length1];
                        System.arraycopy(array1, 0, new_array, 0, length1);
                        System.arraycopy(array2, 0, new_array, length1, length2);


                        String[] new_arrayofIDS = new String[length3 + length4];
                        System.arraycopy(array3, 0, new_arrayofIDS, 0, length3);
                        System.arraycopy(array4, 0, new_arrayofIDS, length3, length4);


                        selectMaterial(new_array, new_arrayofIDS);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetAllMeterialCatResponse> call, Throwable t) {
                Toast.makeText(UpdateItemActivity.this, "OnFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void selectMaterial(String[] array, String[] arrayofis) {
//        for (int x = 0; x < arrayofis.length; x++) {
//
//            Log.d("MYTAG", "selectMaterial: " + arrayofis[x]);
//        }
        ArrayAdapter<String> arrayAdapter_slct_material = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array);
        arrayAdapter_slct_material.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spv_select_material.setAdapter(arrayAdapter_slct_material);

        spv_select_material.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent != null) {
                    String item = parent.getItemAtPosition(position).toString();
                    String ids = arrayofis[position];
                    ((TextView) parent.getChildAt(0)).setText(item);
                    ((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#031A9E"));
                    if (!item.equalsIgnoreCase("Select Material")) {
                        card_updateItem.setVisibility(View.GONE);
                        card_seelctitems.setVisibility(View.GONE);
                        card_select_childtoUpdatecategory.setVisibility(View.GONE);

                        webServices.getAllCatsByID(ids).enqueue(new Callback<GetAllCatResponse>() {
                            @Override
                            public void onResponse(Call<GetAllCatResponse> call, Response<GetAllCatResponse> response) {
                                if (response.isSuccessful() && response.body() != null) {
                                    if (response.body().getStatus()) {
                                        card_selec_category.setVisibility(View.VISIBLE);


                                        List<GetAllCat> catList = response.body().getGetAllCat();


                                        int listsize = catList.size();

                                        String[] array1 = new String[1];
                                        array1[0] = "Select category";

                                        String[] array2 = new String[listsize];

                                        String[] array3 = new String[1];
                                        array3[0] = "Select category";

                                        String[] array4 = new String[listsize];


                                        for (int x = 0; x < catList.size(); x++) {
                                            array2[x] = catList.get(x).getJcName();
                                            array4[x] = catList.get(x).getJcId();

                                        }

                                        int length1 = array1.length; //slect
                                        int length2 = array2.length; //name

                                        int length3 = array3.length; //slect
                                        int length4 = array4.length; //id

                                        String[] new_array = new String[length2 + length1];
                                        System.arraycopy(array1, 0, new_array, 0, length1);
                                        System.arraycopy(array2, 0, new_array, length1, length2);


                                        String[] new_arrayofIDS = new String[length3 + length4];
                                        System.arraycopy(array3, 0, new_arrayofIDS, 0, length3);
                                        System.arraycopy(array4, 0, new_arrayofIDS, length3, length4);


                                        categoryNameIDs(new_array, new_arrayofIDS);

                                    } else {
                                        Log.d("MYTAG", " Status : false ");
                                        card_updateItem.setVisibility(View.GONE);


                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<GetAllCatResponse> call, Throwable t) {
                                Log.d("MYTAG", "OnFailure: " + t.getMessage());
                                card_updateItem.setVisibility(View.GONE);


                            }
                        });


                    } else {
                        card_selec_category.setVisibility(View.GONE);
                        card_updateItem.setVisibility(View.GONE);

                        card_select_childtoUpdatecategory.setVisibility(View.GONE);
                        card_seelctitems.setVisibility(View.GONE);


                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                card_updateItem.setVisibility(View.GONE);

            }
        });

    }

    private void categoryNameIDs(String[] new_array, String[] new_arrayofIDS) {


        card_selec_category.setVisibility(View.VISIBLE);
        ArrayAdapter<String> arrayAdapter_slct_Category = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, new_array);
        arrayAdapter_slct_Category.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spv_select_category.setAdapter(arrayAdapter_slct_Category);

        spv_select_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent != null) {
                    String item = parent.getItemAtPosition(position).toString();
                    String ids = new_arrayofIDS[position];
                    ((TextView) parent.getChildAt(0)).setText(item);
                    ((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#031A9E"));
                    if (!item.equalsIgnoreCase("Select category")) {
                        card_updateItem.setVisibility(View.GONE);

                        card_seelctitems.setVisibility(View.GONE);

                        card_select_childtoUpdatecategory.setVisibility(View.VISIBLE);


                        Log.d(TAG, "onItemSelected: " + ids);
                        webServices.getAllSubCatBYID(ids).enqueue(new Callback<GetAllSubCatsResponse>() {
                            @Override
                            public void onResponse(Call<GetAllSubCatsResponse> call, Response<GetAllSubCatsResponse> response) {
                                if (response.isSuccessful() && response.body() != null) {
                                    if (response.body().getStatus()) {
                                        List<GetAllSubCat> list = response.body().getGetAllSubCats();

                                        int listsize = list.size();
                                        String[] array1 = new String[1];
                                        array1[0] = "Select Child Category";

                                        String[] array2 = new String[listsize];

                                        String[] array3 = new String[1];
                                        array3[0] = "Select Child Category";

                                        String[] array4 = new String[listsize];


                                        for (int x = 0; x < list.size(); x++) {
                                            array2[x] = list.get(x).getJscName();
                                            array4[x] = list.get(x).getJscId();

                                        }

                                        int length1 = array1.length; //slect
                                        int length2 = array2.length; //name

                                        int length3 = array3.length; //slect
                                        int length4 = array4.length; //id

                                        String[] new_array = new String[length2 + length1];
                                        System.arraycopy(array1, 0, new_array, 0, length1);
                                        System.arraycopy(array2, 0, new_array, length1, length2);


                                        String[] new_arrayofIDS = new String[length3 + length4];
                                        System.arraycopy(array3, 0, new_arrayofIDS, 0, length3);
                                        System.arraycopy(array4, 0, new_arrayofIDS, length3, length4);

                                        childCateforySpinner(new_array, new_arrayofIDS);


                                    } else {
                                        card_seelctitems.setVisibility(View.GONE);
                                        card_updateItem.setVisibility(View.GONE);

                                        Toast.makeText(UpdateItemActivity.this, "Nothing Found: " + response.body().getStatus(), Toast.LENGTH_SHORT).show();

                                    }
                                }

                            }

                            @Override
                            public void onFailure(Call<GetAllSubCatsResponse> call, Throwable t) {
                                Toast.makeText(UpdateItemActivity.this, "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });


                    } else {
                        card_select_childtoUpdatecategory.setVisibility(View.GONE);
                        card_seelctitems.setVisibility(View.GONE);
                        card_updateItem.setVisibility(View.GONE);


                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });
    }

    private void childCateforySpinner(String[] arrayname, String[] arrayofIDS) {
        ArrayAdapter<String> arrayAdapter_slct_material = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayname);
        arrayAdapter_slct_material.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spv_select_childtoUpdatecategory.setAdapter(arrayAdapter_slct_material);

        spv_select_childtoUpdatecategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent != null) {
                    String item = parent.getItemAtPosition(position).toString();
                    String ids = arrayofIDS[position];
                    ((TextView) parent.getChildAt(0)).setText(item);
                    ((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#031A9E"));
                    if (!item.equalsIgnoreCase("Select Child Category")) {
                        card_updateItem.setVisibility(View.GONE);

                        Log.d(TAG, "onItemSelected: " + ids);
                        webServices.getAllSOS(ids).enqueue(new Callback<GetAllSubofSubCatsResponse>() {
                            @Override
                            public void onResponse(Call<GetAllSubofSubCatsResponse> call, Response<GetAllSubofSubCatsResponse> response) {
                                if (response.isSuccessful() && response.body() != null) {
                                    if (response.body().getStatus()) {
                                        List<GetAllSubofSubCat> list = response.body().getGetAllSubofSubCats();
                                        card_seelctitems.setVisibility(View.VISIBLE);
                                        String[] array1 = new String[1];
                                        array1[0] = "Select Item";

                                        String[] array2 = new String[list.size()];

                                        String[] array3 = new String[1];
                                        array3[0] = "Select Item";

                                        String[] array4 = new String[list.size()];


                                        for (int x = 0; x < list.size(); x++) {
                                            array2[x] = list.get(x).getJsoscName();
                                            array4[x] = list.get(x).getJsoscId();

                                        }

                                        int length1 = array1.length; //slect
                                        int length2 = array2.length; //name

                                        int length3 = array3.length; //slect
                                        int length4 = array4.length; //id

                                        String[] new_array = new String[length2 + length1];
                                        System.arraycopy(array1, 0, new_array, 0, length1);
                                        System.arraycopy(array2, 0, new_array, length1, length2);


                                        String[] new_arrayofIDS = new String[length3 + length4];
                                        System.arraycopy(array3, 0, new_arrayofIDS, 0, length3);
                                        System.arraycopy(array4, 0, new_arrayofIDS, length3, length4);
                                        uodateSingleItem(new_array, new_arrayofIDS);
                                        card_updateItem.setVisibility(View.GONE);


                                    } else {

                                        card_seelctitems.setVisibility(View.GONE);
                                        card_updateItem.setVisibility(View.GONE);

                                        Toast.makeText(UpdateItemActivity.this, "Nothing Found: " + response.body().getStatus(), Toast.LENGTH_SHORT).show();
                                    }


                                }
                            }

                            @Override
                            public void onFailure(Call<GetAllSubofSubCatsResponse> call, Throwable t) {
                                Toast.makeText(UpdateItemActivity.this, "onFailure : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                                card_seelctitems.setVisibility(View.GONE);

                            }
                        });

                    } else {
                        card_updateItem.setVisibility(View.GONE);

                        card_seelctitems.setVisibility(View.GONE);

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });


    }

    private void uodateSingleItem(String[] arrayname, String[] arrayIDS) {
        for (int x = 0; x < arrayname.length; x++) {
            Log.d(TAG, "uodateSingleItem: " + arrayname[x]);
        }
        ArrayAdapter<String> arrayAdapter_slct_material = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayname);
        arrayAdapter_slct_material.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spv_select_item.setAdapter(arrayAdapter_slct_material);

        spv_select_item.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent != null) {
                    String item = parent.getItemAtPosition(position).toString();
                    String ids = arrayIDS[position];
                    ((TextView) parent.getChildAt(0)).setText(item);
                    ((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#031A9E"));
                    if (!item.equalsIgnoreCase("Select Item")) {
                        card_updateItem.setVisibility(View.VISIBLE);
                        Log.d(TAG, "onItemSelected: " + ids);

                        webServices.updateSingleItem("Name", "9898989", "desc", "image link", ids).enqueue(new Callback<UpdateResponse>() {
                            @Override
                            public void onResponse(Call<UpdateResponse> call, Response<UpdateResponse> response) {
                                if (response.isSuccessful() && response.body() != null) {
                                    if (response.body().getStatus()) {
                                        Toast.makeText(UpdateItemActivity.this, "Updated", Toast.LENGTH_LONG).show();

                                    }


                                }
                            }

                            @Override
                            public void onFailure(Call<UpdateResponse> call, Throwable t) {
                                Toast.makeText(UpdateItemActivity.this, "onFailure: " + t.getMessage(), Toast.LENGTH_LONG).show();

                            }
                        });


                    } else {

                        card_updateItem.setVisibility(View.GONE);

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });
    }


}
