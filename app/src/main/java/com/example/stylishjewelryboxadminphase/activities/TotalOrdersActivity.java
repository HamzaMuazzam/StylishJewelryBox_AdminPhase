package com.example.stylishjewelryboxadminphase.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.stylishjewelryboxadminphase.R;
import com.example.stylishjewelryboxadminphase.fragments.DeliveredFragment;
import com.example.stylishjewelryboxadminphase.fragments.PendingFragment;
import com.example.stylishjewelryboxadminphase.fragments.TotalSaleFragment;
import com.example.stylishjewelryboxadminphase.fragments.UnAsssignedFragment;
import com.example.stylishjewelryboxadminphase.network.WebServices;
import com.example.stylishjewelryboxadminphase.viewpager.ViewPagerAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

public class TotalOrdersActivity extends AppCompatActivity implements PendingFragment.NoOfPendingInterface,
        DeliveredFragment.NoOfDeliveredInterface, UnAsssignedFragment.NoOfUnAssignedInterface, TotalSaleFragment.TotalSale {
    WebServices webServices;
    public static TextView tv_totalDeliverOrder, tv_TotalPendingOrders, tv_TotalUnassignedOrders, tv_totalSale;
    public static String DATE = "2020-03-18";
    public static String LOCATION = "gulberg";
    public static String strDate = null;
    private EditText ed_address;

    public static TabLayout tabLayout;
    private ViewPager viewPager;


    public static String pendingorder,deliveredorder,unassignedorers;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_orders);
        initviews();
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        drawablelistner();
    }


    private void initviews() {
        webServices = WebServices.RETROFIT.create(WebServices.class);


        ed_address = findViewById(R.id.ed_address);

        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.myViewPager);
        viewPager.setOffscreenPageLimit(4);

        tv_totalDeliverOrder = findViewById(R.id.tv_totalDeliverOrder);
        tv_TotalPendingOrders = findViewById(R.id.tv_TotalPendingOrders);
        tv_TotalUnassignedOrders = findViewById(R.id.tv_TotalUnassignedOrders);
        tv_totalSale = findViewById(R.id.tv_totalSale);


    }

    private void drawablelistner() {
        ed_address.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (ed_address.getRight() - ed_address.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        calander();

                        return true;
                    }
                }
                return false;
            }

        });

    }

    private void calander() {
        Calendar calendar = Calendar.getInstance();
        int DAY = calendar.get(Calendar.DAY_OF_MONTH);
        int MONTH = calendar.get(Calendar.MONTH);
        int YEAR = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance((view, year, monthOfYear, dayOfMonth) -> {

            if (monthOfYear < 9) {
                strDate = year + "-0" + (monthOfYear + 1) + "-" + dayOfMonth;
            } else {
                strDate = year + "-" + (+monthOfYear + 1) + "-" + dayOfMonth;
            }

            if (strDate != null) {
                String location = ed_address.getText().toString().trim();
                if (!TextUtils.isEmpty(location)) {
                    LOCATION = location;
                    DATE = strDate;
                    tv_totalDeliverOrder.setText("NA");
                    tv_TotalPendingOrders.setText("NA");
                    tv_TotalUnassignedOrders.setText("NA");
                    tv_totalSale.setText("NA");

                    int i = tabLayout.getSelectedTabPosition();
                    setupViewPager(viewPager);
                    tabLayout.getTabAt(i).select();

                } else {
                    ed_address.setError("Can't be empty");

                }


            } else {
//                tvDate.setText("Please Select Date First");
            }
        }, YEAR, MONTH, DAY);
        datePickerDialog.setOnCancelListener(dialog -> {
            View contextView = findViewById(R.id.context_view1);
            dialog.dismiss();
            Snackbar.make(contextView, "No Date Selected ", Snackbar.LENGTH_SHORT).setAction("Open Calender", v -> calander()).show();
        });
        datePickerDialog.show(getSupportFragmentManager(), "Date Picker");
    }

    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new PendingFragment(), "Pending", "jcdid");
        viewPagerAdapter.addFragment(new DeliveredFragment(), "Delivered", "jcdid");
        viewPagerAdapter.addFragment(new UnAsssignedFragment(), "UnAssigned", "jcdid");
        viewPagerAdapter.addFragment(new TotalSaleFragment(), "Sale", "jcdid");
        viewPager.setAdapter(viewPagerAdapter);
//        try {
//            mInterface = (RefreshFragmentsByParams)getApplicationContext();
//        } catch (Exception e) {
//            Toast.makeText(this,"Exception: "+e.getMessage(),Toast.LENGTH_SHORT).show();
//
//
//        }


    }


    @Override
    public void onPendingNoOfOrderReceived(int no) {
        tv_TotalPendingOrders.setText(String.valueOf(no));
        tv_TotalPendingOrders.setTextColor(Color.parseColor("#980404"));
        pendingorder=String.valueOf(no);


    }

    @Override
    public void onDeliveredNoOfOrderReceived(int no) {

        tv_totalDeliverOrder.setText(String.valueOf(no));
        tv_totalDeliverOrder.setTextColor(Color.parseColor("#980404"));
        deliveredorder=String.valueOf(no);

    }

    @Override
    public void onUnAssignedNoOfOrderReceived(int no) {
        tv_TotalUnassignedOrders.setTextColor(Color.parseColor("#000000"));
        tv_TotalUnassignedOrders.setText(String.valueOf(no));
        unassignedorers=String.valueOf(no);
    }


    @Override
    public void getTotalSale(String totalSale) {
        tv_totalSale.setText(totalSale);
    }
}
