package com.example.stylishjewelryboxadminphase.fragments;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stylishjewelryboxadminphase.R;
import com.example.stylishjewelryboxadminphase.get_order_and_delivered.GetAllOrderAndDelivered;
import com.example.stylishjewelryboxadminphase.get_order_and_delivered.GetAllOrderAndDeliveredResponse;
import com.example.stylishjewelryboxadminphase.network.WebServices;
import com.example.stylishjewelryboxadminphase.recyclerviews.Cats_Adapter;
import com.example.stylishjewelryboxadminphase.recyclerviews.Cats_Model_Class;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    public static final String DATE_FORMAT_1 = "yyyy-MM-dd";
    TextView total_order, total_pending_orders;
    View view;
    private WebServices webServices;

    Cats_Adapter adapter;
    private ArrayList<Cats_Model_Class> list;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);

        list = new ArrayList<>();
        webServices = WebServices.RETROFIT.create(WebServices.class);
        total_order = view.findViewById(R.id.tv_main_totalOrder);
        total_pending_orders = view.findViewById(R.id.tv_totalDeliverOrders);

        recyclerView = view.findViewById(R.id.recyelerview_categories);

        Bitmap addnewcat = getBitmapFromDrawable(getResources().getDrawable(R.drawable.ic_category));
        Bitmap updatecate = getBitmapFromDrawable(getResources().getDrawable(R.drawable.ic_updatecategory));
        Bitmap calculation = getBitmapFromDrawable(getResources().getDrawable(R.drawable.ic_calculator));
        Bitmap seeboys = getBitmapFromDrawable(getResources().getDrawable(R.drawable.ic_seeboy));
        Bitmap addboys = getBitmapFromDrawable(getResources().getDrawable(R.drawable.ic_addboy));
        Bitmap assignorder = getBitmapFromDrawable(getResources().getDrawable(R.drawable.ic_assignorder));

        list.add(new Cats_Model_Class(addnewcat, "Add New Category"));
        list.add(new Cats_Model_Class(updatecate, "Update A Category"));
        list.add(new Cats_Model_Class(calculation, "Toady's Calculations"));
        list.add(new Cats_Model_Class(seeboys, "See Delivery Boys"));
        list.add(new Cats_Model_Class(addboys, "See New Order"));
        list.add(new Cats_Model_Class(assignorder, "Assign Orders"));

        adapter = new Cats_Adapter(getActivity(), list);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2, RecyclerView.VERTICAL, false));

        recyclerView.setAdapter(adapter);


        getordersync();
        return view;


    }

    private String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_1);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date today = Calendar.getInstance().getTime();
        return dateFormat.format(today);
    }


    private void getordersync() {
        String currentDate = getCurrentDate();
        Toast.makeText(getActivity(), "Current Date: " + currentDate, Toast.LENGTH_SHORT).show();
        webServices.getAllOrderAndDelivered("2020-03-20").enqueue(new Callback<GetAllOrderAndDeliveredResponse>() {
            @Override
            public void onResponse(Call<GetAllOrderAndDeliveredResponse> call, Response<GetAllOrderAndDeliveredResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getStatus()) {
                        GetAllOrderAndDelivered getAllOrderAndDelivered = response.body().getGetAllOrderAndDelivered();

                        if (!getAllOrderAndDelivered.getTotalOrder().equalsIgnoreCase("0") && getAllOrderAndDelivered.getTotalOrder() != null) {
                            total_order.setText(getAllOrderAndDelivered.getTotalOrder());
                        }

                        if (!getAllOrderAndDelivered.getTotalDelivered().equalsIgnoreCase("0") && getAllOrderAndDelivered.getTotalDelivered() != null) {
                            total_pending_orders.setText(getAllOrderAndDelivered.getTotalDelivered());
                        }


                    } else {
                        Toast.makeText(getActivity(), "Staus: " + response.body().getStatus(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetAllOrderAndDeliveredResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


    @NonNull
    static private Bitmap getBitmapFromDrawable(@NonNull Drawable drawable) {
        final Bitmap bmp = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bmp);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bmp;
    }

}