package com.example.stylishjewelryboxadminphase.seeDeliveryboys;

import android.os.Bundle;
import android.util.Log;
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

public class PendingJDBFragment extends Fragment {
    public static final String TAG = "MYTAG";
    View view;

    WebServices webServices;
    private TextView tv_crrentdateofpending,tv_last7days_Pending,tv_last30days_pending;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_pending_j_d_b, container, false);
        initviews();
        getJdb_pendingOrderByDateStatus();


        return view;
    }

    private void initviews() {
        webServices = WebServices.RETROFIT.create(WebServices.class);
        tv_last30days_pending=view.findViewById(R.id.tv_last30days_pending);
        tv_last7days_Pending=view.findViewById(R.id.tv_last7days_Pending);
        tv_crrentdateofpending=view.findViewById(R.id.tv_crrentdateofpending);

    }

    private void getJdb_pendingOrderByDateStatus() {
        if (SeeDeliveryBoysActivity.JDB_ID != null) {
            webServices.seeJDB_order_By_DateStatus(SeeDeliveryBoysActivity.JDB_ID, "0", SeeDeliveryBoysActivity.CURRENT_DATE,
                    SeeDeliveryBoysActivity.LAST30DAYS_DATE, SeeDeliveryBoysActivity.LAST_7DAYS_DATE).enqueue(new Callback<SeeJDBOrderbystatusDateResponse>() {
                @Override
                public void onResponse(Call<SeeJDBOrderbystatusDateResponse> call, Response<SeeJDBOrderbystatusDateResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {

                        if (response.body().getStatus()) {
                            SeeJDBOrderbystatusDate data = response.body().getSeeJDBOrderbystatusDate();

                            Log.d(TAG, "current: " + data.getCurrentdateorder());
                            tv_crrentdateofpending.setText(data.getCurrentdateorder());
                            Log.d(TAG, "7days: " + data.getLast7DaysOrders());
                            tv_last7days_Pending.setText(data.getLast7DaysOrders());
                            Log.d(TAG, "30days: " + data.getLast30DaysOrders());
                            tv_last30days_pending.setText(data.getLast30DaysOrders());
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
