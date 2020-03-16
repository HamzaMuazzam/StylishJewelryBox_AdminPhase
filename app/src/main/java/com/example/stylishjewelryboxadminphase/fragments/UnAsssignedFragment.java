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
import com.example.stylishjewelryboxadminphase.network.WebServices;
import com.example.stylishjewelryboxadminphase.recyclerviews.getunassignedorders.gettotalorders.GetunAssignedOrdersByParameter_Adapter;
import com.example.stylishjewelryboxadminphase.recyclerviews.getunassignedorders.gettotalorders.UnassignedDateLocation;
import com.example.stylishjewelryboxadminphase.recyclerviews.getunassignedorders.gettotalorders.UnassignedDateLocationResponse;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class UnAsssignedFragment extends Fragment {
    View view;
    RecyclerView rv_unassignedOrder;
    WebServices webServices;
    NoOfUnAssignedInterface anInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_un_asssigned, container, false);
        initviews();
        getUnAssignedOrders();

        return view;
    }

    private void getUnAssignedOrders() {
        webServices.getUnassignedOrders(TotalOrdersActivity.LOCATION, TotalOrdersActivity.DATE, "0").enqueue(new Callback<UnassignedDateLocationResponse>() {
            @Override
            public void onResponse(Call<UnassignedDateLocationResponse> call, Response<UnassignedDateLocationResponse> response) {
                if (response.isSuccessful() && response.body() != null) {

                    if (response.body().getStatus()) {
                        List<UnassignedDateLocation> list = response.body().getUnassignedDateLocation();
                        GetunAssignedOrdersByParameter_Adapter adapter = new GetunAssignedOrdersByParameter_Adapter(getActivity(), list);
                        rv_unassignedOrder.setAdapter(adapter);
                        rv_unassignedOrder.setLayoutManager(new LinearLayoutManager(getActivity()));
                        adapter.notifyDataSetChanged();


                        anInterface.onUnAssignedNoOfOrderReceived(list.size());
                    } else {
                        Toast.makeText(getActivity(), "false", Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<UnassignedDateLocationResponse> call, Throwable t) {

                Toast.makeText(getActivity(), "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void initviews() {
        webServices = WebServices.RETROFIT.create(WebServices.class);
        rv_unassignedOrder = view.findViewById(R.id.rv_unassignedOrder);

        try {
            anInterface = (NoOfUnAssignedInterface) getContext();
        } catch (ClassCastException e) {
            throw new ClassCastException(Objects.requireNonNull(getContext()).toString()
                    + " must implement BottomSheetListener");

        }


    }

    public interface NoOfUnAssignedInterface {
        void onUnAssignedNoOfOrderReceived(int no);
    }
}
