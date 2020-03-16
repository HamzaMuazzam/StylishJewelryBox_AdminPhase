package com.example.stylishjewelryboxadminphase.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.stylishjewelryboxadminphase.R;
import com.example.stylishjewelryboxadminphase.addcategoris.addcats.GetAllMeterialCat;
import com.example.stylishjewelryboxadminphase.addcategoris.addcats.GetAllMeterialCatResponse;
import com.example.stylishjewelryboxadminphase.network.WebServices;
import com.example.stylishjewelryboxadminphase.updateCategory.UpdateMaterialResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateMaterialActivity extends AppCompatActivity {
    private Spinner spv_updateMeterialName;
    WebServices webServices;
    CardView cardd_update_material;
    EditText et_updateMaterial, et_updateMaterial_price;
    Button btn_updateMaterial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_material);
        intiviews();
        getMaterialNames();


    }

    private void intiviews() {
        webServices = WebServices.RETROFIT.create(WebServices.class);
        spv_updateMeterialName = findViewById(R.id.spv_updateMeterialName);
        cardd_update_material = findViewById(R.id.cardd_update_material);
        et_updateMaterial = findViewById(R.id.et_updateMaterial);
        et_updateMaterial_price = findViewById(R.id.et_updateMaterial_price);
        btn_updateMaterial = findViewById(R.id.btn_updateMaterial);
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
                Toast.makeText(UpdateMaterialActivity.this, "OnFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();

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
        spv_updateMeterialName.setAdapter(arrayAdapter_slct_material);

        spv_updateMeterialName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent != null) {
                    String item = parent.getItemAtPosition(position).toString();
                    String ids = arrayofis[position];
                    ((TextView) parent.getChildAt(0)).setText(item);
                    ((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#031A9E"));
                    if (!item.equalsIgnoreCase("Select Material")) {
                        cardd_update_material.setVisibility(View.VISIBLE);
                        btn_updateMaterial.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String newMName = et_updateMaterial.getText().toString().trim();
                                String newMPrice = et_updateMaterial_price.getText().toString().trim();

                                if (TextUtils.isEmpty(newMName)) {
                                    et_updateMaterial.setError("Can't be Empty");
                                    et_updateMaterial.setFocusable(true);

                                } else if (TextUtils.isEmpty(newMPrice)) {
                                    et_updateMaterial_price.setFocusable(true);
                                    et_updateMaterial_price.setError("Can't be Empty");
                                    et_updateMaterial.setError(null);

                                } else {
                                    et_updateMaterial.setError(null);
                                    et_updateMaterial_price.setError(null);
//                                    Toast.makeText(UpdateMaterialActivity.this, "ID: " + ids, Toast.LENGTH_SHORT).show();
                                    webServices.updateMaterialById(newMPrice, ids, newMName).enqueue(new Callback<UpdateMaterialResponse>() {
                                        @Override
                                        public void onResponse(Call<UpdateMaterialResponse> call, Response<UpdateMaterialResponse> response) {
                                            if (response.body() != null && response.isSuccessful()) {
                                                if (response.body().getStatus()) {
                                                  et_updateMaterial.setText(null);
                                                  et_updateMaterial_price.setText(null);
                                                    Toast.makeText(UpdateMaterialActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<UpdateMaterialResponse> call, Throwable t) {
                                            Toast.makeText(UpdateMaterialActivity.this, "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();

                                        }
                                    });
                                }


                            }
                        });
                    } else {
                        cardd_update_material.setVisibility(View.GONE);


                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                cardd_update_material.setVisibility(View.GONE);

            }
        });

    }


}
