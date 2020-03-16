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


public class PendingFragment extends Fragment  {
    WebServices webServices;
    View view;
    private NoOfPendingInterface mListener;

    RecyclerView rv_pending;
    List<GetOrderByDateLocation> list;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_pending, container, false);
        initviews();
        getPendingOrders();

        return view;
    }

    private void initviews() {

        rv_pending = view.findViewById(R.id.recyeler_pending);
        webServices = WebServices.RETROFIT.create(WebServices.class);
        try {
            mListener = (NoOfPendingInterface) getContext();
        } catch (ClassCastException e) {
            throw new ClassCastException(getContext().toString()
                    + " must implement BottomSheetListener");

        }
    }

    private void getPendingOrders() {
        webServices.getOrdersByLocationDateStatus(TotalOrdersActivity.LOCATION, TotalOrdersActivity.DATE, "0").enqueue(new Callback<GetOrderByDateLocationResponse>() {
            @Override
            public void onResponse(Call<GetOrderByDateLocationResponse> call, Response<GetOrderByDateLocationResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getStatus()) {

                        list = response.body().getGetOrderByDateLocation();
                        GetAllOrdersByParameter_Adapter adapter = new GetAllOrdersByParameter_Adapter(getContext(), list);
                        rv_pending.setAdapter(adapter);
                        rv_pending.setLayoutManager(new LinearLayoutManager(getContext()));
                        assert mListener != null;
                        mListener.onPendingNoOfOrderReceived(list.size());

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

//    @Override
//    public void onDateLocationChanged(String date, String location) {
//        Toast.makeText(getActivity(),date+ location,Toast.LENGTH_SHORT).show();
//    }


    public interface NoOfPendingInterface {
        void onPendingNoOfOrderReceived(int no);
    }
}
