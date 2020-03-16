package com.example.stylishjewelryboxadminphase.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stylishjewelryboxadminphase.R;
import com.example.stylishjewelryboxadminphase.addcategoris.addcats.GetAllMeterialCat;
import com.example.stylishjewelryboxadminphase.addcategoris.addcats.GetAllMeterialCatResponse;
import com.example.stylishjewelryboxadminphase.addcategoris.addcats.InsertNewCategory;
import com.example.stylishjewelryboxadminphase.network.WebServices;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewCategoryActivity extends AppCompatActivity {
    Spinner spv_addnew_cats;
    private ArrayAdapter<String> arrayAdapterOfAddCat;
    WebServices webServices;
    Button btn_insertNewCategory;
    LinearLayout linear_addcategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_category);
        initviews();
    }

    private void initviews() {
        spv_addnew_cats = findViewById(R.id.spv_addnew_cats);
        btn_insertNewCategory = findViewById(R.id.btn_insertNewCategory);
        linear_addcategory = findViewById(R.id.linear_addcategory);
        webServices = WebServices.RETROFIT.create(WebServices.class);
        getMaterialNames();
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
                        array1[0] = "Select Material Type";
                        String[] array2 = new String[listsize];


                        String[] array3 = new String[1];
                        array3[0] = "Select Material";
                        String[] array4 = new String[listsize];


                        for (int x = 0; x < meterialCatList.size(); x++) {
                            array2[x] = meterialCatList.get(x).getMcName();
                            array4[x] = meterialCatList.get(x).getMcId();

                        }


                        String[] locationarray1 = array2;
                        String[] locationarray2 = array1;
                        int length1 = locationarray1.length;
                        int length2 = locationarray2.length;
                        int length3 = array3.length; //slect
                        int length4 = array4.length; //id
                        String[] new_array = new String[length1 + length2];
                        System.arraycopy(locationarray2, 0, new_array, 0, length2);
                        System.arraycopy(locationarray1, 0, new_array, length2, length1);


                        String[] new_arrayofIDS = new String[length3 + length4];
                        System.arraycopy(array3, 0, new_arrayofIDS, 0, length3);
                        System.arraycopy(array4, 0, new_arrayofIDS, length3, length4);

                        spinner_addnewCategory(new_array, new_arrayofIDS);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetAllMeterialCatResponse> call, Throwable t) {
                Toast.makeText(NewCategoryActivity.this, "OnFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void spinner_addnewCategory(String[] array, String[] arrayOfIDS) {

//        for (String item : array) {
//            Log.d("MYTAG", "spinner_addnewCategory: " + item);
//        }

        arrayAdapterOfAddCat = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array);
        arrayAdapterOfAddCat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spv_addnew_cats.setAdapter(arrayAdapterOfAddCat);

        spv_addnew_cats.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent != null) {
                    String item = parent.getItemAtPosition(position).toString();
                    String ID = arrayOfIDS[position];
                    ((TextView) parent.getChildAt(0)).setText(item);
                    ((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#031A9E"));
                    if (!item.equalsIgnoreCase("Select Material Type")) {
                        linear_addcategory.setVisibility(View.VISIBLE);
                        Log.d("MYTAG", "onItemSelected: " + ID);
                        btn_insertNewCategory.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                webServices.insertNewCategory("Test Name", "909", "null",
                                        ID).enqueue(new Callback<InsertNewCategory>() {
                                    @Override
                                    public void onResponse(Call<InsertNewCategory> call, Response<InsertNewCategory> response) {
                                        if (response.isSuccessful() && response.body() != null) {
                                            if (response.body().getStatus()) {

                                                Toast.makeText(NewCategoryActivity.this, "Status " + response.body().getStatus()
                                                        , Toast.LENGTH_LONG).show();

                                            }

                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<InsertNewCategory> call, Throwable t) {

                                        Toast.makeText(NewCategoryActivity.this, "OnFailure " + t.getMessage()
                                                , Toast.LENGTH_LONG).show();
                                    }
                                });


                            }
                        });

                    } else {
                        linear_addcategory.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
