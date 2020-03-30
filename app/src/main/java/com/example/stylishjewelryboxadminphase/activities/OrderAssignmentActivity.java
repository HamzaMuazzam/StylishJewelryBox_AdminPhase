package com.example.stylishjewelryboxadminphase.activities;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stylishjewelryboxadminphase.R;
import com.example.stylishjewelryboxadminphase.network.WebServices;
import com.example.stylishjewelryboxadminphase.order_assignment.GetAllOrdersByjdb;
import com.example.stylishjewelryboxadminphase.order_assignment.GetAllOrdersByjdbResponse;
import com.example.stylishjewelryboxadminphase.order_assignment.GetOrderForAssignment;
import com.example.stylishjewelryboxadminphase.order_assignment.GetOrderForAssignmentResponse;
import com.example.stylishjewelryboxadminphase.recyclerviews.assignorder.AssignOrders;
import com.example.stylishjewelryboxadminphase.recyclerviews.getalldeliveryboys.GetAllDeliveryBoy;
import com.example.stylishjewelryboxadminphase.recyclerviews.getalldeliveryboys.GetAllDeliveryBoyResponse;
import com.example.stylishjewelryboxadminphase.recyclerviews.getallorderforassignment.GetAllOrder_Assignment_Adapter;
import com.google.android.material.snackbar.Snackbar;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderAssignmentActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
        , View.OnLongClickListener {
    public static final String DATE_FORMAT_1 = "yyyy-MM-dd";
    TextView counter_text;
    public boolean is_in_action = false;
    List<GetOrderForAssignment> list = new ArrayList<>();
    List<GetOrderForAssignment> selection_list = new ArrayList<>();
    int counters = 0;
    String location;

    private EditText et_location;
    private Spinner spinner;
    private ArrayAdapter<String> arrayAdapter;
    private String[] selectname, locationarray, jdb_ids;
    private WebServices webServices;
    private String delivery_boy_name = null, jdb_id, strDate, strDate_getorderforassignment;
    private CardView card_date_calender, card_getorderbylocation;
    private ImageView iv_calander;
    private TextView tv_dateofcalneder, tv_numberoforders;
    private Button btn_apply;
    GetAllOrder_Assignment_Adapter adapter;

    private RecyclerView recyclerview_getallorderforassignment;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_assignment);
        initviews();
        getdeliveryboyslist();
        opencalender();
        getallorders();
    }

    private void getallorders() {
        btn_apply.setOnClickListener(v -> {
            location = et_location.getText().toString().trim();
            if (!TextUtils.isEmpty(location)) {
                opencalender_forgettingorders(location);

            } else {
                et_location.setError("Can't be empty");
            }

        });
    }

    private void opencalender_forgettingorders(String location) {
        Calendar calendar = Calendar.getInstance();
        int DAY = calendar.get(Calendar.DAY_OF_MONTH);
        int MONTH = calendar.get(Calendar.MONTH);
        int YEAR = calendar.get(Calendar.YEAR);


        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance((view, year, monthOfYear, dayOfMonth) -> {

            if (monthOfYear < 9) {
                strDate_getorderforassignment = year + "-0" + (monthOfYear + 1) + "-" + dayOfMonth;
            } else {
                strDate_getorderforassignment = year + "-" + (+monthOfYear + 1) + "-" + dayOfMonth;
            }
            if (strDate_getorderforassignment != null) {
//                Toast.makeText(OrderAssignmentActivity.this, "date : " + strDate_getorderforassignment + " Location: " + location, Toast.LENGTH_SHORT).show();
                getOrdersForAssignment(strDate_getorderforassignment, location);


            }

        }, YEAR, MONTH, DAY);
        datePickerDialog.setOnCancelListener(dialog -> {
            View contextView = findViewById(R.id.context_view1);
            dialog.dismiss();
            Snackbar.make(contextView, "No Date Selected ", Snackbar.LENGTH_SHORT).setAction("Open Calender", v -> OrderAssignmentActivity.this.calander()).show();
        });


        datePickerDialog.show(OrderAssignmentActivity.this.getSupportFragmentManager(), "Date Picker");

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
            if (strDate != null && delivery_boy_name != null && !delivery_boy_name.equalsIgnoreCase("Select Name")) {

                Toast.makeText(OrderAssignmentActivity.this, "date : " + strDate + "\n name: " + delivery_boy_name, Toast.LENGTH_SHORT).show();
                if (jdb_id != null)
                    webServices.getallorders_by_jdb(jdb_id, strDate).enqueue(new Callback<GetAllOrdersByjdbResponse>() {
                        @Override
                        public void onResponse(Call<GetAllOrdersByjdbResponse> call, Response<GetAllOrdersByjdbResponse> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                List<GetAllOrdersByjdb> list = response.body().getGetAllOrdersByjdb();
                                if (response.body().getStatus()) {
                                    Toast.makeText(OrderAssignmentActivity.this, "" + list.size(), Toast.LENGTH_SHORT).show();
                                    card_date_calender.setVisibility(View.VISIBLE);
                                    tv_numberoforders.setText(String.valueOf(list.size()));
                                    tv_dateofcalneder.setText(strDate);
                                } else {
                                    tv_numberoforders.setText("0");
                                    tv_dateofcalneder.setText(strDate);
                                    card_date_calender.setVisibility(View.VISIBLE);
                                }
                            }


                        }

                        @Override
                        public void onFailure(Call<GetAllOrdersByjdbResponse> call, Throwable t) {

                        }
                    });

            } else {

            }

        }, YEAR, MONTH, DAY);
        datePickerDialog.setOnCancelListener(dialog -> {
            View contextView = findViewById(R.id.context_view1);
            dialog.dismiss();
            Snackbar.make(contextView, "No Date Selected ", Snackbar.LENGTH_SHORT).setAction("Open Calender", v -> OrderAssignmentActivity.this.calander()).show();
        });

        datePickerDialog.show(OrderAssignmentActivity.this.getSupportFragmentManager(), "Date Picker");
    }


    private void opencalender() {
        iv_calander.setOnClickListener(v -> {
            calander();

        });
    }


    public void getOrdersForAssignment(String date, String location) {
        webServices.getOrderForAssignments("0", location, date).enqueue(new Callback<GetOrderForAssignmentResponse>() {
            @Override
            public void onResponse(Call<GetOrderForAssignmentResponse> call, Response<GetOrderForAssignmentResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getStatus()) {

                        list = response.body().getGetOrderForAssignment();
                        adapter = new GetAllOrder_Assignment_Adapter(OrderAssignmentActivity.this, list);
                        recyclerview_getallorderforassignment.setAdapter(adapter);
                        recyclerview_getallorderforassignment.setLayoutManager(new LinearLayoutManager(OrderAssignmentActivity.this));
                        recyclerview_getallorderforassignment.setVisibility(View.VISIBLE);
                        adapter.notifyDataSetChanged();

                    } else {
                        recyclerview_getallorderforassignment.setVisibility(View.GONE);
                        Toast.makeText(OrderAssignmentActivity.this, "No Order Found\n status: " + response.body().getStatus(), Toast.LENGTH_LONG).show();
                    }

                }

            }

            @Override
            public void onFailure(Call<GetOrderForAssignmentResponse> call, Throwable t) {
                recyclerview_getallorderforassignment.setVisibility(View.GONE);
                Toast.makeText(OrderAssignmentActivity.this, "No Order Found\n Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    private void initviews() {


        toolbar = findViewById(R.id.tooolbar);
        setSupportActionBar(toolbar);
        counter_text = findViewById(R.id.counter_text);
        counter_text.setVisibility(View.GONE);

        et_location = findViewById(R.id.et_location);
        btn_apply = findViewById(R.id.btn_apply);
        tv_dateofcalneder = findViewById(R.id.tv_dateofcalneder);
        tv_numberoforders = findViewById(R.id.tv_numberoforders);
        webServices = WebServices.RETROFIT.create(WebServices.class);
        spinner = findViewById(R.id.order_spiner);
        card_date_calender = findViewById(R.id.card_date_calender);
        iv_calander = findViewById(R.id.iv_calander);
        card_getorderbylocation = findViewById(R.id.card_getorderbylocation);

        recyclerview_getallorderforassignment = findViewById(R.id.recyclerview_getallorderforassignment);
        selectname = new String[1];
        selectname[0] = "Select Name";

    }

    private void getdeliveryboyslist() {
        webServices.getAll_JDBs("1").enqueue(new Callback<GetAllDeliveryBoyResponse>() {
            @Override
            public void onResponse(Call<GetAllDeliveryBoyResponse> call, Response<GetAllDeliveryBoyResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getStatus()) {
                        List<GetAllDeliveryBoy> list = response.body().getGetAllDeliveryBoy();
                        locationarray = new String[list.size()];
                        jdb_ids = new String[list.size() + 1];
                        jdb_ids[0] = "000";
                        for (int x = 0; x < list.size(); x++) {

                            locationarray[x] = list.get(x).getJdbName();
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


        String[] locationarray1 = locationarray;
        String[] locationarray2 = selectname;

        int length1 = locationarray1.length;
        int length2 = locationarray2.length;

        String[] new_array = new String[length1 + length2];

        System.arraycopy(locationarray2, 0, new_array, 0, length2);
        System.arraycopy(locationarray1, 0, new_array, length2, length1);


//        Toast.makeText(this, String.valueOf(length), Toast.LENGTH_SHORT).show();

        arrayAdapter = new ArrayAdapter<String>(OrderAssignmentActivity.this, android.R.layout.simple_spinner_item, new_array);

        arrayAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(OrderAssignmentActivity.this);
    }

    private String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_1);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date today = Calendar.getInstance().getTime();
        return dateFormat.format(today);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String currentDate = getCurrentDate();

        if (parent != null) {
            delivery_boy_name = parent.getItemAtPosition(position).toString();
            ((TextView) parent.getChildAt(0)).setText(delivery_boy_name);
            ((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#000000"));

            if (!delivery_boy_name.equalsIgnoreCase("Select Name")) {
                jdb_id = jdb_ids[position];
//                Toast.makeText(getContext(), jdb_id + "\n" + currentDate, Toast.LENGTH_SHORT).show();
                webServices.getallorders_by_jdb(jdb_id, currentDate).enqueue(new Callback<GetAllOrdersByjdbResponse>() {
                    @Override
                    public void onResponse(Call<GetAllOrdersByjdbResponse> call, Response<GetAllOrdersByjdbResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            List<GetAllOrdersByjdb> list = response.body().getGetAllOrdersByjdb();
                            if (response.body().getStatus()) {

//                                Toast.makeText(OrderAssignmentActivity.this, "" + list.size(), Toast.LENGTH_SHORT).show();

                                card_date_calender.setVisibility(View.VISIBLE);
                                tv_numberoforders.setText(String.valueOf(list.size()));
                                tv_dateofcalneder.setText(currentDate);
                                card_date_calender.setVisibility(View.VISIBLE);
                                card_getorderbylocation.setVisibility(View.VISIBLE);

//                                getOrdersForAssignment("2020-02-26","gulberg");


                            } else {
                                tv_numberoforders.setText("0");
                                card_getorderbylocation.setVisibility(View.VISIBLE);

                                tv_dateofcalneder.setText(currentDate);
                                card_date_calender.setVisibility(View.VISIBLE);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<GetAllOrdersByjdbResponse> call, Throwable t) {

                    }
                });


            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @SuppressLint("RestrictedApi")
    @Override
    public boolean onLongClick(View v) {
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.menu_action_mode);
        counter_text.setVisibility(View.VISIBLE);
        adapter.notifyDataSetChanged();
        is_in_action = true;
        Objects.requireNonNull(getSupportActionBar()).setDefaultDisplayHomeAsUpEnabled(true);
        View viewById = toolbar.findViewById(R.id.item_delete);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assign_orders();
            }
        });
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }


    public void prepareSelection(View v, int position) {
        if (((CheckBox) v).isChecked()) {

            selection_list.add(list.get(position));


            counters = counters + 1;
            updateCounter(counters);
        } else {

            selection_list.remove(list.get(position));
            counters = counters - 1;
            updateCounter(counters);
        }
    }


    private void updateCounter(int counters) {
        if (counters == 0) {
//        counter_text.setVisibility(View.VISIBLE);
            counter_text.setText("0 Items Selected");
        } else {
            counter_text.setText(counters + " Items Selected");
        }
    }


    public void assign_orders() {

        int listsize = selection_list.size();
        if (listsize == 0) {
            Toast.makeText(this, "Please Select any Order first", Toast.LENGTH_LONG).show();
        } else {
            int newsize = listsize + 1;
            String[] array = new String[newsize];
//            array[0] = jdb_id;
            for (int x = 0; x < listsize; x++) {
                array[x] = selection_list.get(x).getJomdOrderId();
            }
            array[listsize] = jdb_id;


            JSONArray json_array = new JSONArray();
            for (String s : array) {
                json_array.put(s);
            }

            System.out.println(json_array);


            Toast.makeText(this, "Date : " + strDate_getorderforassignment + "\n total orders " + (json_array.length() - 1) + "\n Lcoation: " + location
                    + "\n ID: " + jdb_id, Toast.LENGTH_SHORT).show();


            webServices.assign_Orders(json_array, "/topics/" + jdb_id, strDate_getorderforassignment, location, String.valueOf(json_array.length() - 1))
                    .enqueue(new Callback<AssignOrders>() {
                        @Override
                        public void onResponse(Call<AssignOrders> call, Response<AssignOrders> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                if (response.body().getStatus()) {

                                    Toast.makeText(OrderAssignmentActivity.this, "status true ", Toast.LENGTH_LONG).show();

                                    if (selection_list != null) {
                                        selection_list.clear();

                                    }

                                    getOrdersForAssignment(strDate_getorderforassignment, location);
                                } else {
                                    Toast.makeText(OrderAssignmentActivity.this, "status false ", Toast.LENGTH_LONG).show();


                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<AssignOrders> call, Throwable t) {
                            Toast.makeText(OrderAssignmentActivity.this, "OnFailure :" + t.getMessage(), Toast.LENGTH_LONG).show();


                        }
                    });
        }
    }

    public String removeLeadingPlus(String digits) {
        //String.format("%.0f", Double.parseDouble(digits)) //Alternate Solution
//        String regex = "^"+"+";
//        return digits.replaceAll(regex, "");
        StringBuffer buffer = new StringBuffer(digits);
        StringBuffer buffer1 = buffer.deleteCharAt(0);
        return buffer1.toString();
    }


}
