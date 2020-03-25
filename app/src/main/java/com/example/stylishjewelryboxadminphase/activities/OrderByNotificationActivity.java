package com.example.stylishjewelryboxadminphase.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stylishjewelryboxadminphase.R;
import com.example.stylishjewelryboxadminphase.network.WebServices;
import com.example.stylishjewelryboxadminphase.orderfromnotification.GetODetailNotification;
import com.example.stylishjewelryboxadminphase.orderfromnotification.GetODetailNotificationADAPTER;
import com.example.stylishjewelryboxadminphase.orderfromnotification.GetODetailNotificationResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderByNotificationActivity extends AppCompatActivity {
    public static final String TAG = "MYTAG";
    WebServices webServices;
    RecyclerView recycler_noti;

    List<GetODetailNotification> list = new ArrayList<>();
    GetODetailNotificationADAPTER adapter;
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String id = intent.getStringExtra("myid");
            Toast.makeText(OrderByNotificationActivity.this, "key is : " + id, Toast.LENGTH_SHORT).show();
            getorderDetails(id);

//            String[] extra = intent.getStringArrayExtra("KEY");
//
//            if (extra != null) {
//                for (String data : extra) {
//
//                    Toast.makeText(OrderByNotificationActivity.this, "BroadcastReceiver: " + data, Toast.LENGTH_LONG).show();
//
//                }
//            }


        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_by_notification);
        webServices = WebServices.RETROFIT.create(WebServices.class);
        recycler_noti = findViewById(R.id.recycler_noti);


        if (getIntent().getExtras() != null) {
            String id = getIntent().getStringExtra("key");
            getorderDetails(id);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.registerReceiver(this.receiver, new IntentFilter("FCM"));
        Log.d(TAG, "onStart: resgistered");
//        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(receiver, new IntentFilter("FCM"));
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.unregisterReceiver(this.receiver);
//        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(receiver);

    }

    public void getorderDetails(String id) {


        webServices.getOrderDetailsFromNotification(id).enqueue(new Callback<GetODetailNotificationResponse>() {
            @Override
            public void onResponse(Call<GetODetailNotificationResponse> call, Response<GetODetailNotificationResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getStatus()) {

                        List<GetODetailNotification> list1 = response.body().getGetODetailNotification();

                        list.addAll(list1);

                        adapter = new GetODetailNotificationADAPTER(OrderByNotificationActivity.this, list);


                        recycler_noti.setAdapter(adapter);
                        recycler_noti.setLayoutManager(new LinearLayoutManager(OrderByNotificationActivity.this));
                        adapter.notifyDataSetChanged();


                    } else {
                        Toast.makeText(OrderByNotificationActivity.this, "stutus false: ", Toast.LENGTH_SHORT).show();
                    }


                }
            }

            @Override
            public void onFailure(Call<GetODetailNotificationResponse> call, Throwable t) {
                Toast.makeText(OrderByNotificationActivity.this, "onFailure" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}
