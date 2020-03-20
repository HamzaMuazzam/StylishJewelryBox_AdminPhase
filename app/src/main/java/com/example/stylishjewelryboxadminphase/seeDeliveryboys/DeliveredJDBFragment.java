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
import com.example.stylishjewelryboxadminphase.seeDeliveryboys.jdb_details.SeeJDBOrderbystatusDate;
import com.example.stylishjewelryboxadminphase.seeDeliveryboys.jdb_details.SeeJDBOrderbystatusDateResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeliveredJDBFragment extends Fragment {
    private WebServices webServices;
    private TextView tv_DeliveredcurretSeeJDB, tv_Deliveredlast7days, tv_Deliveredlast30days;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_delivered_j_d_b, container, false);
        initviews();
        getJdb_deliveredOrderByDateStatus();
        return view;
    }

    private void initviews() {
        webServices = WebServices.RETROFIT.create(WebServices.class);

        tv_DeliveredcurretSeeJDB = view.findViewById(R.id.tv_DeliveredcurretSeeJDB);
        tv_Deliveredlast7days = view.findViewById(R.id.tv_Deliveredlast7days);
        tv_Deliveredlast30days = view.findViewById(R.id.tv_Deliveredlast30days);
    }

    private void getJdb_deliveredOrderByDateStatus() {
        if (SeeDeliveryBoysActivity.JDB_ID != null) {
            webServices.seeJDB_order_By_DateStatus(SeeDeliveryBoysActivity.JDB_ID, "1", SeeDeliveryBoysActivity.CURRENT_DATE,
                    SeeDeliveryBoysActivity.LAST30DAYS_DATE, SeeDeliveryBoysActivity.LAST_7DAYS_DATE).enqueue(new Callback<SeeJDBOrderbystatusDateResponse>() {
                @Override
                public void onResponse(Call<SeeJDBOrderbystatusDateResponse> call, Response<SeeJDBOrderbystatusDateResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {

                        if (response.body().getStatus()) {
                            SeeJDBOrderbystatusDate data = response.body().getSeeJDBOrderbystatusDate();


                            tv_DeliveredcurretSeeJDB.setText(data.getCurrentdateorder());

                            tv_Deliveredlast7days.setText(data.getLast7DaysOrders());

                            tv_Deliveredlast30days.setText(data.getLast30DaysOrders());
                        }
                    }
                }

                @Override
                public void onFailure(Call<SeeJDBOrderbystatusDateResponse> call, Throwable t) {
                    Toast.makeText(getActivity(), "onFailure: \n" + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

}
