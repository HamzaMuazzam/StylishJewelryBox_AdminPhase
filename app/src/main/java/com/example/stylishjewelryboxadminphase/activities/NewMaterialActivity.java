package com.example.stylishjewelryboxadminphase.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stylishjewelryboxadminphase.R;
import com.example.stylishjewelryboxadminphase.addcategoris.addnewMaterial.AddNewMaterialResponse;
import com.example.stylishjewelryboxadminphase.network.WebServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewMaterialActivity extends AppCompatActivity {
    private EditText et_materilname, et_materilprice;
    private WebServices webServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_material);
        intiviews();


    }

    private void intiviews() {
        webServices = WebServices.RETROFIT.create(WebServices.class);
        et_materilname = findViewById(R.id.et_materilname);
        et_materilprice = findViewById(R.id.et_materilprice);

    }


    public void submit(View view) {
        insertion();
    }


    private void insertion() {
        String name = et_materilname.getText().toString().trim();
        String price = et_materilprice.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            et_materilname.setFocusable(true);
            et_materilname.setError("Can't be Empty");
        } else if (TextUtils.isEmpty(price)) {
            et_materilprice.setFocusable(true);
            et_materilprice.setError("Can't be Empty");
            et_materilname.setError(null);
        } else {
            webServices.addNewMaterial(name, price).enqueue(new Callback<AddNewMaterialResponse>() {
                @Override
                public void onResponse(Call<AddNewMaterialResponse> call, Response<AddNewMaterialResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Toast.makeText(NewMaterialActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
                        et_materilname.setText(null);
                        et_materilprice.setText(null);
                    }
                }

                @Override
                public void onFailure(Call<AddNewMaterialResponse> call, Throwable t) {
                    Toast.makeText(NewMaterialActivity.this, "OnFailure : " + t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

        }

    }
}
