package com.example.stylishjewelryboxadminphase.recyclerviews.getallorderforassignment;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stylishjewelryboxadminphase.R;
import com.example.stylishjewelryboxadminphase.activities.OrderAssignmentActivity;

public class GetAllOrder_Assignment_ViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {
    TextView date, location, order_number;
    CheckBox checkbox_assignorders;
    View view;
    OrderAssignmentActivity mActivity;
    CardView cardvifew;

    public GetAllOrder_Assignment_ViewHolder(@NonNull View itemView, OrderAssignmentActivity mActivity) {
        super(itemView);
        view = itemView;
        this.mActivity = mActivity;
        cardvifew = itemView.findViewById(R.id.cardvifew);
        date = itemView.findViewById(R.id.tv_date);
        location = itemView.findViewById(R.id.tv_location);
        order_number = itemView.findViewById(R.id.tv_order_number);
        checkbox_assignorders = itemView.findViewById(R.id.checkbox_assignorders);
        cardvifew.setOnLongClickListener(mActivity);

        checkbox_assignorders.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        mActivity.prepareSelection(v, getAdapterPosition());

    }
}
