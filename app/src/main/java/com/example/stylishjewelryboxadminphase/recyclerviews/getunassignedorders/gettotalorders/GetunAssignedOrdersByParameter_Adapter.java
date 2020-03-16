package com.example.stylishjewelryboxadminphase.recyclerviews.getunassignedorders.gettotalorders;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stylishjewelryboxadminphase.R;

import java.util.List;

public class GetunAssignedOrdersByParameter_Adapter extends RecyclerView.Adapter<GetunAssignedOrdersByParameter_ViewHolder> {
    Context context;
    List<UnassignedDateLocation> list;

    public GetunAssignedOrdersByParameter_Adapter(Context context, List<UnassignedDateLocation> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public GetunAssignedOrdersByParameter_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GetunAssignedOrdersByParameter_ViewHolder(LayoutInflater.from(context).inflate(R.layout.getunassigendorder_itemayout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GetunAssignedOrdersByParameter_ViewHolder holder, int position) {
        UnassignedDateLocation model = list.get(position);
        holder.tv_locationa.setText(model.getJomdLocation());
        holder.tv_orderid.setText(model.getJomdOrderId());
        holder.tv_oderdate.setText(model.getJomdDatetime());
        holder.tv_orderstatus.setText("Un Assigned");
        holder.tv_orderstatus.setTextColor(Color.DKGRAY);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
