package com.example.stylishjewelryboxadminphase.orderfromnotification;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stylishjewelryboxadminphase.R;

public class GetODetailNotificationVIEWHOLDER extends RecyclerView.ViewHolder {
    TextView tv_orderid_noti, tv_oderdate_noti, tv_location_noti, tv_tItem_noti, tv_tPrice_noti, tv_orderstatus_noti, tv_cid_noti, tv_cname_noti, tv_phone_noti;

    public GetODetailNotificationVIEWHOLDER(@NonNull View itemView) {
        super(itemView);

        tv_orderid_noti = itemView.findViewById(R.id.tv_orderid_noti);
        tv_oderdate_noti = itemView.findViewById(R.id.tv_oderdate_noti);
        tv_location_noti = itemView.findViewById(R.id.tv_location_noti);
        tv_tItem_noti = itemView.findViewById(R.id.tv_tItem_noti);
        tv_tPrice_noti = itemView.findViewById(R.id.tv_tPrice_noti);
        tv_orderstatus_noti = itemView.findViewById(R.id.tv_orderstatus_noti);
        tv_cid_noti = itemView.findViewById(R.id.tv_cid_noti);
        tv_cname_noti = itemView.findViewById(R.id.tv_cname_noti);
        tv_phone_noti = itemView.findViewById(R.id.tv_phone_noti);


    }
}
