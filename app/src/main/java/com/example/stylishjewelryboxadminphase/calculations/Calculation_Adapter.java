package com.example.stylishjewelryboxadminphase.calculations;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stylishjewelryboxadminphase.R;
import com.example.stylishjewelryboxadminphase.activities.TotalOrdersActivity;

import java.util.List;

public class Calculation_Adapter extends RecyclerView.Adapter<Calculation_Viewholder> {
    private Context context;
    private List<Calculation_Model_Class> list;

    public Calculation_Adapter(Context context, List<Calculation_Model_Class> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Calculation_Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout_calculation, parent, false);
        return new Calculation_Viewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Calculation_Viewholder holder, final int position) {

        Calculation_Model_Class modelClass = list.get(position);

        holder.cats_image.setImageBitmap(modelClass.getCats_image());
        holder.cats_name.setText(modelClass.getCats_name());
        holder.view.setOnClickListener(v -> {


            switch (position) {


                case 0:
                    Intent  intent=new Intent(context, TotalOrdersActivity.class);
                    context.startActivity(intent);

                    break;
                case 1:


                    break;
                case 2:


                    break;
                case 3:




                    break;
                case 4:

                    //do here

                    break;
                case 5:


                    break;

                default:
                    Toast.makeText(context, "Something went Wrong", Toast.LENGTH_SHORT).show();


            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
