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
import com.example.stylishjewelryboxadminphase.addcategoris.addcats.InsertNewItemResponse;
import com.example.stylishjewelryboxadminphase.network.WebServices;
import com.example.stylishjewelryboxadminphase.network.get_allcateby_Ids.GetAllCat;
import com.example.stylishjewelryboxadminphase.network.get_allcateby_Ids.GetAllCatResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNewItemActivity extends AppCompatActivity {
    public static final String TAG = "MYTAG";
    Spinner spv_select_material, spv_select_category, spv_select__subcategory;
    CardView card_selec_category, card_select_sub_category, card_details_form_newitem;
    WebServices webServices;
    Button btn_insertNewItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_item);
        initviews();
        getMaterialNames();
    }

    private void initviews() {
        webServices = WebServices.RETROFIT.create(WebServices.class);

        spv_select_material = findViewById(R.id.spv_select_material);
        spv_select_category = findViewById(R.id.spv_select_category);
        spv_select__subcategory = findViewById(R.id.spv_select__subcategory);
        btn_insertNewItem = findViewById(R.id.btn_insertNewItem);


        card_selec_category = findViewById(R.id.card_selec_category);
        card_select_sub_category = findViewById(R.id.card_select_sub_category);
        card_details_form_newitem = findViewById(R.id.card_details_form_newitem);

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
                Toast.makeText(AddNewItemActivity.this, "OnFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();

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
                        card_details_form_newitem.setVisibility(View.GONE);
                        webServices.getAllCatsByID(ids).enqueue(new Callback<GetAllCatResponse>() {
                            @Override
                            public void onResponse(Call<GetAllCatResponse> call, Response<GetAllCatResponse> response) {
                                if (response.isSuccessful() && response.body() != null) {
                                    if (response.body().getStatus()) {


                                        List<GetAllCat> catList = response.body().getGetAllCat();

                                        card_selec_category.setVisibility(View.VISIBLE);
                                        card_select_sub_category.setVisibility(View.GONE);

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


                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<GetAllCatResponse> call, Throwable t) {
                                Log.d("MYTAG", "OnFailure: " + t.getMessage());


                            }
                        });


                    } else {
                        card_selec_category.setVisibility(View.GONE);
                        card_select_sub_category.setVisibility(View.GONE);
                        card_details_form_newitem.setVisibility(View.GONE);


                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                card_select_sub_category.setVisibility(View.GONE);
                card_details_form_newitem.setVisibility(View.GONE);

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
                        card_details_form_newitem.setVisibility(View.GONE);

                        webServices.getAllSubCatBYID(ids).enqueue(new Callback<GetAllSubCatsResponse>() {
                            @Override
                            public void onResponse(Call<GetAllSubCatsResponse> call, Response<GetAllSubCatsResponse> response) {
                                if (response.isSuccessful() && response.body() != null) {
                                    if (response.body().getStatus()) {
                                        card_select_sub_category.setVisibility(View.VISIBLE);
                                        List<GetAllSubCat> subCatList = response.body().getGetAllSubCats();


                                        int listsize = subCatList.size();

                                        String[] array1 = new String[1];
                                        array1[0] = "Select child category";

                                        String[] array2 = new String[listsize];

                                        String[] array3 = new String[1];
                                        array3[0] = "Select child category";

                                        String[] array4 = new String[listsize];


                                        for (int x = 0; x < subCatList.size(); x++) {
                                            array2[x] = subCatList.get(x).getJscName();
                                            array4[x] = subCatList.get(x).getJscId();

                                        }

                                        int length1 = array1.length; //slect
                                        int length2 = array2.length; //name

                                        int length3 = array3.length; //slect
                                        int length4 = array4.length; //id

                                        String[] new_array_ofname = new String[length2 + length1];
                                        System.arraycopy(array1, 0, new_array_ofname, 0, length1);
                                        System.arraycopy(array2, 0, new_array_ofname, length1, length2);


                                        String[] new_arrayof_IDS = new String[length3 + length4];
                                        System.arraycopy(array3, 0, new_arrayof_IDS, 0, length3);
                                        System.arraycopy(array4, 0, new_arrayof_IDS, length3, length4);
                                        subOfSubSpinner(new_array_ofname, new_arrayof_IDS);


                                    } else {
                                        card_select_sub_category.setVisibility(View.GONE);

                                        Toast.makeText(AddNewItemActivity.this, "Status: " + response.body().getStatus(),
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<GetAllSubCatsResponse> call, Throwable t) {
                                card_select_sub_category.setVisibility(View.GONE);
                                Toast.makeText(AddNewItemActivity.this, "onFailure: " + t.getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        });


                    } else {

                        card_select_sub_category.setVisibility(View.GONE);
                        card_details_form_newitem.setVisibility(View.GONE);

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                card_details_form_newitem.setVisibility(View.GONE);

            }
        });
    }

    private void subOfSubSpinner(String[] names, String[] ids) {


        ArrayAdapter<String> arrayAdapter_slct_material = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, names);
        arrayAdapter_slct_material.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spv_select__subcategory.setAdapter(arrayAdapter_slct_material);

        spv_select__subcategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent != null) {
                    String item = parent.getItemAtPosition(position).toString();
                    String str_id = ids[position];
                    ((TextView) parent.getChildAt(0)).setText(item);
                    ((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#031A9E"));
                    if (!item.equalsIgnoreCase("Select child category")) {
                        Log.d(TAG, "onItemSelected: " + str_id);
                        card_details_form_newitem.setVisibility(View.VISIBLE);

                        btn_insertNewItem.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                webServices.insertNewItem("this is name of item", "6643", "http://sourceinflow.com/jewelry/imagefolder/goldbangals.JPEG",
                                        "this is item desc", str_id).enqueue(new Callback<InsertNewItemResponse>() {
                                    @Override
                                    public void onResponse(Call<InsertNewItemResponse> call, Response<InsertNewItemResponse> response) {
                                        if (response.isSuccessful() && response.body() != null) {
                                            if (response.body().getStatus()) {

                                                Toast.makeText(AddNewItemActivity.this, "Status : " + response.body().getStatus(),
                                                        Toast.LENGTH_SHORT).show();
                                            } else {


                                                Toast.makeText(AddNewItemActivity.this, "Status : " + response.body().getStatus(),
                                                        Toast.LENGTH_SHORT).show();
                                            }

                                        }


                                    }

                                    @Override
                                    public void onFailure(Call<InsertNewItemResponse> call, Throwable t) {
                                        Toast.makeText(AddNewItemActivity.this, "onFailure: " + t.getMessage(),
                                                Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                        });


                    } else {
                        card_details_form_newitem.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                card_details_form_newitem.setVisibility(View.GONE);

            }
        });


    }
}
