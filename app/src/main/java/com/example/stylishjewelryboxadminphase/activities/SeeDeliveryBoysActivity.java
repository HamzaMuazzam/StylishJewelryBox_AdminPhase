package com.example.stylishjewelryboxadminphase.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.stylishjewelryboxadminphase.R;
import com.example.stylishjewelryboxadminphase.fragments.DeliveredFragment;
import com.example.stylishjewelryboxadminphase.fragments.TotalSaleFragment;
import com.example.stylishjewelryboxadminphase.fragments.UnAsssignedFragment;
import com.example.stylishjewelryboxadminphase.network.WebServices;
import com.example.stylishjewelryboxadminphase.recyclerviews.getalldeliveryboys.GetAllDeliveryBoy;
import com.example.stylishjewelryboxadminphase.recyclerviews.getalldeliveryboys.GetAllDeliveryBoyResponse;
import com.example.stylishjewelryboxadminphase.seeDeliveryboys.AssignedJDBFragment;
import com.example.stylishjewelryboxadminphase.seeDeliveryboys.DeliveredJDBFragment;
import com.example.stylishjewelryboxadminphase.seeDeliveryboys.PendingJDBFragment;
import com.example.stylishjewelryboxadminphase.seeDeliveryboys.SeeJDB_VPAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeeDeliveryBoysActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private TextView tv_seeJDB_pending, tv_seeJDB_assigned, tv_seeJDB_delivered;
    private Spinner spv_seeDeliveryBoy;
    private ArrayAdapter<String> arrayAdapterOfJDB;
    WebServices webServices;
    private String[] selectname, namesarrays, jdb_ids;
    private String delivery_boy_name = null;
    public  String jdb_id=null;

    private TabLayout tabLayout;
    private ViewPager viewPager;


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

        //Api network
        webServices = WebServices.RETROFIT.create(WebServices.class);

        //tablayout
        tabLayout = findViewById(R.id.tlo_seeJDBs);
        viewPager = findViewById(R.id.vp_seeJDBs);
        viewPager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(viewPager);
        setupViewPager(viewPager);

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
                jdb_id = jdb_ids[position];
                tabLayout.setupWithViewPager(viewPager);
                setupViewPager(viewPager);
                Toast.makeText(SeeDeliveryBoysActivity.this, jdb_id, Toast.LENGTH_SHORT).show();


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


}
