package com.example.stylishjewelryboxadminphase.fragments;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.stylishjewelryboxadminphase.recyclerviews.Cats_Adapter;
import com.example.stylishjewelryboxadminphase.recyclerviews.Cats_Model_Class;
import com.example.stylishjewelryboxadminphase.R;

import java.util.ArrayList;


public class HomeFragment extends Fragment
{

    View view;
    Cats_Adapter adapter;
    private ArrayList<Cats_Model_Class> list;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_home, container, false);
        list = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyelerview_categories);

        Bitmap addnewcat = getBitmapFromDrawable(getResources().getDrawable(R.drawable.ic_category));
        Bitmap updatecate = getBitmapFromDrawable(getResources().getDrawable(R.drawable.ic_updatecategory));
        Bitmap calculation = getBitmapFromDrawable(getResources().getDrawable(R.drawable.ic_calculator));
        Bitmap seeboys = getBitmapFromDrawable(getResources().getDrawable(R.drawable.ic_seeboy));
        Bitmap addboys = getBitmapFromDrawable(getResources().getDrawable(R.drawable.ic_addboy));
        Bitmap assignorder = getBitmapFromDrawable(getResources().getDrawable(R.drawable.ic_assignorder));

        list.add(new Cats_Model_Class(addnewcat, "Add New Category"));
        list.add(new Cats_Model_Class(updatecate, "Update A Category"));
        list.add(new Cats_Model_Class(calculation, "Toady's Calculations"));
        list.add(new Cats_Model_Class(seeboys, "See Delivery Boys"));
        list.add(new Cats_Model_Class(addboys, "Add Delivery Boy"));
        list.add(new Cats_Model_Class(assignorder, "Assign Orders"));
        adapter = new Cats_Adapter(getActivity(), list);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        return view;
    }


    @NonNull
    static private Bitmap getBitmapFromDrawable(@NonNull Drawable drawable) {
        final Bitmap bmp = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bmp);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bmp;
    }

}