package com.example.stylishjewelryboxadminphase.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.stylishjewelryboxadminphase.R;
import com.example.stylishjewelryboxadminphase.activities.TotalOrdersActivity;
import com.example.stylishjewelryboxadminphase.calculations.percentages.GetAllForPercentage;
import com.example.stylishjewelryboxadminphase.calculations.percentages.GetAllForPercentageResponse;
import com.example.stylishjewelryboxadminphase.network.WebServices;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TotalSaleFragment extends Fragment {
    public static final String TAG = "MYTAG";
    Button btnmovetosirfttab, btnmovetodelivered, btnmovetounassigned;
    View view;
    WebServices webServices;
    private TextView tvpending_salepercent, tvtotalorder_unassnpercnt,
            tv_TotalPendingOrderspercntorder, tv_totalDeliverOrdersalepercent,
            tvtotalsale, tvunassignedSalepercent, tvdeliveredsalepercent, tvtotalorder,
            tvsaleofunassigned, tvsaleofdelivered, tv_saleofpending,
            tvTotalorderunassined, tvTotalorderdelivered, tvTotalorderPending;
    TotalSale mInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_total_sale, container, false);
        initviews();
        getallorders();
        try {
            mInterface = (TotalSale) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getContext().toString()
                    + " must implement BottomSheetListener");
        }
        return view;
    }

    private void initviews() {

        tvpending_salepercent = view.findViewById(R.id.tvpending_salepercent);
        tvdeliveredsalepercent = view.findViewById(R.id.tvdeliveredsalepercent);
        tvunassignedSalepercent = view.findViewById(R.id.tvunassignedSalepercent);
        tvtotalorder = view.findViewById(R.id.tvtotalorder);
        tvtotalsale = view.findViewById(R.id.tvtotalsale);
        tv_totalDeliverOrdersalepercent = view.findViewById(R.id.tv_totalDeliverOrdersalepercent);
        tv_TotalPendingOrderspercntorder = view.findViewById(R.id.tv_TotalPendingOrderspercntorder);
        tvtotalorder_unassnpercnt = view.findViewById(R.id.tvtotalorder_unassnpercnt);
        tvsaleofunassigned = view.findViewById(R.id.tvsaleofunassigned);
        tvsaleofdelivered = view.findViewById(R.id.tvsaleofdelivered);
        tv_saleofpending = view.findViewById(R.id.tv_saleofpending);
        tvTotalorderunassined = view.findViewById(R.id.tvTotalorderunassined);
        tvTotalorderdelivered = view.findViewById(R.id.tvTotalorderdelivered);
        tvTotalorderPending = view.findViewById(R.id.tvTotalorderPending);

        webServices = WebServices.RETROFIT.create(WebServices.class);
        btnmovetosirfttab = view.findViewById(R.id.btnmovetosirfttab);
        btnmovetodelivered = view.findViewById(R.id.btnmovetodelivered);
        btnmovetounassigned = view.findViewById(R.id.btnmovetounassigned);

        btnmovetosirfttab.setOnClickListener(v -> Objects.requireNonNull(TotalOrdersActivity.tabLayout.getTabAt(0)).select());
        btnmovetodelivered.setOnClickListener(v -> Objects.requireNonNull(TotalOrdersActivity.tabLayout.getTabAt(1)).select());
        btnmovetounassigned.setOnClickListener(v -> Objects.requireNonNull(TotalOrdersActivity.tabLayout.getTabAt(2)).select());


    }

    private void getallorders() {

        webServices.getAllOrderToMakePercentage(TotalOrdersActivity.LOCATION, TotalOrdersActivity.DATE).enqueue(new Callback<GetAllForPercentageResponse>() {
            @Override
            public void onResponse(Call<GetAllForPercentageResponse> call, Response<GetAllForPercentageResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getStatus()) {
                        GetAllForPercentage data = response.body().getGetAllForPercentage();
                        String unassigned = data.getTotalSaleOfUnassigned();

                        if (data.getTotalSale() != null) {
                            String totalSale = data.getTotalSale();

                            tvtotalsale.setText(totalSale);
                            mInterface.getTotalSale(totalSale);

                            if (unassigned != null) {
                                tvsaleofunassigned.setText(unassigned);
                                double amount = Double.parseDouble(unassigned);
                                double totalamount = Double.parseDouble(totalSale);
                                double res = (amount / totalamount) * 100;
                                Log.d(TAG, "onResponse: " + res);
                                tvunassignedSalepercent.setText(String.valueOf(res));

                            }

                            if (data.getTotalSaleOfDelivered() != null) {
                                tvsaleofdelivered.setText(data.getTotalSaleOfDelivered());
                                double amount = Double.parseDouble(data.getTotalSaleOfDelivered());
                                double totalamount = Double.parseDouble(totalSale);
                                double res = (amount / totalamount) * 100;
                                Log.d(TAG, "onResponse: " + res);
                                tvdeliveredsalepercent.setText(String.valueOf(res));
                            }
                            if (data.getTotalSaleOfPending() != null) {
                                tv_saleofpending.setText(data.getTotalSaleOfPending());
                                double amount = Double.parseDouble(data.getTotalSaleOfPending());
                                double totalamount = Double.parseDouble(totalSale);
                                double res = (amount / totalamount) * 100;
                                Log.d(TAG, "onResponse: " + res);
                                tvpending_salepercent.setText(String.valueOf(res));

                            }
                            if (data.getTotalOrders() != null) {
                                tvtotalorder.setText(data.getTotalOrders());
                                String totalOrders = data.getTotalOrders();


                                if (data.getTotalDeliveredOrders() != null) {

                                    tvTotalorderdelivered.setText(data.getTotalDeliveredOrders());
                                    double amount = Double.parseDouble(data.getTotalDeliveredOrders());
                                    double totalamount = Double.parseDouble(totalOrders);
                                    double res = (amount / totalamount) * 100;
                                    tv_totalDeliverOrdersalepercent.setText(String.valueOf(res));

                                }


                                if (data.getTotalPendingOrders() != null) {

                                    tvTotalorderPending.setText(data.getTotalPendingOrders());
                                    double amount = Double.parseDouble(data.getTotalPendingOrders());
                                    double totalamount = Double.parseDouble(totalOrders);
                                    double res = (amount / totalamount) * 100;
                                    tv_TotalPendingOrderspercntorder.setText(String.valueOf(res));

                                }

                                if (data.getTotalUnassigned() != null) {
                                    tvTotalorderunassined.setText(data.getTotalUnassigned());
                                    double amount = Double.parseDouble(data.getTotalUnassigned());
                                    double totalamount = Double.parseDouble(totalOrders);
                                    double res = (amount / totalamount) * 100;
                                    tvtotalorder_unassnpercnt.setText(String.valueOf(res));
                                }

                            }


                        }

                    } else {
                        Toast.makeText(getActivity(), "Status: " + response.body().getStatus(), Toast.LENGTH_SHORT).show();

                    }

                }
            }

            @Override
            public void onFailure(Call<GetAllForPercentageResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }


    public interface TotalSale {
        void getTotalSale(String totalSale);

    }


}
