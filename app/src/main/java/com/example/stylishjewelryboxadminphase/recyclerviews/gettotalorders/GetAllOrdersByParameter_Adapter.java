package com.example.stylishjewelryboxadminphase.recyclerviews.gettotalorders;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stylishjewelryboxadminphase.R;
import com.example.stylishjewelryboxadminphase.calculations.getallordersbydatelocation.GetOrderByDateLocation;

import java.util.List;

public class GetAllOrdersByParameter_Adapter extends RecyclerView.Adapter<GetAllOrdersByParameter_ViewHolder> {
    Context context;
    List<GetOrderByDateLocation> list;

    public GetAllOrdersByParameter_Adapter(Context context, List<GetOrderByDateLocation> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public GetAllOrdersByParameter_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GetAllOrdersByParameter_ViewHolder(LayoutInflater.from(context).inflate(R.layout.getallorder_itemayout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GetAllOrdersByParameter_ViewHolder holder, int position) {
        GetOrderByDateLocation model = list.get(position);
        holder.tv_locationa.setText(model.getJomdLocation());
        holder.tv_orderid.setText(model.getJomdOrderId());
        holder.tv_oderdate.setText(model.getJomdDatetime());


        if (model.getOrderstatus().equals("0")) {
            holder.tv_orderstatus.setText("Pending");



        } else {
            holder.tv_orderstatus.setText("Delivered");

        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
