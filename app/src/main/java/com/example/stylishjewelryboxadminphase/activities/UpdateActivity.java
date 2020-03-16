package com.example.stylishjewelryboxadminphase.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stylishjewelryboxadminphase.R;

public class UpdateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

    }

    public void updateMaterial(View view) {
        Intent intent = new Intent(this, UpdateMaterialActivity.class);
        startActivity(intent);
    }

    public void updateCategory(View view) {
        Intent intent = new Intent(this, UpdateCategoryActivity.class);
        startActivity(intent);

    }

    public void update_ChildCategory(View view) {
        Intent intent = new Intent(this, UpdateChildActivity.class);
        startActivity(intent);
    }

    public void update_singleItem(View view) {
        Intent intent = new Intent(this, UpdateItemActivity.class);
        startActivity(intent);
    }
}
