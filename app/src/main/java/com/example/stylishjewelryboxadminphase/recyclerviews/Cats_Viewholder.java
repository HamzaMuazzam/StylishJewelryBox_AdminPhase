package com.example.stylishjewelryboxadminphase.recyclerviews;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stylishjewelryboxadminphase.R;

public class Cats_Viewholder extends RecyclerView.ViewHolder {
    public ImageView cats_image;
    public TextView cats_name;
    View view;

    public Cats_Viewholder(@NonNull View itemView) {
        super(itemView);
        cats_image = itemView.findViewById(R.id.iv_catsitemlayout);
        cats_name = itemView.findViewById(R.id.tv_catsnames);
        view = itemView;

    }


}


