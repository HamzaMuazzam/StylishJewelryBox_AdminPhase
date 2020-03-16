package com.example.stylishjewelryboxadminphase.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.stylishjewelryboxadminphase.R;
import com.google.android.material.tabs.TabLayout;

public class AddActivity extends AppCompatActivity {

    public static TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_category);
    }




    public void add_NewMaterial(View view) {

        Intent intent = new Intent(this, NewMaterialActivity.class);

        startActivity(intent);

    }

    public void addNewCategory(View view) {

        Intent intent = new Intent(this, NewCategoryActivity.class);
        startActivity(intent);

    }

    public void addNewChildCategory(View view) {


        Intent intent = new Intent(this, AddNewChildCategoryActivity.class);
        startActivity(intent);
    }

    public void addNewItem(View view) {
        Intent intent = new Intent(this, AddNewItemActivity.class);
        startActivity(intent);
    }


}
