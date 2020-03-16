package com.example.stylishjewelryboxadminphase.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stylishjewelryboxadminphase.R;
import com.example.stylishjewelryboxadminphase.network.GetAllOrdersByjdb;
import com.example.stylishjewelryboxadminphase.network.GetAllOrdersByjdbResponse;
import com.example.stylishjewelryboxadminphase.network.GetOrderForAssignment;
import com.example.stylishjewelryboxadminphase.network.GetOrderForAssignmentResponse;
import com.example.stylishjewelryboxadminphase.network.WebServices;
import com.example.stylishjewelryboxadminphase.recyclerviews.getalldeliveryboys.GetAllDeliveryBoy;
import com.example.stylishjewelryboxadminphase.recyclerviews.getalldeliveryboys.GetAllDeliveryBoyResponse;
import com.example.stylishjewelryboxadminphase.recyclerviews.getallorderforassignment.GetAllOrder_Assignment_Adapter;
import com.google.android.material.snackbar.Snackbar;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AssignFragment extends Fragment  {
  View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_assign, container, false);



        return view;
    }














}
