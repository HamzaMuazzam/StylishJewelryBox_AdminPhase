package com.example.stylishjewelryboxadminphase.seeDeliveryboys;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.stylishjewelryboxadminphase.R;
import com.example.stylishjewelryboxadminphase.activities.SeeDeliveryBoysActivity;
import com.example.stylishjewelryboxadminphase.network.WebServices;
import com.example.stylishjewelryboxadminphase.seeDeliveryboys.jdb_details.SeeJDBAssignedOrder;
import com.example.stylishjewelryboxadminphase.seeDeliveryboys.jdb_details.SeeJDBAssignedOrderResponse;
import com.example.stylishjewelryboxadminphase.seeDeliveryboys.jdb_details.SeeJDBOrderbystatusDate;
import com.example.stylishjewelryboxadminphase.seeDeliveryboys.jdb_details.SeeJDBOrderbystatusDateResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AssignedJDBFragment extends Fragment {
    private WebServices webServices;
    private View view;
TextView tvcurrentAssigned,tvlast7Assigned,tvlast30Assigned;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_assigned_j_d_b, container, false);
        initviews();
        getJdb_deliveredOrderByDateStatus();
        return view;
    }

    private void initviews() {
        webServices = WebServices.RETROFIT.create(WebServices.class);

        tvlast30Assigned=view.findViewById(R.id.tvlast30Assigned);

        tvlast7Assigned=view.findViewById(R.id.tvlast7Assigned);

        tvcurrentAssigned=view.findViewById(R.id.tvcurrentAssigned);


    }

    private void getJdb_deliveredOrderByDateStatus() {
        if (SeeDeliveryBoysActivity.JDB_ID != null) {
            webServices.seeJDB_Assigned_order_By_DateStatus(SeeDeliveryBoysActivity.JDB_ID,  SeeDeliveryBoysActivity.CURRENT_DATE,
                    SeeDeliveryBoysActivity.LAST30DAYS_DATE, SeeDeliveryBoysActivity.LAST_7DAYS_DATE).enqueue(new Callback<SeeJDBAssignedOrderResponse>() {
                @Override
                public void onResponse(Call<SeeJDBAssignedOrderResponse> call, Response<SeeJDBAssignedOrderResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {

                        if (response.body().getStatus()) {
                            SeeJDBAssignedOrder data = response.body().getSeeJDBAssignedOrder();


                            tvcurrentAssigned.setText(data.getCurrentdateorder());

                            tvlast7Assigned.setText(data.getLast7DaysOrders());

                            tvlast30Assigned.setText(data.getLast30DaysOrders());


                        }
                    }
                }

                @Override
                public void onFailure(Call<SeeJDBAssignedOrderResponse> call, Throwable t) {
                    Toast.makeText(getActivity(), "onFailure: \n" + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }


}