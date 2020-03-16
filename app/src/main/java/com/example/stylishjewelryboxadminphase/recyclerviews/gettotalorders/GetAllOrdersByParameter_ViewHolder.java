package com.example.stylishjewelryboxadminphase.recyclerviews.gettotalorders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stylishjewelryboxadminphase.R;

public class GetAllOrdersByParameter_ViewHolder extends RecyclerView.ViewHolder {
    TextView tv_oderdate, tv_orderid, tv_locationa,tv_orderstatus;

    public GetAllOrdersByParameter_ViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_orderid = itemView.findViewById(R.id.tv_orderid);
        tv_oderdate = itemView.findViewById(R.id.tv_oderdate);
        tv_locationa = itemView.findViewById(R.id.tv_locationa);
        tv_orderstatus = itemView.findViewById(R.id.tv_orderstatus);
    }
}
