package com.example.stylishjewelryboxadminphase.recyclerviews;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stylishjewelryboxadminphase.R;
import com.example.stylishjewelryboxadminphase.activities.AddActivity;
import com.example.stylishjewelryboxadminphase.activities.OrderAssignmentActivity;
import com.example.stylishjewelryboxadminphase.activities.SeeDeliveryBoysActivity;
import com.example.stylishjewelryboxadminphase.activities.TotalOrdersActivity;
import com.example.stylishjewelryboxadminphase.activities.UpdateActivity;

import java.util.ArrayList;

public class Cats_Adapter extends RecyclerView.Adapter<Cats_Viewholder> {
    private Context context;
    private ArrayList<Cats_Model_Class> list;

    public Cats_Adapter(Context context, ArrayList<Cats_Model_Class> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Cats_Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout_categories, parent, false);
        return new Cats_Viewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Cats_Viewholder holder, final int position) {

        Cats_Model_Class modelClass = list.get(position);

        holder.cats_image.setImageBitmap(modelClass.getCats_image());
        holder.cats_name.setText(modelClass.getCats_name());
        holder.view.setOnClickListener(v -> {


            switch (position) {


                case 0:

                    Intent intentaddcat = new Intent(context, AddActivity.class);
                    context.startActivity(intentaddcat);


                    break;
                case 1:


                    Intent intentUpdateActivity = new Intent(context, UpdateActivity.class);
                    context.startActivity(intentUpdateActivity);


                    break;
                case 2:

                    //do here
                    Intent intent = new Intent(context, TotalOrdersActivity.class);
                    context.startActivity(intent);

                    break;
                case 3:
                    Intent intent1 = new Intent(context, SeeDeliveryBoysActivity.class);
                    context.startActivity(intent1);
                    //do here

                    break;
                case 4:

                    //do here

                    break;
                case 5:
                    Intent intentcal = new Intent(context, OrderAssignmentActivity.class);
                    context.startActivity(intentcal);
//                        ((FragmentActivity) context).getSupportFragmentManager().beginTransaction()
////                                .setCustomAnimations(R.anim.right_left, R.anim.exit_right_to_left
////                                        , R.anim.left_right, R.anim.exit_left_to_right)
//                                .replace(R.id.mainfragment, new AssignFragment())
//                                .addToBackStack(null)
//                                .commit();
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
