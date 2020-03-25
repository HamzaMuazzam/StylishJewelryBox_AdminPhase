package com.example.stylishjewelryboxadminphase.recyclerviews.getallorderforassignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stylishjewelryboxadminphase.R;
import com.example.stylishjewelryboxadminphase.activities.OrderAssignmentActivity;
import com.example.stylishjewelryboxadminphase.order_assignment.GetOrderForAssignment;

import java.util.List;

public class GetAllOrder_Assignment_Adapter extends RecyclerView.Adapter<GetAllOrder_Assignment_ViewHolder> {
    private Context context;
    OrderAssignmentActivity activity;
    private List<GetOrderForAssignment> list;

    public GetAllOrder_Assignment_Adapter(Context context, List<GetOrderForAssignment> list) {
        this.context = context;
        this.activity = (OrderAssignmentActivity) context;
        this.list = list;


    }

    @NonNull
    @Override
    public GetAllOrder_Assignment_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GetAllOrder_Assignment_ViewHolder(LayoutInflater.from(context).inflate(R.layout.order_for_assignment_item_layout
                , parent, false), activity);
    }

    @Override
    public void onBindViewHolder(@NonNull GetAllOrder_Assignment_ViewHolder holder, int position) {
        GetOrderForAssignment model = list.get(position);
        holder.order_number.setText(model.getJomdOrderId());
        holder.location.setText(model.getJomdLocation());
        holder.date.setText(model.getJomdDatetime());

        if (!activity.is_in_action) {
            holder.checkbox_assignorders.setVisibility(View.GONE);
        } else {
            holder.checkbox_assignorders.setVisibility(View.VISIBLE);

//            holder.checkBox.setChecked(true);
        }




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
