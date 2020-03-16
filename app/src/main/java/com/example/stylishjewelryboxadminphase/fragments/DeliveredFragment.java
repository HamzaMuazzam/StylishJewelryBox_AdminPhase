package com.example.stylishjewelryboxadminphase.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stylishjewelryboxadminphase.R;
import com.example.stylishjewelryboxadminphase.activities.TotalOrdersActivity;
import com.example.stylishjewelryboxadminphase.calculations.getallordersbydatelocation.GetOrderByDateLocation;
import com.example.stylishjewelryboxadminphase.calculations.getallordersbydatelocation.GetOrderByDateLocationResponse;
import com.example.stylishjewelryboxadminphase.network.WebServices;
import com.example.stylishjewelryboxadminphase.recyclerviews.gettotalorders.GetAllOrdersByParameter_Adapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeliveredFragment extends Fragment {
    RecyclerView recycler_delivered;
    WebServices webServices;
    View view;
    NoOfDeliveredInterface anInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_delivered, container, false);
        initviews();
        getdeliveredOrders();
        try {
            anInterface = (NoOfDeliveredInterface) getContext();
        } catch (ClassCastException e) {
            throw new ClassCastException(getContext().toString()
                    + " must implement BottomSheetListener");

        }
        return view;
    }

    private void initviews() {
        webServices = WebServices.RETROFIT.create(WebServices.class);
        recycler_delivered = view.findViewById(R.id.recycler_delivered);
    }

    private void getdeliveredOrders() {
        webServices.getOrdersByLocationDateStatus(TotalOrdersActivity.LOCATION, TotalOrdersActivity.DATE, "1").enqueue(new Callback<GetOrderByDateLocationResponse>() {
            @Override
            public void onResponse(Call<GetOrderByDateLocationResponse> call, Response<GetOrderByDateLocationResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getStatus()) {
                        List<GetOrderByDateLocation> list = response.body().getGetOrderByDateLocation();
                        list = response.body().getGetOrderByDateLocation();
                        GetAllOrdersByParameter_Adapter adapter = new GetAllOrdersByParameter_Adapter(getContext(), list);
                        recycler_delivered.setAdapter(adapter);
                        recycler_delivered.setLayoutManager(new LinearLayoutManager(getContext()));
                        anInterface.onDeliveredNoOfOrderReceived(list.size());


                    } else {
                        Toast.makeText(getContext(), "No Order Found", Toast.LENGTH_SHORT).show();
                    }


                }
            }

            @Override
            public void onFailure(Call<GetOrderByDateLocationResponse> call, Throwable t) {
                Toast.makeText(getContext(), "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


    public interface NoOfDeliveredInterface {
        void onDeliveredNoOfOrderReceived(int no);
    }
}
