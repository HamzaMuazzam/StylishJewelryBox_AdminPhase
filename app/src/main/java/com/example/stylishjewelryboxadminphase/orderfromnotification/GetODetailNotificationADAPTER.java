package com.example.stylishjewelryboxadminphase.orderfromnotification;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stylishjewelryboxadminphase.R;

import java.util.List;

public class GetODetailNotificationADAPTER extends RecyclerView.Adapter<GetODetailNotificationVIEWHOLDER> {
    private Context context;
    private List<GetODetailNotification> list;

    public GetODetailNotificationADAPTER(Context context, List<GetODetailNotification> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public GetODetailNotificationVIEWHOLDER onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GetODetailNotificationVIEWHOLDER(LayoutInflater.from(context).inflate(R.layout.getorder_notification_itemayout, parent
                , false));
    }

    @Override
    public void onBindViewHolder(@NonNull GetODetailNotificationVIEWHOLDER holder, int position) {
        GetODetailNotification model = list.get(position);

        holder.tv_cid_noti.setText(model.getJcdIdFk());
        holder.tv_cname_noti.setText(model.getJcdName());
        holder.tv_phone_noti.setText(model.getJcdPhone());

        holder.tv_location_noti.setText(model.getJomdLocation());
        holder.tv_oderdate_noti.setText(model.getJomdDatetime());
        holder.tv_orderid_noti.setText(model.getJomdOrderId());

        holder.tv_orderstatus_noti.setText("Pending");
        holder.tv_tItem_noti.setText(model.getTotalItems());
        holder.tv_tPrice_noti.setText(model.getTotalCost());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
