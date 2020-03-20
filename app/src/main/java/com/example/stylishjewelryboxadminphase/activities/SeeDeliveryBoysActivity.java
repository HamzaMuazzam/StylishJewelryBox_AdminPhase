package com.example.stylishjewelryboxadminphase.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import com.example.stylishjewelryboxadminphase.R;
import com.example.stylishjewelryboxadminphase.network.WebServices;
import com.example.stylishjewelryboxadminphase.recyclerviews.getalldeliveryboys.GetAllDeliveryBoy;
import com.example.stylishjewelryboxadminphase.recyclerviews.getalldeliveryboys.GetAllDeliveryBoyResponse;
import com.example.stylishjewelryboxadminphase.seeDeliveryboys.AssignedJDBFragment;
import com.example.stylishjewelryboxadminphase.seeDeliveryboys.DeliveredJDBFragment;
import com.example.stylishjewelryboxadminphase.seeDeliveryboys.PendingJDBFragment;
import com.example.stylishjewelryboxadminphase.seeDeliveryboys.SeeJDB_VPAdapter;
import com.example.stylishjewelryboxadminphase.seeDeliveryboys.jdb_details.GetJDBDetail;
import com.example.stylishjewelryboxadminphase.seeDeliveryboys.jdb_details.GetJDBDetailResponse;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeeDeliveryBoysActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public static final String TAG = "MYTAG";
    private TextView tv_seeJDB_pending, tv_seeJDB_assigned, tv_seeJDB_delivered;
    private Spinner spv_seeDeliveryBoy;
    private ArrayAdapter<String> arrayAdapterOfJDB;
    private WebServices webServices;
    private String[] selectname, namesarrays, jdb_ids;
    private String delivery_boy_name = null;
    public static String JDB_ID = null;
    private CardView cv_jdb_details;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView tv_jdb_phone, tv_jdb_email, tv_jdb_name, tv_jdb_id;
    public static String CURRENT_DATE = null, LAST_7DAYS_DATE = null, LAST30DAYS_DATE = null;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_delivery_boys);
        initviews();
        getdeliveryboyslist();

    }

    private void initviews() {

        //string arrays
        selectname = new String[1];
        selectname[0] = "Select Name";

        //spinner
        spv_seeDeliveryBoy = findViewById(R.id.spv_seeDeliveryBoy);

        //textviews
        tv_seeJDB_delivered = findViewById(R.id.tv_seeJDB_delivered);
        tv_seeJDB_assigned = findViewById(R.id.tv_seeJDB_assigned);
        tv_seeJDB_pending = findViewById(R.id.tv_seeJDB_pending);

        tv_jdb_phone = findViewById(R.id.tv_jdb_phone);
        tv_jdb_name = findViewById(R.id.tv_jdb_name);
        tv_jdb_email = findViewById(R.id.tv_jdb_email);
        tv_jdb_id = findViewById(R.id.tv_jdb_id);


        //Api network
        webServices = WebServices.RETROFIT.create(WebServices.class);

        //tablayout
        tabLayout = findViewById(R.id.tlo_seeJDBs);
        viewPager = findViewById(R.id.vp_seeJDBs);
        viewPager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(viewPager);
        setupViewPager(viewPager);

        //card views
        cv_jdb_details = findViewById(R.id.cv_jdb_details);

    }

    private void getdeliveryboyslist() {
        webServices.getAll_JDBs("1").enqueue(new Callback<GetAllDeliveryBoyResponse>() {
            @Override
            public void onResponse(Call<GetAllDeliveryBoyResponse> call, Response<GetAllDeliveryBoyResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getStatus()) {
                        List<GetAllDeliveryBoy> list = response.body().getGetAllDeliveryBoy();
                        namesarrays = new String[list.size()];
                        jdb_ids = new String[list.size() + 1];
                        jdb_ids[0] = "000";
                        for (int x = 0; x < list.size(); x++) {

                            namesarrays[x] = list.get(x).getJdbName();
                            jdb_ids[x + 1] = list.get(x).getJdbId();
                        }
//                        if (locationarray.length == list.size()) {
//                        }
                        spinnerwork();

                    }
                }
            }

            @Override
            public void onFailure(Call<GetAllDeliveryBoyResponse> call, Throwable t) {

            }
        });
    }

    private void spinnerwork() {


        int length1 = namesarrays.length;
        int length2 = selectname.length;

        String[] new_array = new String[length1 + length2];

        System.arraycopy(selectname, 0, new_array, 0, length2);
        System.arraycopy(namesarrays, 0, new_array, length2, length1);


//        Toast.makeText(this, String.valueOf(length), Toast.LENGTH_SHORT).show();

        arrayAdapterOfJDB = new ArrayAdapter<String>(SeeDeliveryBoysActivity.this, android.R.layout.simple_spinner_item, new_array);

        arrayAdapterOfJDB.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );
        spv_seeDeliveryBoy.setAdapter(arrayAdapterOfJDB);

        spv_seeDeliveryBoy.setOnItemSelectedListener(SeeDeliveryBoysActivity.this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        if (parent != null) {
            delivery_boy_name = parent.getItemAtPosition(position).toString();
            ((TextView) parent.getChildAt(0)).setText(delivery_boy_name);
            ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);

            if (!delivery_boy_name.equalsIgnoreCase("Select Name")) {
                JDB_ID = jdb_ids[position];
                tabLayout.setupWithViewPager(viewPager);

                Toast.makeText(SeeDeliveryBoysActivity.this, JDB_ID, Toast.LENGTH_SHORT).show();
                webServices.get_JDB_Details(JDB_ID).enqueue(new Callback<GetJDBDetailResponse>() {
                    @Override
                    public void onResponse(Call<GetJDBDetailResponse> call, Response<GetJDBDetailResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            if (response.body().getStatus()) {

                                cv_jdb_details.setVisibility(View.VISIBLE);

                                List<GetJDBDetail> list = response.body().getGetJDBDetail();

                                String email = list.get(0).getJdbEmail();
                                String id_jdb = list.get(0).getJdbId();
                                String phone = list.get(0).getJdbPhone();
                                String name = list.get(0).getJdbName();

                                tv_jdb_phone.setText(phone);
                                tv_jdb_name.setText(name);
                                tv_jdb_email.setText(email);
                                tv_jdb_id.setText(id_jdb);


                                CURRENT_DATE = getCalculatedDate("yyyy-MM-dd", 0);
                                Log.d(TAG, "current Date: " + CURRENT_DATE);

                                LAST_7DAYS_DATE = getCalculatedDate("yyyy-MM-dd", -6);
                                Log.d(TAG, "last 7 days Date: " + LAST_7DAYS_DATE);

                                LAST30DAYS_DATE = getCalculatedDate("yyyy-MM-dd", -29);
                                Log.d(TAG, "last 30 days Date: " + LAST30DAYS_DATE);


                                setupViewPager(viewPager);












                            }
                        } else {
                            cv_jdb_details.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<GetJDBDetailResponse> call, Throwable t) {
                        Toast.makeText(SeeDeliveryBoysActivity.this, "OnFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        cv_jdb_details.setVisibility(View.GONE);

                    }
                });


            } else {
                cv_jdb_details.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void setupViewPager(ViewPager viewPager) {

        SeeJDB_VPAdapter viewPagerAdapter = new SeeJDB_VPAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new PendingJDBFragment(), "Pending", "jcdid");
        viewPagerAdapter.addFragment(new DeliveredJDBFragment(), "Delivered", "jcdid");
        viewPagerAdapter.addFragment(new AssignedJDBFragment(), "Assigned", "jcdid");

        viewPager.setAdapter(viewPagerAdapter);

    }
    public static String getCalculatedDate(String dateFormat, int days) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat s = new SimpleDateFormat(dateFormat);
        cal.add(Calendar.DAY_OF_YEAR, days);
        return s.format(new Date(cal.getTimeInMillis()));
    }
}
